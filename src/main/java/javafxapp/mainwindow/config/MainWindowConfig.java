package javafxapp.mainwindow.config;

import javafxapp.config.Config;

public class MainWindowConfig {
    public static final String FIRST_FONT_COLOR = "#582";
    public static final String SECOND_FONT_COLOR = "#f00";

    public static final double HEADER_HEIGHT = 30;

    public static final double PANE_HEADER_HEIGHT = Config.WINDOW_HEIGHT - HEADER_HEIGHT;
    public static final double LEFT_PANE_WIDTH = Config.WINDOW_WIDTH * 0.2d;
    public static final double CENTER_PANE_WIDTH = Config.WINDOW_WIDTH * 0.4d;
    public static final double RIGHT_PANE_WIDTH = Config.WINDOW_WIDTH * 0.4d;
}
