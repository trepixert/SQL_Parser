package ekwik.parser;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Parser parser = new ParserImpl();

    public static void main(String[] args) {
        // write your code here
        new Main().start();
    }

    private void start() {
        try {
            String query = Objects.requireNonNull(getQuery(new File("query.txt")))
                    .orElseThrow(() -> new IllegalArgumentException("Ошибка при попытке чтения запроса"));
            parser.doSQLParse(query);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private Optional<String> getQuery(File file) {
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder sb = new StringBuilder();
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            return Optional.of(new String(sb));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
