package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
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

    private static List<Extension> extensions;
    private static Parser parser;

    static {
        extensions = Arrays.asList(ImageAttributesExtension.create(), StrikethroughExtension.create(), InsExtension.create());

        parser = Parser.builder()
                .extensions(extensions)
                .build();
    }

    private final EmailTemplateConfigBuilder builder;
    @Getter
    private String markdown;
    @Getter
    private String html;


    public MarkdownLine(String markdown) {
        this(null, markdown);
    }

    public MarkdownLine(EmailTemplateConfigBuilder builder, String markdown) {
        this.builder = builder;
        this.markdown = markdown;

        HtmlRenderer htmlRenderer = HtmlRenderer.builder()
                .extensions(extensions)
                .attributeProviderFactory(new CssInlinerAttributeProvider(builder != null ? builder.getConfiguration() : TbConfiguration.newInstance()))
                .build();

        this.html = htmlRenderer.render(parser.parse(markdown));
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.MARKDOWN;
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        if (builder == null) {
            throw new RuntimeException("not supported");
        }
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        if (builder == null) {
            throw new RuntimeException("not supported");
        }
        return builder.build();
    }
}
