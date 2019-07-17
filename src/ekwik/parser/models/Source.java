package ekwik.parser.models;

public class Source {

    private String sourceName;

    public Source(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "Source{" +
                "sourceName='" + sourceName + '\'' +
                '}';
    }
}
