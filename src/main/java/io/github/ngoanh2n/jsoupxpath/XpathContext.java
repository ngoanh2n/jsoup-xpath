package io.github.ngoanh2n.jsoupxpath;

import com.sun.istack.internal.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;

/**
 * Repository: <a href="https://github.com/ngoanh2n/jsoup-xpath">https://github.com/ngoanh2n/jsoup-xpath</a>
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class XpathContext {

    protected final Document document;

    public XpathContext(@NotNull Document document) {
        this.document = document;
    }

    public Element findElement(@NotNull String xpath) {
        return findElements(xpath).first();
    }

    public Elements findElements(String xpath) {
        XpathEvaluator evaluator = new XpathEvaluator(xpath);
        return Collector.collect(evaluator, document);
    }
}
