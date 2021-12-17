package com.pratham.admin.modalclasses;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class JSONArrayToString {
    @TypeConverter
    public static String JSONArrayToString(JsonArray stringArray) {
        return stringArray == null ? null : stringArray.toString();
    }

    @TypeConverter
    public static JsonArray stringToJSONArray(String strings) {
        JsonArray jsonArray = null;
        if (strings != null) {
            try {
                Gson gson = new Gson();
                jsonArray = gson.fromJson(strings, JsonArray.class);
                jsonArray.toString();
            } catch (Exception e) {

            }
        }
        return jsonArray;
    }
}
