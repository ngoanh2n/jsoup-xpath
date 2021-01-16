package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

import javax.annotation.Nonnull;

/**
 * {@linkplain XpathEvaluator} is a org.jsoup.select.{@linkplain org.jsoup.select.Evaluator}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class XpathEvaluator extends Evaluator {

    private final Evaluator evaluator;

    public XpathEvaluator(@Nonnull String xpath) {
        this(new NodeXpath(xpath));
    }

    public XpathEvaluator(@Nonnull NodeXpath nodeXpath) {
        this.evaluator = XpathEvaluators.combination(nodeXpath.evaluators());
    }

    @Override
    public boolean matches(Element root, Element element) {
        return evaluator.matches(root, element);
    }
}
