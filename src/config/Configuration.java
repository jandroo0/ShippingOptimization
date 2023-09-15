package config;

import java.awt.*;

public class Configuration {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;



    // colors
    private static final Color textColor = Color.BLACK;

    // fonts
    private static final Font FONT_TITLE = new Font("Arial", Font.BOLD, 30);

    private static final Font textFont = new Font("Arial", Font.PLAIN, 20);






    public static int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public static int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }



    public static Font getFONT_TITLE() {
        return FONT_TITLE;
    }

    public static Font getTextFont() {
        return textFont;
    }

    public static Font getTextFont(int size) {
        return new Font(getTextFont().getName(), getTextFont().getStyle(), size);
    }


    public static Color getTextColor() {
        return textColor;
    }
}
