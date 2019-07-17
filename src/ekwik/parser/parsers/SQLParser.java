package ekwik.parser.parsers;

import ekwik.parser.models.Query;

public interface SQLParser {
    void parse(String query);
    Query getQuery();
}
