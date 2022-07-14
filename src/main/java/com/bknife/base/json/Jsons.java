package com.bknife.base.json;

import com.bknife.base.json.serializer.JsonToString;

public final class Jsons implements JsonContants {
    private Jsons() {
    }

    public static String toString(Object object, int feature) {
        return JsonToString.toString(object, feature);
    }

    public static String toString(Object object) {
        return toString(object, 0);
    }
}
