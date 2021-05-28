package io.rocketbase.mail.line;

import lombok.Getter;

@Getter
public class AbstractHrLine<E extends AbstractHrLine> {

    String margin;


    public E margin(String margin) {
        this.margin = margin;
        return (E) this;
    }
}
