package org.rehab.app.utils;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is used for maintain the log values and write in the sdcard..
 *
 * @author and15031989
 */
public class Logger {

    private static String TAG = "TAG";
    private static boolean logstatus = false;
    private static boolean isFileWrite = false;
    private static String logFileName = "log_file";

    /**
     * Used to set the file name which is write in the sdcard.
     *
     * @param fileName
     */
    public static void setFileName(@NonNull String fileName) {
        logFileName = fileName;
    }

    /**
     * Set the log status.
     *
     * @param isShow true:-> Need to show else false:-> not show
     */
    public static void setLogStatus(boolean isShow) {
        logstatus = isShow;
    }

    /**
     * Set the log write into file status
     *
     * @param fileWriteStatus true:-> Need to write else false:-> not write
     */
    public static void setFileWriteStatus(boolean fileWriteStatus) {
        isFileWrite = fileWriteStatus;
    }

    /**
     * Set the tag value for the log.
     * @param tagValue
     */
    public static void setTag(String tagValue) {
        TAG = tagValue;
    }

    public static void w(final String TAG, final String s) {
        if (logstatus) {
            Log.w(TAG, ":->" + s);
        }
        appendLog(s);

    }

    public static void w(final String s) {
        if (logstatus) {
            Log.w(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void warn(final String s) {
        if (logstatus) {
            Log.w(TAG, ":->" + s);
        }
        appendLog(s);
    }


    public static void i(final String TAG, final String s) {
        if (logstatus) {
            Log.i(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void i(final String s) {
        if (logstatus) {
            Log.i(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void info(final String s) {
        if (logstatus) {
            Log.i(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void info(final String s, final Throwable throwable) {
        if (logstatus) {
            Log.i(TAG, s, throwable);
        }
        appendLog(s);
    }

    public static void e(final String TAG, final String s) {
        if (logstatus) {
            Log.e(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void e(final String s) {
        if (logstatus) {
            Log.e(TAG, ":->" + s);
        }
    }

    public static void error(final String s) {
        if (logstatus) {
            Log.e(TAG, ":->" + s);
        }
    }

    public static void error(final String tag, final String s) {
        if (logstatus) {
            Log.e(tag, ":->" + s);
        }
        appendLog(s);
    }


    public static void error(final Throwable throwable) {
        if (logstatus) {
            Log.e(TAG, null, throwable);
        }
    }

    public static void error(final String s, final Throwable throwable) {
        if (logstatus) {
            Log.e(TAG, s, throwable);
        }
    }


    public static void d(final String TAG, final String s) {
        if (logstatus) {
            Log.w(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void d(final String s) {
        if (logstatus) {
            Log.w(TAG, ":->" + s);
        }
        appendLog(s);
    }

    public static void debug(final String s) {
        if (logstatus) {
            Log.w(TAG, ":->" + s);
        }
        appendLog(s);
    }


    /**
     * Method for writing the code in the sdcard.
     *
     * @param text Text to need to write.
     */
    private static void appendLog(String text) {
        if (isFileWrite) {
            File logFile = new File(Environment.getExternalStorageDirectory() + "/" + logFileName + ".txt");
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                //BufferedWriter for performance, true to set append to file flag
                BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
                buf.append(text);
                buf.newLine();
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
