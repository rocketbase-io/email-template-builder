package io.rocketbase.mail.markdown;

import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.config.base.TbFont;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.*;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;

@RequiredArgsConstructor
public class CssInlinerAttributeProvider implements AttributeProviderFactory {

    private final TbConfiguration configuration;

    @Override
    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
        return (node, tagName, attributes) -> {
            if (node instanceof Link) {
                attributes.put("style", "font-family: " + configuration.getFont().getFamily() +
                        "; color: " + configuration.getText().getLinkColor());
            }
            if (node instanceof Image) {
                attributes.put("style", "border: none");
            } else if (node instanceof Paragraph ||
                    node instanceof OrderedList ||
                    node instanceof BulletList) {
                attributes.put("style", "font-family: " + configuration.getFont().getFamily() +
                        "; margin: " + configuration.getText().getMargin() +
                        "; font-size: " + configuration.getText().getSize() +
                        "; line-height: " + configuration.getText().getLineHeight() +
                        "; color: " + configuration.getText().getColor());
            } else if (node instanceof Heading heading) {
                TbFont font = null;
                switch (heading.getLevel()) {
                    case 1:
                        font = configuration.getFont().getH1();
                        break;
                    case 2:
                        font = configuration.getFont().getH2();
                        break;
                    default:
                        font = configuration.getFont().getH3();
                }
                attributes.put("style", "font-family: " + configuration.getFont().getFamily() +
                        "; font-size: " + font.getSize() +
                        "; color: " + font.getColor());
            }
        };
    }
}
