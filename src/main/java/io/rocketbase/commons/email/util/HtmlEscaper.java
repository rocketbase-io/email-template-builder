package io.rocketbase.commons.email.util;

public final class HtmlEscaper {

    public static String escapeHtml(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.replace("\"", "&quot;")
                .replace("'", "&#39;")
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
