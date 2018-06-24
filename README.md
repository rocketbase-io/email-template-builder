

# email-template-builder

![logo](assets/logo.svg)

[![Build Status](https://travis-ci.org/rocketbase-io/email-template-builder.svg?branch=master)](https://travis-ci.org/rocketbase-io/email-template-builder)

Let this service build your html/text emails in a fluient manner. It doesn't try to reinvent the wheel - it simply extends the given [email-tempalte](https://github.com/mailgun/transactional-email-templates) of mailgun and backed it for java to use it for many purposes.

You can write your email content fluently and the template builder cares for the correct instyling, conversation to txt version etc...

For sending the email I can recommend the spring-boot-starter-mail...

## usage

```java
// generate html/text content
HtmlTextEmail htmlTextEmail = EmailTemplateBuilder.builder()
        .header("Warning: You're approaching your limit. Please upgrade.", new ColorStyle("ffffff", "ff9f00"))
        .addHtml("You have 1 <b>free report</b> remaining.")
        .addText("Add your credit card now to upgrade your account to a premium plan to ensure you don't miss out on any reports.")
        .addButton("Upgrade My Account", "http//upgrade", new ColorStyle("ffffff", "348eda"))
        .addText("Thanks for choosing Acme Inc.")
        .addFooter("<a href=\"http://unsubscribe\">Unsubscribe</a> from these alerts.", false)
        .copyright("rocketbase.io", "https://www.rocketbase.io")
        .build();

// sent email
MimeMessage message = emailSender.createMimeMessage();
MimeMessageHelper helper = new MimeMessageHelper(message,
        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
        StandardCharsets.UTF_8.name());
helper.setTo(to);
helper.setSubject(subject);
helper.setText(htmlTextEmail.getText(), htmlTextEmail.getHtml());
helper.setFrom(from);
emailSender.send(message);

```

**html:**

![sample](assets/mail-sample.png)

**text:**

```
Warning: You're approaching your limit. Please upgrade.
You have 1 free report remaining.
Add your credit card now to upgrade your account to a premium plan to ensure you don't miss out on any reports.
Upgrade My Account -> http//upgrade
Thanks for choosing Acme Inc.
You got this email because of...
2018 rocketbase.io -> https://www.rocketbase.io
```

