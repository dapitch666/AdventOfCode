package org.anne.aoc2016;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashSet;
import java.util.Set;

public class Toto {
    public static void main(String[] args) {
        String packageName = "org.anne.aoc.2016";
        Set<Class> classes = findAllClassesUsingReflectionsLibrary(packageName);
        System.out.println("Toto");
    }

    public static Set<Class> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }
}
