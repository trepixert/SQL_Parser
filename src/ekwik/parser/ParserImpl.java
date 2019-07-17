package ekwik.parser;

import ekwik.parser.models.Query;
import ekwik.parser.parsers.SQLParser;
import ekwik.parser.parsers.implementations.SQLParserImpl;

public class ParserImpl implements Parser {
    private final SQLParser sqlParser = new SQLParserImpl();

    @Override
    public void doSQLParse(String query) {
        sqlParser.parse(query);
        Query parserQuery = sqlParser.getQuery();
        System.out.println(parserQuery);
    }
}
