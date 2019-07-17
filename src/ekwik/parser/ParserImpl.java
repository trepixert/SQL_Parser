package ekwik.parser;

import ekwik.parser.models.Query;
import ekwik.parser.parsers.SQLParser;
import ekwik.parser.parsers.implementations.SQLParserImpl;

public class ParserImpl implements Parser {
    private final SQLParser parser = new SQLParserImpl();

    @Override
    public void doSQLParse(String query) {
        parser.parse(query);
        Query parserQuery = parser.getQuery();
        System.out.println(parserQuery);
    }
}
