package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.List;

/**
 * Provides org.jsoup.select.{@linkplain Evaluator}s to build {@linkplain XpathEvaluator}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class XpathEvaluators {

    public static Evaluator root() {
        return new RootEvaluator();
    }

    public static Evaluator ancestor(Evaluator evaluator) {
        return new AncestorEvaluator(evaluator);
    }

    public static Evaluator parent(Evaluator evaluator) {
        return new ParentEvaluator(evaluator);
    }

    public static Evaluator tag(String tagName) {
        return new Evaluator.Tag(tagName.trim().toLowerCase());
    }

    public static Evaluator index(int b) {
        return new IndexEvaluator(0, b);
    }

    public static Evaluator combination(List<Evaluator> evaluators) {
        return new CombinationEvaluator(evaluators);
    }

    private static final class RootEvaluator extends Evaluator {

        @Override
        public boolean matches(Element root, Element element) {
            return root == element;
        }
    }

    private static final class AncestorEvaluator extends Evaluator {

        private final Evaluator evaluator;

        private AncestorEvaluator(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        public boolean matches(Element root, Element element) {
            Element parent = element.parent();
            while (parent != null) {
                if (evaluator.matches(root, parent)) {
                    return true;
                }
                parent = parent.parent();
            }
            return false;
        }
    }

    private static final class ParentEvaluator extends Evaluator {

        private final Evaluator evaluator;

        private ParentEvaluator(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        public boolean matches(Element root, Element element) {
            Element parent = element.parent();
            return parent != null && evaluator.matches(root, parent);
        }
    }

    private static final class IndexEvaluator extends Evaluator.CssNthEvaluator {

        private IndexEvaluator(int a, int b) {
            super(a, b);
        }

        protected int calculatePosition(Element root, Element element) {
            int position = 0;
            Elements family = element.parent().children();
            for (Element value : family) {
                if (value.tag().equals(element.tag())) position++;
                if (value == element) break;
            }
            return position;
        }

        @Override
        protected String getPseudoClass() {
            return "index";
        }
    }

    private static final class CombinationEvaluator extends Evaluator {

        private final List<Evaluator> evaluators;

        private CombinationEvaluator(List<Evaluator> evaluators) {
            this.evaluators = evaluators;
        }

        @Override
        public boolean matches(Element root, Element node) {
            for (Evaluator evaluator : evaluators) {
                if (!evaluator.matches(root, node)) {
                    return false;
                }
            }
            return true;
        }
    }
}
