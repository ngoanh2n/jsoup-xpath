package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class XpathContextTest extends AbstractTest {

    XpathContext context;

    @Test
    @Order(1)
    void invalidTest() {
        Element element = context.findElement("html/body/div[2]");
        assertNull(element);
    }

    @Order(2)
    @Test
    void validTest1() {
        Element element = context.findElement("html/body/div/input");
        assertNotNull(element);
    }

    @Order(3)
    @Test
    void validTest2() {
        Element element = context.findElement("/html/body/div/input[2]");
        assertNotNull(element);
    }

    @Order(4)
    @Test
    void validTest3() {
        Element element = context.findElement("//html[1]/body[1]/div[1]/input[3]");
        assertNotNull(element);
    }

    @BeforeEach
    void createXpathContext() {
        context = new XpathContext(document);
    }
}
