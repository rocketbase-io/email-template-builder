package io.rocketbase.commons.email.template;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public interface TemplateLine {

    TemplateLineType getType();

    EmailTemplateConfigBuilder and();

    HtmlTextEmail build();

    /**
     * will replace <br>
     * links to => text -> url<br>
     * &lt;br&gt; => newline<br>
     * &lt;p&gt;..&lt;/p&gt; => ...newline<br>
     *
     * @param doc that could get modified
     * @return correctly escaped txt version of doc
     */
    default String extractHtml(Document doc) {
        Elements links = doc.body().getElementsByTag("a");
        links.forEach(e -> {
            if (!e.attr("href").equals("")) {
                e.appendChild(new TextNode(" -> " + e.attr("href")));
            }
        });
        Elements brs = doc.body().getElementsByTag("br");
        brs.forEach(e -> {
            e.replaceWith(new TextNode("\n"));
        });
        Elements ps = doc.body().getElementsByTag("p");
        ps.forEach(e -> {
            e.replaceWith(new TextNode(String.format("%s\n", e.wholeText())));
        });
        return doc.wholeText().replaceAll("\n$", "");
    }

    enum TemplateLineType {

        TEXT, BUTTON, IMAGE, FOOTER, TABLE;
    }

}
