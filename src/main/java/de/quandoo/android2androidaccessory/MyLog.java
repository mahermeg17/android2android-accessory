package de.quandoo.android2androidaccessory;

import com.zc.logger.LogManager;

/**
 * Created by aat on 14/11/17.
 */

public class MyLog {
    private MyLog() {
    }

    public static void v(String tag, String text) {
        LogManager.getInstance().v(tag, text);
    }

    public static void d(String tag, String text) {
        LogManager.getInstance().d(tag, text);
    }

    public static void i(String tag, String text) {
        LogManager.getInstance().i(tag, text);
    }

    public static void w(String tag, String text) {
        LogManager.getInstance().w(tag, text);
    }

    public static void e(String tag, String text) {
        LogManager.getInstance().e(tag, text);
    }
}
