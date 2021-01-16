package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import javax.annotation.Nonnull;

/**
 * A search context for org.jsoup.nodes.{@linkplain Document}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class XpathContext {

    protected final Document document;

    public XpathContext(@Nonnull Document document) {
        this.document = document;
    }

    /**
     * Find the first org.jsoup.nodes.{@linkplain Element} using the given xpath
     *
     * @param xpath is location path (chained tag names)
     * @return The first matching element on the current context
     */
    public Element findElement(@Nonnull String xpath) {
        return findElements(xpath).first();
    }

    /**
     * Find the all org.jsoup.nodes.{@linkplain Element} (org.jsoup.nodes.{@linkplain Elements}) using the given xpath
     *
     * @param xpath is location path (chained tag names)
     * @return The matching elements on the current context
     */
    public Elements findElements(@Nonnull String xpath) {
        Evaluator evaluator = new XpathEvaluator(xpath);
        return Collector.collect(evaluator, document);
    }
}
