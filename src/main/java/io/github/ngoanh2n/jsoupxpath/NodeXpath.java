package io.github.ngoanh2n.jsoupxpath;

import com.sun.istack.internal.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final LinkedList<Tag> tags;

    public NodeXpath(@NotNull Node node) {
        this.tags = nodeToTags(node);
    }

    public NodeXpath(@NotNull String xpath) {
        this.tags = xpathToTags(xpath);
    }

    public String getLocationPath() {
        StringBuilder path = new StringBuilder();
        if (!tags.isEmpty()) {
            path.append("/");
            tags.forEach(tag -> path.append("/").append(tag.toString()));
        }
        return path.toString();
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

    private static final class Tag {

        private int index;
        private final String name;

        Tag(String name) {
            this(name, 1);
        }

        Tag(String name, int index) {
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

    private LinkedList<Tag> nodeToTags(Node node) {
        LinkedList<Tag> tags = new LinkedList<>();

        if (NodeHelper.standard(node)) {
            Elements parents = ((Element) node).parents();

            for (int i = parents.size() - 1; i >= 0; i--) {
                tags.add(elementToTag(parents.get(i)));
            }
            tags.add(elementToTag((Element) node));
        }
        return tags;
    }

    private LinkedList<Tag> xpathToTags(String xpath) {
        if (!xpath.startsWith("/")) {
            xpath = "/" + xpath;
        }
        LinkedList<Tag> tags = new LinkedList<>();

        /*
        ^(|\/)(((\/[a-z]{1,10})(|\[\d\]))+)$
        * */
        String inputRegex = "^(|/)(((/[a-z]{1,10})(|\\[\\d]))+)$";
        Matcher inputMatcher = Pattern.compile(inputRegex).matcher(xpath);

        if (inputMatcher.find()) {
            xpath = inputMatcher.group(2);
            /*
             * ([a-z]{1,10})(\[\d\]|)
             * */
            String tagRegex = "([a-z]{1,10})(\\[\\d]|)";
            Matcher tagMatcher = Pattern.compile(tagRegex).matcher(xpath);

            while (tagMatcher.find()) {
                String tagName = tagMatcher.group(1);
                String tagIndex = tagMatcher.group(2);

                if (tagIndex.isEmpty()) {
                    tags.add(new Tag(tagName));
                } else {
                    /*
                     * ^\[(\d)\]$
                     * */
                    String indexRegex = "^\\[(\\d)]$";
                    Matcher indexMatcher = Pattern.compile(indexRegex).matcher(tagIndex);

                    if (indexMatcher.find()) {
                        tags.add(new Tag(tagName, Integer.parseInt(indexMatcher.group(1))));
                    }
                }
            }
        }

        return tags;
    }

    private static Tag elementToTag(Element element) {
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
