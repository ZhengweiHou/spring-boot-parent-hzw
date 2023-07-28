package com.hzw.grpc.fram.common.cache;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ReflectCache {


    /*----------- Class Cache ------------*/
    /**
     * String-->Class 缓存
     */
    static final ConcurrentMap<String, WeakHashMap<ClassLoader, Class>> CLASS_CACHE    = new ConcurrentHashMap<String, WeakHashMap<ClassLoader, Class>>();

    /**
     * Class-->String 缓存
     */
    static final ConcurrentMap<Class, String>                           TYPE_STR_CACHE = new ConcurrentHashMap<Class, String>();

    /**
     * 放入Class缓存
     *
     * @param typeStr 对象描述
     * @param clazz   类
     */
    public static void putClassCache(String typeStr, Class clazz) {
        CLASS_CACHE.putIfAbsent(typeStr, new WeakHashMap<ClassLoader, Class>());
        CLASS_CACHE.get(typeStr).put(clazz.getClassLoader(), clazz);
    }

    /**
     * 得到Class缓存
     *
     * @param typeStr 对象描述
     * @return 类
     */
    public static Class getClassCache(String typeStr) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            return null;
        } else {
            Map<ClassLoader, Class> temp = CLASS_CACHE.get(typeStr);
            return temp == null ? null : temp.get(classLoader);
        }
    }

    /**
     * 放入类描述缓存
     *
     * @param clazz   类
     * @param typeStr 对象描述
     */
    public static void putTypeStrCache(Class clazz, String typeStr) {
        TYPE_STR_CACHE.put(clazz, typeStr);
    }

    /**
     * 得到类描述缓存
     *
     * @param clazz 类
     * @return 类描述
     */
    public static String getTypeStrCache(Class clazz) {
        return TYPE_STR_CACHE.get(clazz);
    }


    /*----------- Cache Management ------------*/
    /**
     * 清理方法
     */
    static void clearAll() {
        CLASS_CACHE.clear();
        TYPE_STR_CACHE.clear();
    }

}
