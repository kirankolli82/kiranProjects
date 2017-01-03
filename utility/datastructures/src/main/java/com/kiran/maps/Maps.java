package com.kiran.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class Maps {

    public static <KeyT, ValueT> Map<KeyT, ValueT> createMapWith(Map<KeyT, ValueT> source) {
        Map<KeyT, ValueT> target = new HashMap<>();
        if (source != null) {
            target.putAll(source);
        }

        return target;
    }
}
