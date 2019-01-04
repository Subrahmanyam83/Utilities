package concepts.jira_api;

public class JiraComment {
    private JiraPerson author;
    private String body;

    public JiraPerson getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return body;
    }
}
