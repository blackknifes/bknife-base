package com.bknife.base.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类静态工具类
 */
public abstract class Classs {

    private static void scanClasses(List<Class<?>> classList, Class<?> superClass, ClassLoader classLoader,
            String packageName) throws Exception {
        String packageResource = packageName.replace(".", "/");
        URL url = classLoader.getResource(packageResource);

        try {
            File root = new File(url.toURI());
            scanClassesFromFile(classList, superClass, root, packageName);
        } catch (URISyntaxException e) {
            throw e;
        } catch (Exception e) {
            String fullPath = url.getFile();
            if (fullPath.startsWith("file:/"))
                fullPath = fullPath.substring(6);
            String jarPath = fullPath.substring(0, fullPath.indexOf("!"));
            JarFile jarFile = new JarFile(jarPath);
            scanClassesFromJar(classList, superClass, jarFile, packageName);
        }
    }

    private static void scanClassesFromJar(List<Class<?>> classList, Class<?> superClass, JarFile jarFile,
            String packageName) throws ClassNotFoundException {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith(".class")) {
                entryName = entryName.replaceAll("/", ".");
                entryName = entryName.substring(0, entryName.length() - 6);
                try {
                    Class<?> clazz = Class.forName(entryName);
                    if (superClass == null || superClass.isAssignableFrom(clazz))
                        classList.add(clazz);
                } catch (Throwable e) {
                }
            }
        }
    }

    private static void scanClassesFromFile(List<Class<?>> classList, Class<?> superClass, File root,
            String packageName)
            throws Exception {
        for (File child : Objects.requireNonNull(root.listFiles())) {
            String name = child.getName();
            if (child.isDirectory()) {
                scanClassesFromFile(classList, superClass, child, packageName + "." + name);
            } else if (name.endsWith(".class")) {
                String className = (packageName.isEmpty() ? packageName : packageName + ".")
                        + name.substring(0, name.length() - 6);
                Class<?> theClass = Class.forName(className);
                if (superClass == null || superClass.isAssignableFrom(theClass))
                    classList.add(theClass);
            }
        }
    }

    /**
     * 扫描包下所有类
     * 
     * @param packageName
     * @param superClass
     * @param classLoader
     * @return
     * @throws Exception
     */
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