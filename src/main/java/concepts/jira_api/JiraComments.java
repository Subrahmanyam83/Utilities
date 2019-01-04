package concepts.jira_api;

import java.util.List;

public class JiraComments {

    private List<JiraComment> comments;

    public List<JiraComment> getComments() {
        return comments;
    }

    public JiraComment getLast() {
        if(comments.isEmpty()) {
            return null;
        }

        return comments.get(comments.size() - 1);
    }
}
