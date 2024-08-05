package com.feuji.utaf.helpers;


import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadJsonFile {

    public static JSONObject readJsonFile(String filePath) throws FileNotFoundException {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);

            JSONTokener jsonTokener = new JSONTokener(fileReader);

            return new JSONObject(jsonTokener);
        } finally {
            if (fileReader != null){
                try{
                    fileReader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
