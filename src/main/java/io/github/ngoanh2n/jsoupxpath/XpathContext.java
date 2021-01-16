package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;

/**
 * Repository: <a href="https://github.com/ngoanh2n/jsoup-xpath">https://github.com/ngoanh2n/jsoup-xpath</a>
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

    public Element findElement(@Nonnull String xpath) {
        return findElements(xpath).first();
    }

    public Elements findElements(@Nonnull String xpath) {
        XpathEvaluator evaluator = new XpathEvaluator(xpath);
        return Collector.collect(evaluator, document);
    }
}
