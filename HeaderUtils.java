package com.anfu.cloud.utils;

import com.alibaba.fastjson.JSONObject;

public class HeaderUtils {

    public static String getRequestHeader(JSONObject request, String headerKey){
        JSONObject jsonObject = request.getJSONObject("header");
        return jsonObject.getString(headerKey);
    }

}
