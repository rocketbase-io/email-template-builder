package io.rocketbase.mail;

import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.markdown.CssInlinerAttributeProvider;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.Getter;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;


public class MarkdownLine implements TemplateLine {

    private static Parser parser;
    private static HtmlRenderer renderer;

    static {
        List<Extension> extensions = Arrays.asList(ImageAttributesExtension.create(), StrikethroughExtension.create(), InsExtension.create());

        parser = Parser.builder()
                .extensions(extensions)
                .build();
        renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .attributeProviderFactory(new CssInlinerAttributeProvider(TbConfiguration.DEFAULT))
                .build();
    }

    @Getter
    private String markdown;
    @Getter
    private String html;


    public MarkdownLine(String markdown) {
        this.markdown = markdown;
        this.html = renderer.render(parser.parse(markdown));
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.MARKDOWN;
    }

    @Override
    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        throw new RuntimeException("not supported");
    }

    @Override
    public HtmlTextEmail build() {
        throw new RuntimeException("not supported");
    }
}
