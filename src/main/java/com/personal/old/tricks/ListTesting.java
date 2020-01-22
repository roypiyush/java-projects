package com.personal.old.tricks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class HO {
    long[] a = new long[1024];
}

public class ListTesting {

    public static void main(String[] args) {
        main(LinkedList.class);
        main(ArrayList.class);
    }

    private static <T> void main(Class<T> t) {
        System.out.println();
        if (t.getName().contains("Linked")) {
            System.out.println("Using Type LinkedList");
        } else {
            System.out.println("Using Type ArrayList");
        }
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean() ;
        List<HO> objects1 = getList(t);
        int i = 0;
        int x = 120000;
        while (i++ < x) {
            objects1.add(new HO());
        }
        System.out.println(String.format("Total Memory after list1 %s bytes %s bytes", memBean.getNonHeapMemoryUsage().getUsed(), memBean.getHeapMemoryUsage().getUsed()));
        i = 0;
        List<HO> objects2 = getList(t);
        while (i++ < x) {
            objects2.add(new HO());
        }
        System.out.println(String.format("Total Memory after list2 %s bytes %s bytes", memBean.getNonHeapMemoryUsage().getUsed(), memBean.getHeapMemoryUsage().getUsed()));

        Iterable<Object> concat = Iterables.concat(objects1, objects2);
        System.out.println(String.format("After concating list1 and list2 1 %s bytes %s bytes", memBean.getNonHeapMemoryUsage().getUsed(), memBean.getHeapMemoryUsage().getUsed()));

        ImmutableList<Object> objects = ImmutableList.copyOf(concat);
        System.out.println(String.format("After creating immutable list %s bytes %s bytes", memBean.getNonHeapMemoryUsage().getUsed(), memBean.getHeapMemoryUsage().getUsed()));
        objects = ImmutableList.copyOf(objects);
        System.out.println(String.format("Again immutable list %s bytes %s bytes", memBean.getNonHeapMemoryUsage().getUsed(), memBean.getHeapMemoryUsage().getUsed()));
        objects.hashCode();
    }

    private static <T> List<HO> getList(Class<T> t) {
        if (t.getName().contains("Linked")) {
            return (List) (new LinkedList<>());
        }
        return new ArrayList<>();
    }
}
