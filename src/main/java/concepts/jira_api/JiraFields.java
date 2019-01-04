package concepts.jira_api;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class JiraFields {
    private JiraStatus status;

    private String summary;

    private JiraPerson assignee;

    @SerializedName("comment")
    private JiraComments comments;

    private Date updated;


    public JiraStatus getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public JiraPerson getAssignee() {
        return assignee;
    }

    public JiraComments getComments() {
        return comments;
    }

    public Date getUpdated() {
        return updated;
    }
}
