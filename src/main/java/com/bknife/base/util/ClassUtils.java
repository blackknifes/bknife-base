package com.bknife.base.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class ClassUtils {

    private static void scanClasses(List<Class<?>> classList, Class<?> superClass, ClassLoader classLoader,
            String packageName) throws Exception {
        String packageResource = packageName.replace(".", "/");
        URL url = classLoader.getResource(packageResource);
        File root = new File(url.toURI());
        scanClassesInner(classList, superClass, root, packageName);
    }

    private static void scanClassesInner(List<Class<?>> classList, Class<?> superClass, File root, String packageName)
            throws Exception {
        for (File child : Objects.requireNonNull(root.listFiles())) {
            String name = child.getName();
            if (child.isDirectory()) {
                scanClassesInner(classList, superClass, child, packageName + "." + name);
            } else if (name.endsWith(".class")) {
                String className = packageName + "." + name.replace(".class", "");
                Class<?> theClass = Class.forName(className);
                if (superClass == null || superClass.isAssignableFrom(theClass))
                    classList.add(theClass);
            }
        }
    }

    public static Collection<Class<?>> scanClasses(String packageName, Class<?> superClass, ClassLoader classLoader)
            throws Exception {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        scanClasses(classList, superClass, classLoader, packageName);
        return classList;
    }

    public static Collection<Class<?>> scanClasses(String packageName, Class<?> superClass) throws Exception {
        return scanClasses(packageName, superClass, ClassLoader.getSystemClassLoader());
    }

    public static Collection<Class<?>> scanClasses(String packageName) throws Exception {
        return scanClasses(packageName, null);
    }

    public static Collection<Class<?>> scanClasses() throws Exception {
        return scanClasses(".");
    }
}