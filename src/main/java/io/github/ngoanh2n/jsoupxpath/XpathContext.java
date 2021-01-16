package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

/**
 * Repository: <a href="https://github.com/ngoanh2n/jsoup-xpath">https://github.com/ngoanh2n/jsoup-xpath</a>
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class XpathContext {

    protected final Evaluator evaluator;

    public XpathContext(NodeXpath xpath) {
        this.evaluator = new XpathEvaluator(xpath);
    }

    public Element findElement(Element root) {
        return findElements(root).first();
    }

    public Elements findElements(Element root) {
        return Collector.collect(evaluator, root);
    }
}
