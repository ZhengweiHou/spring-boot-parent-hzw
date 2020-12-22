/*
 * Decompiled with CFR.
 *
 * Could not load the following classes:
 *  com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello
 */
package com.hzw.learn.springboot.springbase.AOP.CGLIB;

import com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello;
import java.lang.reflect.Method;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Factory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class HzwHello$$EnhancerByCGLIB$$dc02cf00
        extends HzwHello
        implements Factory {
    private boolean CGLIB$BOUND;
    public static Object CGLIB$FACTORY_DATA;
    private static ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static Callback[] CGLIB$STATIC_CALLBACKS;
    private MethodInterceptor CGLIB$CALLBACK_0;
    private MethodInterceptor CGLIB$CALLBACK_1;
    private MethodInterceptor CGLIB$CALLBACK_2;
    private static Object CGLIB$CALLBACK_FILTER;
    private static Method CGLIB$sayHello$0$Method;
    private static MethodProxy CGLIB$sayHello$0$Proxy;
    private static Object[] CGLIB$emptyArgs;
    private static Method CGLIB$equals$1$Method;
    private static MethodProxy CGLIB$equals$1$Proxy;
    private static Method CGLIB$toString$2$Method;
    private static MethodProxy CGLIB$toString$2$Proxy;
    private static Method CGLIB$hashCode$3$Method;
    private static MethodProxy CGLIB$hashCode$3$Proxy;
    private static Method CGLIB$clone$4$Method;
    private static MethodProxy CGLIB$clone$4$Proxy;

    public HzwHello$$EnhancerByCGLIB$$dc02cf00() {
        HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00 = this;
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(hzwHello$$EnhancerByCGLIB$$dc02cf00);
    }

    static {
        try {
            HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$STATICHOOK1();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final boolean equals(Object object) {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            Object object2 = null;
            try {
                object2 = methodInterceptor.intercept(this, CGLIB$equals$1$Method, new Object[]{object}, CGLIB$equals$1$Proxy);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return object2 == null ? false : (Boolean)object2;
        }
        return super.equals(object);
    }

    public final String toString() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_2;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_2;
        }
        if (methodInterceptor != null) {
            try {
                return (String)methodInterceptor.intercept(this, CGLIB$toString$2$Method, CGLIB$emptyArgs, CGLIB$toString$2$Proxy);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return super.toString();
    }

    public final int hashCode() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_1;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_1;
        }
        if (methodInterceptor != null) {
            Object object = null;
            try {
                object = methodInterceptor.intercept(this, CGLIB$hashCode$3$Method, CGLIB$emptyArgs, CGLIB$hashCode$3$Proxy);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return object == null ? 0 : ((Number)object).intValue();
        }
        return super.hashCode();
    }

    protected final Object clone() throws CloneNotSupportedException {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_1;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_1;
        }
        if (methodInterceptor != null) {
            try {
                return methodInterceptor.intercept(this, CGLIB$clone$4$Method, CGLIB$emptyArgs, CGLIB$clone$4$Proxy);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return super.clone();
    }

    public Object newInstance(Callback callback) {
        throw new IllegalStateException("More than one callback object required");
    }

    public Object newInstance(Class[] arrclass, Object[] arrobject, Callback[] arrcallback) {
        HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00;
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$SET_THREAD_CALLBACKS(arrcallback);
        Class[] arrclass2 = arrclass;
        switch (arrclass.length) {
            case 0: {
                hzwHello$$EnhancerByCGLIB$$dc02cf00 = new HzwHello$$EnhancerByCGLIB$$dc02cf00();
                break;
            }
            default: {
                throw new IllegalArgumentException("Constructor not found");
            }
        }
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$SET_THREAD_CALLBACKS(null);
        return hzwHello$$EnhancerByCGLIB$$dc02cf00;
    }

    public Object newInstance(Callback[] arrcallback) {
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$SET_THREAD_CALLBACKS(arrcallback);
        HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00 = new HzwHello$$EnhancerByCGLIB$$dc02cf00();
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$SET_THREAD_CALLBACKS(null);
        return hzwHello$$EnhancerByCGLIB$$dc02cf00;
    }

    public void setCallback(int n, Callback callback) {
        switch (n) {
            case 0: {
                this.CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
                break;
            }
            case 1: {
                this.CGLIB$CALLBACK_1 = (MethodInterceptor)callback;
                break;
            }
            case 2: {
                this.CGLIB$CALLBACK_2 = (MethodInterceptor)callback;
                break;
            }
        }
    }

    public final void sayHello() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_1;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_1;
        }
        if (methodInterceptor != null) {
            try {
                Object object = methodInterceptor.intercept(this, CGLIB$sayHello$0$Method, CGLIB$emptyArgs, CGLIB$sayHello$0$Proxy);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return;
        }
        super.sayHello();
    }

    public void setCallbacks(Callback[] arrcallback) {
        this.CGLIB$CALLBACK_0 = (MethodInterceptor)arrcallback[0];
        this.CGLIB$CALLBACK_1 = (MethodInterceptor)arrcallback[1];
        Callback[] arrcallback2 = arrcallback;
        HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00 = this;
        this.CGLIB$CALLBACK_2 = (MethodInterceptor)arrcallback[2];
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] arrcallback) {
        CGLIB$THREAD_CALLBACKS.set(arrcallback);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] arrcallback) {
        CGLIB$STATIC_CALLBACKS = arrcallback;
    }

    public Callback getCallback(int n) {
        MethodInterceptor methodInterceptor;
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
        HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00 = this;
        switch (n) {
            case 0: {
                methodInterceptor = hzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$CALLBACK_0;
                break;
            }
            case 1: {
                methodInterceptor = hzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$CALLBACK_1;
                break;
            }
            case 2: {
                methodInterceptor = hzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$CALLBACK_2;
                break;
            }
            default: {
                methodInterceptor = null;
            }
        }
        return methodInterceptor;
    }

    public Callback[] getCallbacks() {
        HzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BIND_CALLBACKS(this);
        HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00 = this;
        return new Callback[]{this.CGLIB$CALLBACK_0, this.CGLIB$CALLBACK_1, this.CGLIB$CALLBACK_2};
    }

    final String CGLIB$toString$2() {
        return super.toString();
    }

    final void CGLIB$sayHello$0() {
        super.sayHello();
    }

    private static final void CGLIB$BIND_CALLBACKS(Object object) {
        block2: {
            Object object2;
            HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf00;
            block3: {
                hzwHello$$EnhancerByCGLIB$$dc02cf00 = (HzwHello$$EnhancerByCGLIB$$dc02cf00)object;
                if (hzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BOUND) break block2;
                hzwHello$$EnhancerByCGLIB$$dc02cf00.CGLIB$BOUND = true;
                object2 = CGLIB$THREAD_CALLBACKS.get();
                if (object2 != null) break block3;
                object2 = CGLIB$STATIC_CALLBACKS;
                if (CGLIB$STATIC_CALLBACKS == null) break block2;
            }
            Callback[] arrcallback = (Callback[])object2;
            HzwHello$$EnhancerByCGLIB$$dc02cf00 hzwHello$$EnhancerByCGLIB$$dc02cf002 = hzwHello$$EnhancerByCGLIB$$dc02cf00;
            hzwHello$$EnhancerByCGLIB$$dc02cf002.CGLIB$CALLBACK_2 = (MethodInterceptor)arrcallback[2];
            hzwHello$$EnhancerByCGLIB$$dc02cf002.CGLIB$CALLBACK_1 = (MethodInterceptor)arrcallback[1];
            hzwHello$$EnhancerByCGLIB$$dc02cf002.CGLIB$CALLBACK_0 = (MethodInterceptor)arrcallback[0];
        }
    }

    final int CGLIB$hashCode$3() {
        return super.hashCode();
    }

    static void CGLIB$STATICHOOK1() throws ClassNotFoundException {
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class<?> class_ = Class.forName("com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello$$EnhancerByCGLIB$$dc02cf00");
        Class<?> class_2 = Class.forName("java.lang.Object");
        Method[] arrmethod = ReflectUtils.findMethods(new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, class_2.getDeclaredMethods());
        CGLIB$equals$1$Method = arrmethod[0];
        CGLIB$equals$1$Proxy = MethodProxy.create(class_2, class_, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
        CGLIB$toString$2$Method = arrmethod[1];
        CGLIB$toString$2$Proxy = MethodProxy.create(class_2, class_, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
        CGLIB$hashCode$3$Method = arrmethod[2];
        CGLIB$hashCode$3$Proxy = MethodProxy.create(class_2, class_, "()I", "hashCode", "CGLIB$hashCode$3");
        CGLIB$clone$4$Method = arrmethod[3];
        CGLIB$clone$4$Proxy = MethodProxy.create(class_2, class_, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
        class_2 = Class.forName("com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello");
        CGLIB$sayHello$0$Method = ReflectUtils.findMethods(new String[]{"sayHello", "()V"}, class_2.getDeclaredMethods())[0];
        CGLIB$sayHello$0$Proxy = MethodProxy.create(class_2, class_, "()V", "sayHello", "CGLIB$sayHello$0");
    }

    final boolean CGLIB$equals$1(Object object) {
        return super.equals(object);
    }

    final Object CGLIB$clone$4() throws CloneNotSupportedException {
        return super.clone();
    }

    public static MethodProxy CGLIB$findMethodProxy(Signature signature) {
        String string = ((Object)signature).toString();
        switch (string.hashCode()) {
            case -508378822: {
                if (!string.equals("clone()Ljava/lang/Object;")) break;
                return CGLIB$clone$4$Proxy;
            }
            case 1535311470: {
                if (!string.equals("sayHello()V")) break;
                return CGLIB$sayHello$0$Proxy;
            }
            case 1826985398: {
                if (!string.equals("equals(Ljava/lang/Object;)Z")) break;
                return CGLIB$equals$1$Proxy;
            }
            case 1913648695: {
                if (!string.equals("toString()Ljava/lang/String;")) break;
                return CGLIB$toString$2$Proxy;
            }
            case 1984935277: {
                if (!string.equals("hashCode()I")) break;
                return CGLIB$hashCode$3$Proxy;
            }
        }
        return null;
    }
}
