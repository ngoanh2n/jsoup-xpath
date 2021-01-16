package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public abstract class AbstractTest {

    static Document document;

    @BeforeAll
    static void loadDummyHtml() throws IOException {
        ClassLoader classLoader = AbstractTest.class.getClassLoader();
        URL resource = classLoader.getResource("io/github/ngoanh2n/jsoupxpath/dummy.html");

        if (resource == null) {
            throw new IllegalArgumentException("Dummy HTML not found!");
        } else {
            File html = new File(resource.getFile());
            StringBuilder sb = new StringBuilder();
            Stream<String> lines = Files.lines(Paths.get(html.getAbsolutePath()), StandardCharsets.UTF_8);
            lines.forEach(sb::append);
            document = Jsoup.parse(sb.toString());
        }
    }
}
