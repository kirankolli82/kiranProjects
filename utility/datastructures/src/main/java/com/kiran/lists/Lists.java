package com.kiran.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class Lists {

    public static <ListTypeT> List<ListTypeT> createListWith(List<ListTypeT> source) {
        List<ListTypeT> target = new ArrayList<>();
        if (source != null) {
            target.addAll(source);
        }
        return target;
    }


}
