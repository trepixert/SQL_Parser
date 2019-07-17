package ekwik.parser.parsers.implementations;

import ekwik.parser.models.InnerQuery;
import ekwik.parser.models.Join;
import ekwik.parser.models.Query;
import ekwik.parser.models.Sort;
import ekwik.parser.models.Source;
import ekwik.parser.models.WhereClause;
import ekwik.parser.parsers.SQLParser;

import java.util.Scanner;

public class SQLParserImpl implements SQLParser {
    private int count;

    private Query query = new Query();

    private String[] operators =
            {"SELECT", "FROM", "LEFT JOIN",
                    "RIGHT JOIN", "JOIN", "FULL JOIN",
                    "GROUP BY", "SORT BY", "HAVING", "LIMIT","OFFSET"};

    public void parse(String query) {
        query = findAndRemoveInnerQueries(query);
        String[] subexpressions = query.split("\n");
        for (String subexpression : subexpressions) {
            redirectOperatorParse(subexpression, this.query);
        }
    }

    private void innerParse(String query, Query queryInstance) {
        query = findAndRemoveInnerQueries(query);
        String[] subexpressions = query.split("\n");
        for (String subexpression : subexpressions) {
            redirectOperatorParse(subexpression, queryInstance);
        }
    }

    private String findAndRemoveInnerQueries(String query) {
        StringBuilder sb = new StringBuilder(query);
        InnerQuery innerQuery = new InnerQuery();
        int size = 0;
        while (true) {
            int indexStart = sb.indexOf("(select");
            if (indexStart == -1) break;
            int indexEnd = getIndexEnd(query, indexStart);
            size++;
            innerParse(sb.substring(indexStart+1, indexEnd), innerQuery);
            sb.delete(indexStart, indexEnd + 1);
            sb.insert(indexStart, "inner" + count++);
            this.query.addInnerQuery(innerQuery);
        }
        if (size == 0) return query;
        return new String(sb).replaceAll("[\\s]{2,}", " ");
    }

    private int getIndexEnd(String query, int indexStart) {
        char[] symbols = query.substring(indexStart + 1).toCharArray();
        int countOfBrackets = 1;
        for (int i = 0; i < symbols.length; i++) {
            char symbol = symbols[i];
            if (symbol == '(') countOfBrackets++;
            if (symbol == ')' && countOfBrackets == 1) return indexStart + (++i);
            if (symbol == ')') countOfBrackets--;
        }
        throw new IllegalArgumentException("Не соответствие скобок");
    }

    private void findColumns(String subexpression, String operator, Query query) {
        String[] columns = getElms(subexpression, operator, ",");
        for (String column : columns) {
            query.addColumn(column);
        }
    }

    private void findSources(String subexpression, String operator, Query query) {
        String[] sources = getElms(subexpression, operator, ",");
        for (String source : sources) {
            query.addSource(new Source(source));
        }
    }

    private void findJoins(String subexpression, String operator, Query query) {
        String[] joins = getElms(subexpression, operator, "on");
        query.addJoin(new Join(operator, joins[0], joins[1]));
    }

    private void findClauses(String subexpression, String operator, Query query) {
        String[] clauses = getElms(subexpression, operator, "or");
        for (String clause : clauses) {
            query.addWhereClause(new WhereClause(clause));
        }
    }

    private void findGroups(String subexpression, String operator, Query query) {
        String[] groups = getElms(subexpression, operator, ",");
        for (String column : groups) {
            query.addGroupBy(column);
        }
    }

    private void findSorts(String subexpression, String operator, Query query) {
        String[] sorts = getElms(subexpression, operator, ",");
        for (String sort : sorts) {
            query.addSort(new Sort(sort));
        }
    }

    private void findLimit(String subexpression, String operator, Query query) {
        String[] limits = getElms(subexpression, operator, ",");
        for (String limit : limits) {
            query.setLimit(Integer.valueOf(limit));
        }
    }

    private void findOffset(String subexpression, String operator, Query query){
        String[] offsets = getElms(subexpression,operator,",");
        for (String offset : offsets) {
            query.setOffset(Integer.valueOf(offset));
        }
    }

    private String[] getOperators() {
        return operators;
    }

    private void redirectOperatorParse(String subexpression, Query query) {
        String operator = getOperator(subexpression);
        switch (operator) {
            case "SELECT":
                findColumns(subexpression, operator, query);
                break;
            case "FROM":
                findSources(subexpression, operator, query);
                break;
            case "LEFT JOIN":
            case "RIGHT JOIN":
            case "JOIN":
            case "FULL JOIN":
                findJoins(subexpression, operator, query);
                break;
            case "GROUP BY":
                findGroups(subexpression, operator, query);
                break;
            case "SORT BY":
                findSorts(subexpression, operator, query);
                break;
            case "HAVING":
                findClauses(subexpression, operator, query);
                break;
            case "LIMIT":
                findLimit(subexpression, operator, query);
                break;
            case "OFFSET":
                findOffset(subexpression,operator,query);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }

    private String getOperator(String subexpression) {
        Scanner scanner = new Scanner(subexpression.toUpperCase());
        String[] operators = getOperators();
        String operator = "";
        for (String o : operators) {
            operator = scanner.findInLine(o);
            if (operator != null) break;
        }
        return operator;
    }

    private String[] getElms(String subexpression, String operator, String delimiter) {
        return subexpression
                .toLowerCase()
                .replace(operator.toLowerCase(), "")
                .replace(";", "")
                .replaceAll("[\\s]{2,}", " ")
                .trim()
                .split(delimiter);
    }

    public Query getQuery() {
        return query;
    }
}
