package com;

import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map1 = Maps.asMap(Sets.newHashSet("asd","dsa","dda","sad"), Functions.toStringFunction());


        //filtrowanie po klucz
        Map<String, String> filteredMap = map1.entrySet()
                .stream()
                .filter(map -> map.getKey().startsWith("d"))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        Map<String, String> filteredMap2= map1.entrySet()
                .stream()
                .filter(map -> "dsa".equals(map.getValue()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        filteredMap.entrySet()
                .stream()
                .forEach(map -> System.out.println(map));

        filteredMap2.entrySet()
                .stream()
                .forEach(map -> System.out.println(map));
    }
}
