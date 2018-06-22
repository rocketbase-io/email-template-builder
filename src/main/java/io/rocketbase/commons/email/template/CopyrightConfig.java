package io.rocketbase.commons.email.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CopyrightConfig {

    private final String name;
    private final String url;
    private final Integer year;

}
