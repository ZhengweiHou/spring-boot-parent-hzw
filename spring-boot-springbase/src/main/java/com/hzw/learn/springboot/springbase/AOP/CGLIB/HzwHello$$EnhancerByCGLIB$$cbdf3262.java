/*
 * Decompiled with CFR.
 *
 * Could not load the following classes:
 *  com.hzw.learn.springboot.springbase.AOP.CGLIB.HelloApi
 *  com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello
 */
package com.hzw.learn.springboot.springbase.AOP.CGLIB;

import com.hzw.learn.springboot.springbase.AOP.CGLIB.HelloApi;
import com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello;
import java.lang.reflect.Method;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Factory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class HzwHello$$EnhancerByCGLIB$$cbdf3262
        extends HzwHello
        implements HelloApi,
        Factory {
    private boolean CGLIB$BOUND;
    public static Object CGLIB$FACTORY_DATA;
    private static ThreadLocal CGLIB$THREAD_CALLBACKS = null;
    private static Callback[] CGLIB$STATIC_CALLBACKS = new Callback[0];
    private MethodInterceptor CGLIB$CALLBACK_0;
    private static Object CGLIB$CALLBACK_FILTER;
    private static Method CGLIB$sayHello$0$Method = null;
    private static MethodProxy CGLIB$sayHello$0$Proxy = null;
    private static Object[] CGLIB$emptyArgs = new Object[0];
    private static Method CGLIB$equals$1$Method = null;
    private static MethodProxy CGLIB$equals$1$Proxy = null;
    private static Method CGLIB$toString$2$Method = null;
    private static MethodProxy CGLIB$toString$2$Proxy = null;
    private static Method CGLIB$hashCode$3$Method = null;
    private static MethodProxy CGLIB$hashCode$3$Proxy = null;
    private static Method CGLIB$clone$4$Method = null;
    private static MethodProxy CGLIB$clone$4$Proxy = null;

    public HzwHello$$EnhancerByCGLIB$$cbdf3262() {
        HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262 = this;
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(hzwHello$$EnhancerByCGLIB$$cbdf3262);
    }

    static {
        try {
            HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$STATICHOOK1();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final boolean equals(Object object) {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
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
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
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
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
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
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
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

    public Object newInstance(Callback[] arrcallback) {
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$SET_THREAD_CALLBACKS(arrcallback);
        HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262 = new HzwHello$$EnhancerByCGLIB$$cbdf3262();
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$SET_THREAD_CALLBACKS(null);
        return hzwHello$$EnhancerByCGLIB$$cbdf3262;
    }

    public Object newInstance(Callback callback) {
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$SET_THREAD_CALLBACKS(new Callback[]{callback});
        HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262 = new HzwHello$$EnhancerByCGLIB$$cbdf3262();
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$SET_THREAD_CALLBACKS(null);
        return hzwHello$$EnhancerByCGLIB$$cbdf3262;
    }

    public Object newInstance(Class[] arrclass, Object[] arrobject, Callback[] arrcallback) {
        HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262;
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$SET_THREAD_CALLBACKS(arrcallback);
        Class[] arrclass2 = arrclass;
        switch (arrclass.length) {
            case 0: {
                hzwHello$$EnhancerByCGLIB$$cbdf3262 = new HzwHello$$EnhancerByCGLIB$$cbdf3262();
                break;
            }
            default: {
                throw new IllegalArgumentException("Constructor not found");
            }
        }
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$SET_THREAD_CALLBACKS(null);
        return hzwHello$$EnhancerByCGLIB$$cbdf3262;
    }

    public void setCallback(int n, Callback callback) {
        switch (n) {
            case 0: {
                this.CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
                break;
            }
        }
    }

    public final void sayHello() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
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

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] arrcallback) {
        CGLIB$THREAD_CALLBACKS.set(arrcallback);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] arrcallback) {
        CGLIB$STATIC_CALLBACKS = arrcallback;
    }

    public void setCallbacks(Callback[] arrcallback) {
        Callback[] arrcallback2 = arrcallback;
        HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262 = this;
        this.CGLIB$CALLBACK_0 = (MethodInterceptor)arrcallback[0];
    }

    public Callback getCallback(int n) {
        MethodInterceptor methodInterceptor;
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
        switch (n) {
            case 0: {
                methodInterceptor = this.CGLIB$CALLBACK_0;
                break;
            }
            default: {
                methodInterceptor = null;
            }
        }
        return methodInterceptor;
    }

    public Callback[] getCallbacks() {
        HzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BIND_CALLBACKS(this);
        HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262 = this;
        return new Callback[]{this.CGLIB$CALLBACK_0};
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

    final void CGLIB$sayHello$0() throws Throwable {
        super.sayHello();
    }

    final boolean CGLIB$equals$1(Object object) {
        return super.equals(object);
    }

    final int CGLIB$hashCode$3() {
        return super.hashCode();
    }

    final Object CGLIB$clone$4() throws CloneNotSupportedException {
        return super.clone();
    }

    final String CGLIB$toString$2() {
        return super.toString();
    }

    static void CGLIB$STATICHOOK1() throws ClassNotFoundException {
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class<?> class_ = Class.forName("com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello$$EnhancerByCGLIB$$cbdf3262");
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

    private static final void CGLIB$BIND_CALLBACKS(Object object) {
        block2: {
            Object object2;
            block3: {
                HzwHello$$EnhancerByCGLIB$$cbdf3262 hzwHello$$EnhancerByCGLIB$$cbdf3262 = (HzwHello$$EnhancerByCGLIB$$cbdf3262)object;
                if (hzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BOUND) break block2;
                hzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$BOUND = true;
                object2 = CGLIB$THREAD_CALLBACKS.get();
                if (object2 != null) break block3;
                object2 = CGLIB$STATIC_CALLBACKS;
                if (CGLIB$STATIC_CALLBACKS == null) break block2;
            }
//            hzwHello$$EnhancerByCGLIB$$cbdf3262.CGLIB$CALLBACK_0 = (MethodInterceptor)((Callback[])object2)[0];
        }
    }
}
