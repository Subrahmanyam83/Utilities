package concepts.jira_api;

import java.util.Date;

import static java.lang.String.format;

public class JiraIssues {

    private String key;

    private JiraFields fields;

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return format("https://jira.baplc.com/browse/%s", key);
    }

    public JiraComments getComments() {
        return fields.getComments();
    }

    public Date getLastUpdatedDate() {
        return fields.getUpdated();
    }


    public String getSummary() {
        return fields.getSummary();
    }

    public String getAssignee() {
        return fields.getAssignee().getDisplayName();
    }

    public JiraFields getFields() {
        return fields;
    }
}
