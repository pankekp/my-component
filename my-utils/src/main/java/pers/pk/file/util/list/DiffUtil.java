package pers.pk.file.util.list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DiffUtil {

    /**
     * return elements of list1 that are not in list2
     */
    public static <T> List<T> getDiffs(List<T> list1, List<T> list2) {

        Map<T, Integer> map = new HashMap<>();

        for (T t : list1) {
            map.put(t, 1);
        }

        for (T t : list2) {
            map.putIfAbsent(t, 2);
        }

        List<T> result = new LinkedList<>();

        for (T key : map.keySet()) {
            if (map.get(key) == 2) {
                result.add(key);
            }
        }

        return result;
    }
}
