package io.rocketbase.commons.email.template;

import io.rocketbase.commons.email.template.ColorPalette.RgbColor;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ColorPaletteTest {

    @Test
    public void hex2rgb() {
        // given
        String inputOne = "#fff";
        String inputTwo = "#ffffff";
        String inputThree = "fff";
        String inputFour = "a";
        // when
        RgbColor colorOne = ColorPalette.hex2rgb(inputOne);
        RgbColor colorTwo = ColorPalette.hex2rgb(inputTwo);
        RgbColor colorThree = ColorPalette.hex2rgb(inputThree);
        RgbColor colorFour = ColorPalette.hex2rgb(inputFour);
        // then
        RgbColor color = new RgbColor(255, 255, 255);

        assertThat(colorOne, equalTo(color));
        assertThat(colorTwo, equalTo(color));
        assertThat(colorThree, equalTo(color));
        assertThat(colorFour, nullValue());
    }

    @Test
    public void isTextContrastBlack() {
        // given
        ColorPalette black = ColorPalette.BLACK;
        ColorPalette white = ColorPalette.WHITE;
        // when
        boolean blackContrastBlack = black.isTextContrastBlack();
        boolean whiteContrastBlack = white.isTextContrastBlack();
        // then
        assertThat(blackContrastBlack, equalTo(false));
        assertThat(whiteContrastBlack, equalTo(true));

    }

    @Test
    public void getHexCode() {
        // given
        RgbColor white = new RgbColor(255, 255, 255);
        RgbColor black = new RgbColor(0, 0, 0);
        // when
        String whiteHex = white.getHexCode();
        String blackHex = black.getHexCode();
        // then
        assertThat(whiteHex, equalTo("ffffff"));
        assertThat(blackHex, equalTo("000000"));
    }
}