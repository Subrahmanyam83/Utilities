package concepts.regression_stability;

import com.github.opendevl.JFlat;
import com.google.gson.Gson;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.View;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by Subrahmanyam Rentala
 * */

public class Regression_Job_Stats {

    final static Logger LOG = Logger.getLogger(Regression_Job_Stats.class);
    private JenkinsServer jenkinsServer;
    private List<TJob> jobList = new LinkedList<>();
    private int numberOfBuilds = 10;
    private boolean singleFile = true;
    /*Any sub-view separate it with view. eg: Regression/view/CUSTOMER*/
    private String rootView = "Investigation/view/WorkPackage3/view/POST_BOOKING_SANITY";
    private String autotestURL = "https://autotest.baplc.com/ci/view/";
    private String fileName = "PostBookingSanityStatus";
    private String jenkinsUrl = autotestURL + rootView + "/";
    private String pathToStoreFiles = System.getProperty("user.dir") + "/src/main/java/concepts/regression_stability/postBookingJobs/";
    private Set<String> invalidJobs = new HashSet<>();
    private String stringSeparatorToGetFunctionalArea = autotestURL + rootView + "/view/";
    private String functionalAreaName = null;
    private String NOT_COMPLETED = "NOT COMPLETED";

    /*
     * Setup steps
     * */
    @BeforeClass
    private void setup() throws URISyntaxException, IOException {
        LOG.info("Time started: " + getCurrentTime());
        jenkinsServer = new JenkinsServer(new URI(jenkinsUrl));
        deleteFolder(pathToStoreFiles);
        createFolder(pathToStoreFiles);
    }

    @Test
    public void getJSONFilesForStatistics() throws Exception {
        List<TJob> jobStatistics = getJobsStaticticsListAsJSON(jenkinsServer);

        /* Call this if you want to print the invalid jobs*/
        printInvalidJobs();
        createFilesForJobStatistics(jobStatistics, singleFile, FileFormat.CSV.format);
    }

    /*
     ** Creates a list of Jobs and its build details.
     */
    private List<TJob> getJobsStaticticsListAsJSON(JenkinsServer view) throws IOException, URISyntaxException {

        /* If View does not have jobs then recursively call the function */
        if (view.getJobs().size() == 0) {
            for (View subViews : view.getViews().values()) {
                functionalAreaName = subViews.getUrl().split(stringSeparatorToGetFunctionalArea)[1].split("/")[0].replace("%20", " ");
                getJobsStaticticsListAsJSON(new JenkinsServer(new URI(subViews.getUrl())));
            }
        } else {
            functionalAreaName = rootView.split("/")[rootView.split("/").length -1];
            for (Job job : view.getJobs().values()) {

                /* If number of actual builds are less than the given number then consider number of builds as the count */
                int copyNumberOfBuilds = numberOfBuilds > job.details().getAllBuilds().size() ? job.details().getAllBuilds().size() : numberOfBuilds;

                /*
                 * Iterate over each and every build to get job and build details
                 * */
                ArrayList<HashMap> jobBuilds = new ArrayList<>();
                if (copyNumberOfBuilds != 0) {
                    for (int i = 0; i < copyNumberOfBuilds; i++) {

                        int buildNumber = job.details().getAllBuilds().get(i).getNumber();
                        String url = job.getUrl() + buildNumber + "/testReport/api/json";

                        HashMap<String, Object> buildMap = new HashMap<String, Object>();
                        String buildDateAndTime = null;
                        String buildDay = null;
                        if (job.details().getBuildByNumber(buildNumber).details() != null) {
                            buildDateAndTime = (new Date(job.details().getBuildByNumber(buildNumber).details().getTimestamp())).toString();

                            DateFormat f = new SimpleDateFormat("MMM-dd");
                            buildDay = f.format(job.details().getBuildByNumber(buildNumber).details().getTimestamp());
                        }
                        JSONObject jsonObject = parseURLToGetJSONObject(url);
                        buildMap.put("buildDateAndTime", buildDateAndTime);
                        buildMap.put("buildDay", buildDay);
                        buildMap.put("buildJSONURLObject",jsonObject);
                        buildMap.put("buildNumber", buildNumber);
                        buildMap.put("functionalArea", functionalAreaName);
                        jobBuilds.add(buildMap);
                    }

                    TJob tJob = getJobStatistics(job.getName(), jobBuilds);
                    jobList.add(tJob);
                }
                jobBuilds.clear();
            }
        }
        return jobList;
    }

