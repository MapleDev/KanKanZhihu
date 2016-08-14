package com.xznn.kankanzhihu.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MapleDev
 * @time 16/08/04  8:58
 * @desc ${TODD}
 */
public class JsonUtil {
    /**
     * @author MapleDev
     * @time 16/07/19  21:22
     * @desc 将 JSON String 转化成 List<T>
     */
    public static <T> List<T> json2List(String json, Class<T> classOfT) {
        // 先反序列化出ArrayList<JsonObject>
        List<T> lst = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement elem : array) {
            lst.add(new Gson().fromJson(elem, classOfT));
        }
        return lst;
    }
}
