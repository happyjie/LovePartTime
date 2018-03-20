package com.lib.llj.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by chenyk on 2016/5/12.
 * describe the function:Gson解析工具类
 */
public class GsonUtil {
    public static Gson gson = new Gson();

    /**
     * 实体类转化为json
     *
     * @param bean
     * @return
     */
    public static String bean2json(Object bean) {
        return gson.toJson(bean);
    }

    /**
     * @param <T>
     * @param json
     * @param type 转化的目标实体类
     * @return
     */
    public static <T> T json2bean(String json, Type type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

}
