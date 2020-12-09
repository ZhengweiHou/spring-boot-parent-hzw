## CGLIB
> 关键类：
> - Enhancer
> - MethodInterceptor

生成的动态代理类实际上是目标类的子类（区别proxy的方式）

``` java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(HzwHello.class);
enhancer.setCallback(new HzwMethodInterceptor());
HzwHello hello = (HzwHello) enhancer.create();
hello.sayHello();
```
hello对象实际上是动态代理对象，代理对象的反编译后，我们看到
```java
public class HzwHello$$EnhancerByCGLIB$$b2f16848
        extends HzwHello
        implements Factory {
    ...
    public final void sayHello() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            HzwHello$$EnhancerByCGLIB$$b2f16848.CGLIB$BIND_CALLBACKS(this);
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
    ...
}
```
`methodInterceptor`为实际方法代理，简单样例可以如下
```java
public class HzwMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前...");
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("执行后...");
        return object;
    }
}
```

## Proxy
> 关键类
> - java.lang.reflect.Proxy
> - java.lang.reflect.InvocationHandler

Pxoy代理的目标是接口，代理对象和我们要切面的目标实例本质上是同级关系（实现同一接口），
但我们可以人工让InvocationHandler持有要切面的实例对象从而完成AOP包装
```java
Class<?> clazz = Proxy.getProxyClass(HelloApi.class.getClassLoader(), new Class[]{HelloApi.class});
Constructor constructor = clazz.getConstructor(InvocationHandler.class);
HelloApi hello = (HelloApi) constructor.newInstance(
        new HzwInvocationHandler(new HzwHello())    // 自定义的InvocationHandler对象，其会持有我们的目标对象HzwHello
);
// 调用代理对象的目标方法，注意，此时hello并不是HzwHello本体
hello.sayHello();

或

HelloApi hello = (HelloApi) Proxy.newProxyInstance(
this.getClass().getClassLoader(),           // 类加载器
new Class[]{HelloApi.class},                // 目标接口
new HzwInvocationHandler(new HzwHello())    // 指定InvocationHandler（真正干活的）
);
hello.sayHello();
```
上面两种用法结果都一样

hello对象实际上是动态代理对象，代理对象的反编译后，我们看到（省略了部分代码）
```java
public final class $Proxy5
        extends Proxy
        implements HelloApi {
    ...
    private static Method m3;    
    // 反射获取Method对象
    m3 = Class.forName("com.hzw.learn.springboot.springbase.AOP.PROXY.HelloApi").getMethod("sayHello", new Class[0]);
    ...
    public $Proxy5(InvocationHandler invocationHandler) {
        super(invocationHandler);
        /*  父类构造器长这样，呼应下文的h对象
        protected Proxy(InvocationHandler h) {
            Objects.requireNonNull(h);
            this.h = h;
        }
         */
    }
    ...
    public final void sayHello() {
        try {
            // 调用invokerHandler对象invoke方法，传入目标Method对象
            this.h.invoke(this, m3, null);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
    ...
```
通过上面反编译出来的代码，对比CGLIB，CGLIB的代理对象是直接继承要切面的类从而直接封装。
Proxy的代理对象中没有我们要切面的对象，而是要通过编写InvocationHandler实现类时，手动注入要切面的目标类对象，在InvocationHandler中进行包装。