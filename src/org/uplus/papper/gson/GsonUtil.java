package org.uplus.papper.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by youjia.zyj on 2014/5/29.
 */
public class GsonUtil {
    private volatile static Gson gson;

    public static Gson getGson() {
        synchronized (GsonUtil.class) {
            if (gson == null) {
                gson = createGson();
            }
        }
        return gson;
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(
                        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
