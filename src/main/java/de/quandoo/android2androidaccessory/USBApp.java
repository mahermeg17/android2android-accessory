package de.quandoo.android2androidaccessory;

import android.app.Application;

import com.zc.logger.LogManager;
import com.zc.logger.config.Config;
import com.zc.logger.config.LogManagerConfig;
import com.zc.logger.config.OnFileFullListener;
import com.zc.logger.format.DefaultFormatter;
import com.zc.logger.log.ConsoleLogger;
import com.zc.logger.log.FileLogger;
import com.zc.logger.model.LogModule;
import com.zc.logger.util.LogUtil;

import java.io.File;

/**
 * Created by aat on 14/11/17.
 */

public class USBApp extends Application {
    private static final boolean DEBUG = true;
    private static final String TAG = "USBApp";

    @Override
    public void onCreate() {
        super.onCreate();
        initLogManager();// init LogManager before you use it to print log
        LogUtil.writeLogs(DEBUG);
    }

    private void initLogManager() {
        LogManagerConfig config = new LogManagerConfig.Builder(this)
                .minLevel(Config.LEVEL_VERBOSE) // default, lowest log level is v
                .maxLevel(Config.LEVEL_ASSERT) // default, highest log level is a
                .enableModuleFilter(!DEBUG) // if false, all logs are printed; if true, only added module logs are printed
                .addModule(new LogModule(USBApp.TAG))
                .writeLogs(true) // display Logger internal logs
                .setFileLevel(10) // create at most 10 log files
                .setFileSize(100000) // create every log file smaller than 100000Byte
                .addOnFileFullListener(new OnFileFullListener() {
                    @Override
                    public void onFileFull(File file) {
                        // called in ui thread when one log file is full, you can upload it to your log server
                    }
                })
                .formatter(new DefaultFormatter()) // log format is the same as adb logcat
                .addLogger(new FileLogger()) // print log to file
                .addLogger(new ConsoleLogger()) // print log to console
                .build();
        LogManager.getInstance().init(config);
    }

}
