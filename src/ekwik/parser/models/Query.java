package ekwik.parser.models;

import java.util.ArrayList;
import java.util.List;

public class Query {
    protected List<String> columns;
    protected List<Source> fromSources;
    protected List<Join> joins;
    protected List<WhereClause> whereClauses;
    protected List<String> groupByColumns;
    protected List<Sort> sortColumns;
    protected List<InnerQuery> innerQueries;
    protected Integer limit;
    protected Integer offset;

    public Query() {
        this.columns = new ArrayList<>();
        this.fromSources = new ArrayList<>();
        this.joins = new ArrayList<>();
        this.whereClauses = new ArrayList<>();
        this.sortColumns = new ArrayList<>();
        this.groupByColumns = new ArrayList<>();
        this.innerQueries = new ArrayList<>();
    }

    public void addColumn(String column) {
        columns.add(column);
    }

    public void addSource(Source source) {
        fromSources.add(source);
    }

    public void addJoin(Join join) {
        joins.add(join);
    }

    public void addWhereClause(WhereClause clause) {
        whereClauses.add(clause);
    }

    public void addGroupBy(String column) {
        groupByColumns.add(column);
    }

    public void addSort(Sort sort) {
        sortColumns.add(sort);
    }

    public void addInnerQuery(InnerQuery innerQuery) {
        innerQueries.add(innerQuery);
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<Source> getFromSources() {
        return fromSources;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public List<String> getGroupByColumns() {
        return groupByColumns;
    }

    public List<Sort> getSortColumns() {
        return sortColumns;
    }

    public List<InnerQuery> getInnerQueries() {
        return innerQueries;
    }

    @Override
    public String toString() {
        return String.format("Query {\n" +
                        "\tcolumns= %s \n" +
                        "\tfromSource= %s \n" +
                        "\tjoins= %s \n" +
                        "\twhereClauses= %s \n" +
                        "\tgroupByColumns= %s \n" +
                        "\tsortColumns= %s \n" +
                        "\tinnerQueries= %s \n" +
                        "\tlimit= %d\n" +
                        "\toffset= %d \n" +
                        '}',
                columns.toString(), fromSources.toString(),
                joins.toString(), whereClauses.toString(),
                groupByColumns.toString(), sortColumns.toString(),
                innerQueries.toString(),
                limit, offset);
    }
}
