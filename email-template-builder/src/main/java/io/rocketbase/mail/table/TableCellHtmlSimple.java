package io.rocketbase.mail.table;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TableCellHtmlSimple implements TableCellHtml {

    final String html;
    final String text;
}
