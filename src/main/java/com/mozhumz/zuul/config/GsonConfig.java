//package com.mozhumz.zuul.config;
//
//import java.lang.reflect.Type;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParseException;
//import com.google.gson.JsonPrimitive;
//import com.google.gson.JsonSerializationContext;
//import com.google.gson.JsonSerializer;
//
//@Slf4j
//public class GsonConfig implements JsonSerializer, JsonDeserializer {
//
//
//    public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return new JsonPrimitive(sdf.format(arg0));
//    }
//
//    @Override
//    public Date deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            date = sdf.parse(arg0.getAsJsonPrimitive().getAsString());
//        } catch (ParseException e) {
//            log.error("UtilDateGSON deserialize error.", e);
//        }
//
//        return date;
//    }
//
//
//    @Override
//    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return new JsonPrimitive(sdf.format(o));
//    }
//}
