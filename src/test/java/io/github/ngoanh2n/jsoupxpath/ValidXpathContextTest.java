package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class XpathContextTest extends AbstractTest {

    @Test
    void test1() {
        XpathContext context = new XpathContext(document);
        Element element = context.findElement("html/body/div/input");
        assertNotNull(element);
    }

    @Test
    void test2() {
        XpathContext context = new XpathContext(document);
        Element element = context.findElement("/html/body/div/input[2]");
        assertNotNull(element);
    }

    @Test
    void test3() {
        XpathContext context = new XpathContext(document);
        Element element = context.findElement("//html[1]/body[1]/div[1]/input[3]");
        assertNotNull(element);
    }
}
