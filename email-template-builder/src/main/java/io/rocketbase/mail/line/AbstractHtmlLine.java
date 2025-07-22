package io.rocketbase.mail.line;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class AbstractHtmlLine <E extends AbstractHtmlLine> {

    final String html;
    final String text;

    private static Pattern PATTERN_A_TAG, PATTERN_A_HREF;

    static {
        PATTERN_A_TAG = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");
        PATTERN_A_HREF = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
    }

    public AbstractHtmlLine(String html, String text) {
        this.html = html;
        this.text = text;
    }

    /**
     * @param html will extract text version out of it.<br>
     *             new line for br and p and links will get shifted to text -> link<br>
     *             all other tags will get removed
     */
    public AbstractHtmlLine(String html) {
        this.html = html;
        this.text = generateTextVersion(html);
    }

    protected String embedLinks(String html) {
        String result = html + "";
        Matcher matcherATag = PATTERN_A_TAG.matcher(html);
        while (matcherATag.find()) {
            String href = matcherATag.group(1);
            String linkText = matcherATag.group(2);

            Matcher matcherHref = PATTERN_A_HREF.matcher(href);
            while (matcherHref.find()) {
                String link = matcherHref.group(1).replaceAll("('|\")", "");
                result = result.replace(matcherATag.group(), String.format("%s -> %s", linkText, link));
            }

        }
        return result;
    }

    protected String embedLineBreaks(String html) {
        return html.replaceAll("(\\<br\\>|\\<\\/p\\>)", "\n");
    }

    protected String generateTextVersion(String html) {
        return embedLineBreaks(embedLinks(html)).replaceAll("\\<.*?>", "");
    }
}
