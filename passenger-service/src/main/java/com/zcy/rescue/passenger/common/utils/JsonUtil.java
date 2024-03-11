package com.zcy.rescue.passenger.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * json工具类
 *
 * @author zouc
 * @ClassName: JsonUtil
 * @Description: json处理工具类
 * @date 2017年8月24日
 */
public class JsonUtil {

    public static final FastJsonSerializeConfig FAST_JSON_SERIALIZE_CONFIG = new FastJsonSerializeConfig();

    /**
     * 将json转对象
     *
     * @param json
     * @param dstClazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(final String json, Class<T> dstClazz) {
        return JSONObject.parseObject(json, dstClazz);
    }

    /**
     * 将json转对象
     *
     * @param json
     * @param dstClazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toObjectList(final String json, Class<T> dstClazz) {
        return JSONObject.parseArray(json, dstClazz);
    }

    /**
     * 将json转对象
     *
     * @param json
     * @param targetType
     * @param <T>
     * @return
     */
    public static <T> T toObject(final String json, Type targetType) {
        return JSONObject.parseObject(json, targetType);
    }

    /**
     * 将对象转Json
     *
     * @param srcClazz
     * @param <T>
     * @return
     */
    public static <T> String toJson(final T srcClazz) {
        return toJson(srcClazz, false);
    }

    /**
     * 将对象转换成json
     *
     * @param srcClazz    数据对象
     * @param isWriteNull 是否写入空值
     * @param <T>
     * @return
     */
    public static <T> String toJson(final T srcClazz, boolean isWriteNull) {
        if (isWriteNull) {
            return JSONObject.toJSONString(srcClazz, FAST_JSON_SERIALIZE_CONFIG, SerializerFeature.SkipTransientField, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
        } else {
            return JSONObject.toJSONString(srcClazz, FAST_JSON_SERIALIZE_CONFIG, SerializerFeature.SkipTransientField);
        }
    }

    /**
     * 读取json文件，返回json串
     *
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
//
//    public static void main(String[] args) {
//    	BaseInfoEntity baseInfoEntity=new BaseInfoEntity();
//    	baseInfoEntity.setCode("test").setCompanyName("dayi");
//    	String json = toJson(baseInfoEntity,true);
//    	System.out.println(json);
//    	System.out.println(baseInfoEntity);
//    }
}
