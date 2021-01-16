package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NodeXpathTest extends AbstractTest {

    @Test
    void test() {
        Elements elements = document.select("input");

        for (int index = 1; index <= elements.size(); index++) {
            Element element = elements.get(index - 1);

            if (index == 1) {
                NodeXpath xpath = new NodeXpath(element);
                assertEquals("//html/body/div/input", xpath.getLocationPath());
            } else if (index == 2) {
                NodeXpath nodeXpath = new NodeXpath(element);
                assertEquals("//html/body/div/input[2]", nodeXpath.getLocationPath());
            } else if (index == 3) {
                NodeXpath nodeXpath = new NodeXpath(element);
                assertEquals("//html/body/div/input[3]", nodeXpath.getLocationPath());
            }
        }
    }
}
