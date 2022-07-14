package com.bknife.base.json.serializer;

import com.bknife.base.json.JsonContants;

class JsonSerializeFeature {
    private boolean beatiful;
    private boolean serializeTransient;
    private boolean serializeIgnore;

    public JsonSerializeFeature(int feature) {
        beatiful = (feature & JsonContants.FEATURE_BEAUTIFUL) == JsonContants.FEATURE_BEAUTIFUL;
        serializeTransient = (feature
                & JsonContants.FEATURE_SERIALIZE_TRANSIENT) == JsonContants.FEATURE_SERIALIZE_TRANSIENT;
        serializeIgnore = (feature & JsonContants.FEATURE_SERIALIZE_IGNORE) == JsonContants.FEATURE_SERIALIZE_IGNORE;
    }

    public boolean isBeatiful() {
        return beatiful;
    }

    public boolean isSerializeTransient() {
        return serializeTransient;
    }

    public boolean isSerializeIgnore() {
        return serializeIgnore;
    }
}
