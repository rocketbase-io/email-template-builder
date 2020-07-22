package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class TbFooterConfig {

    static final TbFooterConfig DEFAULT = new TbFooterConfig("#6B6E76",
            new TbFooterLink("#6B6E76", "underline"));

    public static final TbFooterConfig newInstance() {
        return new TbFooterConfig(DEFAULT);
    }

    private String color;
    private TbFooterLink link;

    public TbFooterConfig(TbFooterConfig other) {
        this.color = other.color;
        this.link = new TbFooterLink(other.link);
    }

    @Data
    @AllArgsConstructor
    public static class TbFooterLink {
        private String color;
        private String textDecoration;

        public TbFooterLink(TbFooterLink other) {
            this.color = other.color;
            this.textDecoration = other.textDecoration;
        }
    }
}
