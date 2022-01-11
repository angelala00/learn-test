package com.anfu.cloud.utils;

import com.alibaba.fastjson.JSONObject;

public class ResultCheckUtils {

    public static boolean same(JSONObject jsonObject, JSONObject extResult) {
        int org_errno = jsonObject.getInteger("errno");
        int des_errno = extResult.getInteger("errno");
        return org_errno==des_errno;
    }

}
