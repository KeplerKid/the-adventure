package com.bigeauofn.adventure.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListUtility {


    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<>(c);
        java.util.Collections.sort(list);
        return list;
    }
}
