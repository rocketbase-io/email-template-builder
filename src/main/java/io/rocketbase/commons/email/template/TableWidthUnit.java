package io.rocketbase.commons.email.template;

public enum TableWidthUnit {

    PIXEL(""), PERCENTAGE("%");

    private String html;

    TableWidthUnit(String html) {
        this.html = html;
    }

    public String getHtmlValue(Integer value) {
        if (value != null) {
            return String.valueOf(value) + html;
        }
        return "";
    }
}
