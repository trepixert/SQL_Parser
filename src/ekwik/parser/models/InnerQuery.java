package ekwik.parser.models;

public class InnerQuery extends Query{
    @Override
    public String toString() {
        return String.format("Query {\n" +
                        "\t\tcolumns= %s \n" +
                        "\t\tfromSource= %s \n" +
                        "\t\tjoins= %s \n" +
                        "\t\twhereClauses= %s \n" +
                        "\t\tgroupByColumns= %s \n" +
                        "\t\tsortColumns= %s \n" +
                        "\t\tinnerQueries= %s \n" +
                        "\t\tlimit= %d\n" +
                        "\t\toffset= %d \n" +
                        "\t\t}",
                columns.toString(), fromSources.toString(),
                joins.toString(), whereClauses.toString(),
                groupByColumns.toString(), sortColumns.toString(),
                innerQueries.toString(),
                limit, offset);
    }
}
