package com.mservice.paygate.utils;

/**
 * Created by hainguyen on 4/12/17.
 */
public class Console {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void log(Object... args){
        System.out.println(ANSI_GREEN + getString(args) + ANSI_RESET);
    }
    public static void error(String... args){
        System.out.println(ANSI_RED + getString(args) + ANSI_RESET);
    }
    public static void warn(String... args){
        System.out.println(ANSI_YELLOW + getString(args) + ANSI_RESET);
    }
    public static void debug(String... args){
        System.out.println(ANSI_CYAN + getString(args) + ANSI_RESET);
    }
    public static String getString(Object... args){
        String text = "";
        for(Object arg : args){
            text += arg;
        }
        return text;
    }
    public static String getObject(String... args){
        String text = "";
        for(String arg : args){
            text += arg + " ";
        }
        return text;
    }
}
