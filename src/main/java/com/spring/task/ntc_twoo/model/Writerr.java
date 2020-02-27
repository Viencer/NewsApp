package com.spring.task.ntc_twoo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class Writerr {

    public static void write(List<Articles> tasks) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("", tasks);
        try (FileWriter file = new FileWriter("save.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