    /*
     *  Get test case details for each build of a job
     * */
    private TJob getJobStatistics(String jobName, List myBuilds) {
        TJob tJob = new TJob();
        tJob.builds = new LinkedList<>();

        /*
         * Iterate over each build of a job and get details.
         * */
        for (Object myBuild : myBuilds) {
            String buildDnT = (String) ((HashMap) myBuild).get("buildDateAndTime");
            String buildDay = (String) ((HashMap) myBuild).get("buildDay");
            JSONObject buildURLJSONObject = (JSONObject) ((HashMap) myBuild).get("buildJSONURLObject");
            int buildNum = (int) ((HashMap) myBuild).get("buildNumber");
            String functionalAreaForEachJob = (String) ((HashMap) myBuild).get("functionalArea");
            TBuild tbuild = new TBuild();
            tbuild.tCases = new LinkedList<>();

            /* Continue only if Build has produced valid results */
            if (buildURLJSONObject != null) {

                List<JSONObject> testCaseJSONArray = new LinkedList<>();
                        JSONArray jsonSuiteArray = buildURLJSONObject.getJSONArray("suites");

                        for(int j =0;j<jsonSuiteArray.length();j++){
                            JSONArray jsonArray = ((JSONObject)jsonSuiteArray.get(j)).getJSONArray("cases");
                            jsonArray.forEach(testCaseJsonObject->{
                                testCaseJSONArray.add((JSONObject) testCaseJsonObject);
                            });
                        }

                /* Iterate over each build's testReport JSON url*/
                for (int index = 0; index < testCaseJSONArray.size(); index++) {
                    TCase tCase = new TCase();
                    JSONObject testCaseDetails = testCaseJSONArray.get(index);

                    tCase.testCaseName = (String) testCaseDetails.get("name");
                    tCase.testCaseExecutionDuration = Integer.toString(((Double) testCaseDetails.get("duration")).intValue());
                    String testCaseStatus = (String) testCaseDetails.get("status");
                    if (testCaseStatus.equalsIgnoreCase("REGRESSION"))
                        testCaseStatus = "FAILED";
                    else if (testCaseStatus.equalsIgnoreCase("FIXED"))
                        testCaseStatus = "PASSED";
                    tCase.testCaseExecutionStatus = testCaseStatus;
                    tCase.buildTime = buildDnT;
                    tCase.buildDay = buildDay;
                    tCase.functionalArea = functionalAreaForEachJob;

                    tbuild.tCases.add(tCase);
                    tbuild.buildNumber = buildNum;
                }
            } else {
                TCase tCase = new TCase();
                tCase.testCaseName = NOT_COMPLETED;
                tCase.testCaseExecutionDuration = NOT_COMPLETED;
                tCase.testCaseExecutionStatus = NOT_COMPLETED;
                tCase.buildTime = buildDnT;
                tCase.buildDay = buildDay;
                tCase.functionalArea = functionalAreaForEachJob;

                tbuild.tCases.add(tCase);
                tbuild.buildNumber = buildNum;
            }
            tJob.builds.add(tbuild);
            tJob.jobName = jobName;
        }
        return tJob;
    }

    /*
     * Print the jobs with invalid builds which were unable to produce valid testReports
     * */
    private void printInvalidJobs() {
        if (invalidJobs.size() > 0) {
            LOG.fatal("The following jobs have not generated reports. Please investigate");
            for (String job : invalidJobs) {
                System.out.println(job);
            }
        }
    }

    /*
    Parse the URL which serves a JSON and create a JSON Object
    * */
    private JSONObject parseURLToGetJSONObject(String url) {
        JSONObject json = null;
        try {
            for(int i=0;i<2;i++){
                json = new JSONObject(IOUtils.toString(new URL(url),Charset.defaultCharset()));
            }
        } catch (Exception f) {
            /*TODO
            Implement Loggers instead of println
            */
            invalidJobs.add(url);
        }
        return json;
    }

    /*
     ** This method will iterate over each Jobs Statistics: Builds-> Test Cases Array-> Test case details
     **/
    private void createFilesForJobStatistics(List<TJob> myJobs, boolean singleFile, String format) {
        Gson gson = new Gson();
        if (!singleFile) {
            myJobs.forEach(eachJob -> {
                String jobStats = gson.toJson(eachJob);
                writeIntoFile(jobStats, eachJob.jobName, format);
            });
        } else {
            String jobStats = gson.toJson(myJobs);
            writeIntoFile(jobStats, fileName, format);
        }
    }

    /*
     * This method will write the JSON content to a specified file
     * */
    private void writeIntoFile(String content, String fileName, String format) {
        String path = pathToStoreFiles + fileName + ".json";
        try {
            FileUtils.writeStringToFile(new File(path), content, "UTF-8", false);
            if (format.equalsIgnoreCase(FileFormat.CSV.format)) {
                JSONtoCSVConvertor(fileName);
            }
        } catch (Exception e) {
            LOG.info(e.getStackTrace());
        }
    }

    /*
     ** Utility to convert a JSON to CSV
     * */
    private void JSONtoCSVConvertor(String fileName) throws IOException {
        String path = pathToStoreFiles + fileName + ".json";
        String str = new String(Files.readAllBytes(Paths.get(path)));
        JFlat jflat = new JFlat(str);
        jflat.json2Sheet();
        jflat.write2csv(pathToStoreFiles + fileName + ".csv");
    }

    /*
     ** Get the current time in the format specified
     */
    private String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("d-MMM-YYYY HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /*
     * Utility to delete a folder
     * */
    private void deleteFolder(String path) throws IOException {
        File directory = new File(path);
        if (directory.exists()) {
            FileUtils.forceDelete(directory);
        }
    }

    /*
     * Utility to create a folder
     * */
    private void createFolder(String path) throws IOException {
        File directory = new File(path);
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
        }
    }

    /* ENUM for the file format*/
    private enum FileFormat {
        JSON("json"),
        CSV("csv");

        private String format;

        FileFormat(String format) {
            this.format = format;
        }
    }

    /*
     * Create a POJO class for JOB, Build and Test Case
     * */
    private class TJob {
        private String jobName;
        private List<Regression_Job_Stats.TBuild> builds;
    }

    private class TBuild {
        private List<Regression_Job_Stats.TCase> tCases;
        private Integer buildNumber;
    }

    private class TCase {
        private String testCaseName = "Not Completed";
        private String testCaseExecutionDuration = "Not Completed";
        private String testCaseExecutionStatus = "Not Completed";
        private String buildTime = "Not Completed";
        private String functionalArea = "Not Completed";
        private String buildDay = "Not Completed";
    }

    /* Tear Down steps */
    @AfterClass
    private void tearDown() {
        LOG.info("Time completed: " + getCurrentTime());
    }
}
