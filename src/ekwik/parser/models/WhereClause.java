package ekwik.parser.models;

public class WhereClause {
    private String clause;

    public WhereClause(String clause) {
        this.clause = clause;
    }

    @Override
    public String toString() {
        return "WhereClause{" +
                "clause='" + clause + '\'' +
                '}';
    }
}
