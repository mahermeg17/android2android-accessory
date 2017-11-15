package de.quandoo.android2androidaccessory;

/**
 * Created by aat on 13/11/17.
 */


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Maher.MEGADMINI on 07/08/2017.
 */
public class ATTUtils {
    private static final String TAG = "ATTUtils";

    /**
     * read file from external storage and return it as an input stream      *      * @param fullPath full path      * @return file content as InputStream
     */
    public static InputStream inputStreamFromStorage(String fullPath) {
        File file = new File(fullPath);
        try {
            FileInputStream is = new FileInputStream(file);
            return is;
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static Object loadObjectFromJsonFile(String path, Class<?> classType) {
        Object jsonObject;
        try {
            String jsonData = loadFromStorage(path);
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("EEE, dd MMM yyyy, HH:mm").create();
            jsonObject = gson.fromJson(jsonData, classType);
        } catch (Exception e) {
            jsonObject = null;
            Log.e(TAG, e.getMessage());
        }
        return jsonObject;
    }

    public static String convertObjectToJson(Object o) {
        String jsonString;
        try {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("EEE, dd MMM yyyy, HH:mm").create();
            jsonString = gson.toJson(o);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            jsonString = "";
        }
        return jsonString;
    }

    public static boolean saveObjectIntoJsonFile(String jsonData, String fullPath) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(fullPath)));
            outputStreamWriter.write(jsonData);
            outputStreamWriter.close();
            return true;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private static String loadFromStorage(String fullPath) {
        String ret;
        try {
            InputStream inputStream = inputStreamFromStorage(fullPath);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
                return ret;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}


