package ekwik.parser.models;

public class Join {
    private String type;
    private String tableName;
    private String onClause;

    public Join(String type, String tableName, String onClause) {
        this.type = type;
        this.tableName = tableName;
        this.onClause = onClause;
    }

    @Override
    public String toString() {
        return "Join{" +
                "type='" + type + '\'' +
                ", tableName='" + tableName + '\'' +
                ", onClause='" + onClause + '\'' +
                '}';
    }
}
