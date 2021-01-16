package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to solve xpath (location path) for a {@linkplain Node} from its {@linkplain Document}
 * <br>
 * <br>
 * Repository: <a href="https://github.com/ngoanh2n/jsoup-xpath">https://github.com/ngoanh2n/jsoup-xpath</a>
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class NodeXpath {

    private final Node node;
    private final LinkedList<Tag> tags;

    public NodeXpath(Node node) {
        this.node = node;
        this.buildXpathUnits();
        this.tags = new LinkedList<>();
    }

    public String getLocationPath() {
        StringBuilder locations = new StringBuilder();
        if (!tags.isEmpty()) {
            locations.append("/");
            tags.forEach(tag -> locations.append("/").append(tag.toString()));
        }
        return locations.toString();
    }

    @Override
    public String toString() {
        return getLocationPath();
    }

    List<Evaluator> toEvaluators() {
        LinkedList<Evaluator> results = new LinkedList<>();

        for (int i = 0; i < tags.size(); i++) {
            Evaluator evaluator;
            Tag tag = tags.get(i);

            if (results.size() == 0) {
                evaluator = XpathEvaluators.root();
            } else if (results.size() == 1) {
                evaluator = results.get(0);
            } else {
                evaluator = XpathEvaluators.combination(results);
            }
            results.clear();
            LinkedList<Evaluator> evaluators = new LinkedList<>();
            evaluators.add(XpathEvaluators.tag(tag.name));

            if (!tag.isUnique()) {
                evaluators.add(XpathEvaluators.index(tag.index));
            }
            if (i == 0) {
                evaluators.add(XpathEvaluators.ancestor(evaluator));
            } else {
                evaluators.add(XpathEvaluators.parent(evaluator));
            }
            evaluator = XpathEvaluators.combination(evaluators);
            results.add(evaluator);
        }
        return results;
    }

    public static final class Tag {

        private int index;
        private final String name;

        Tag(String name) {
            this(1, name);
        }

        Tag(int index, String name) {
            this.name = name;
            this.index = index;
        }

        boolean isUnique() {
            return index == 1;
        }

        @Override
        public String toString() {
            return index == 1 ? name : name + "[" + index + "]";
        }
    }

    private void buildXpathUnits() {
        if (NodeHelper.standard(node)) {
            Elements parents = ((Element) node).parents();
            for (int i = parents.size() - 1; i >= 0; i--) {
                tags.add(toTag(parents.get(i)));
            }
            tags.add(toTag((Element) node));
        }
    }

    private static Tag toTag(Element element) {
        Tag tag = new Tag(element.nodeName());
        Element parent = element.parent();

        if (parent != null) {
            Elements children = parent.children();
            Elements sibling = children.select(element.nodeName());
            if (sibling.size() > 0) {
                for (int j = 0; j < sibling.size(); j++) {
                    if (sibling.get(j).equals(element)) {
                        tag.index = j + 1;
                        break;
                    }
                }
            }
        }
        return tag;
    }
}
