package io.github.ngoanh2n.jsoupxpath;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import javax.annotation.Nonnull;

/**
 * Utilities to handle for org.jsoup.nodes.{@linkplain Node}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 * @version 1.0.0
 * @since 2021-01-16
 */
public class NodeHelper {

    /**
     * Whether {@code node} is a standard node. <br>
     * This means it's not: {@linkplain Document}, {@linkplain DocumentType}, {@linkplain TextNode}
     *
     * @param node is a HTML element
     * @return Indicate {@code node} is a standard node or not
     */
    public static boolean standard(@Nonnull Node node) {
        if (!(node instanceof Document)) {
            if (!(node instanceof DocumentType)) {
                return !(node instanceof TextNode);
            }
        }
        return false;
    }

    /**
     * Whether {@code node} is a primitive node. <br>
     * This means it's not: {@linkplain Document}, {@linkplain DocumentType}, {@linkplain TextNode}. <br>
     * And has no any child node except {@linkplain TextNode}
     *
     * @param node is a HTML element
     * @return Indicate {@code node} is a standard node or not
     */
    public static boolean primitive(@Nonnull Node node) {
        if (standard(node)) {
            if (node.childNodes().size() == 0) {
                return true;
            }
            if (node.childNodes().size() == 1) {
                return node.childNodes().get(0) instanceof TextNode;
            }
        }
        return false;
    }
}
