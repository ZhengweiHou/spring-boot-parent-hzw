package com.hzw.grpc.fram.serializer.protostuff;

import com.hzw.grpc.fram.serializer.protostuff.delegate.SqlDateDelegate;
import com.hzw.grpc.fram.serializer.protostuff.delegate.TimeDelegate;
import com.hzw.grpc.fram.serializer.protostuff.delegate.TimestampDelegate;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.RuntimeEnv;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName WrapperUtils
 * @Description ProtoStuff序列化对特殊类型封装的工具类
 * @Author houzw
 * @Date 2023/9/11
 **/
public class WrapperUtils {

    private static final Set<Class<?>> WRAPPER_SET = new HashSet<>();

    static {

        if (RuntimeEnv.ID_STRATEGY instanceof DefaultIdStrategy) {
            ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY).registerDelegate(new TimeDelegate());
            ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY).registerDelegate(new TimestampDelegate());
            ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY).registerDelegate(new SqlDateDelegate());
        }

        WRAPPER_SET.add(Map.class);
        WRAPPER_SET.add(HashMap.class);
        WRAPPER_SET.add(TreeMap.class);
        WRAPPER_SET.add(Hashtable.class);
        WRAPPER_SET.add(SortedMap.class);
        WRAPPER_SET.add(LinkedHashMap.class);
        WRAPPER_SET.add(ConcurrentHashMap.class);

        WRAPPER_SET.add(List.class);
        WRAPPER_SET.add(ArrayList.class);
        WRAPPER_SET.add(LinkedList.class);

        WRAPPER_SET.add(Vector.class);

        WRAPPER_SET.add(Set.class);
        WRAPPER_SET.add(HashSet.class);
        WRAPPER_SET.add(TreeSet.class);
        WRAPPER_SET.add(BitSet.class);

        WRAPPER_SET.add(StringBuffer.class);
        WRAPPER_SET.add(StringBuilder.class);

        WRAPPER_SET.add(BigDecimal.class);
        WRAPPER_SET.add(Date.class);
        WRAPPER_SET.add(Calendar.class);
        WRAPPER_SET.add(Time.class);
        WRAPPER_SET.add(Timestamp.class);
        WRAPPER_SET.add(java.sql.Date.class);

        WRAPPER_SET.add(Wrapper.class);

    }

    /**
     * Determine if the object needs wrap
     *
     * @param clazz object type
     * @return need wrap
     */
    public static boolean needWrapper(Class<?> clazz) {
        return WrapperUtils.WRAPPER_SET.contains(clazz) || clazz.isArray() || clazz.isEnum()|| clazz.isPrimitive();
    }

    /**
     * Determine if the object needs wrap
     *
     * @param obj object
     * @return need wrap
     */
    public static boolean needWrapper(Object obj) {
        return needWrapper(obj.getClass());
    }
}
