package io.rocketbase.mail.line;

import lombok.Getter;

@Getter
public class AbstractSpaceLine<E extends AbstractSpaceLine> {
    Integer height = 20;

    public AbstractSpaceLine(Integer height) {
        this.height = height;
    }

    public AbstractSpaceLine() {
    }

    /**
     * default 20
     */
    public E height(Integer height) {
        this.height = height;
        return (E) this;
    }
}
