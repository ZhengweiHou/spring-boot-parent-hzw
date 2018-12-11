# 反射

`Object obj = new Student()`，这段代码中对于变量obj来说，Object是**编译时类型**，Student是**运行时类型**，
但是程序中通过变量obj并不能直接调用起运行是类型中的相关方法;<br/>
两个方案：1.强制类型转换 2.**反射**

## 反射查看类信息

### 获取Class对象

1. Class对象的forName方法：`Class.forName("[全类名]")`
2. 目标类的class属性：`Student.class`
3. 目标类对象的getClass()方法：`new Student().getClass()`

### 反射获取类信息

`Method`方法、`Constructor`构造器、`Field`成员变量、`Annotation`注解<br/>
#### Class类中的getxxx()和getDeclaredxxx()方法区别：

1. getxxx()只获取public内容，getDeclaredxxx()获取所有内容；
1. getxxx()能获取父类或接口的资源，而Declared类方法获取的内容**不包含**目标类继承过来的内容。

例如getFields()和getDeclaredFields()的描述差异<br/>

* getFields():
    > Returns an array containing Field objects reflecting **all the accessible public fields** of the class or interface represented by this Class object.
* getDeclaredFields():

    > Returns an array of Field objects reflecting **all the fields** declared by the class or interface represented by this Class object. 
    This includes public, protected, default (package) access, and private fields, **but excludes inherited fields**.

#### Method、Constructor和Field拥有一个共同父类：AccessibleObject
权限访问检查-Field为例：<br/>
`Field.isAccessible()`使用字段时是否进行访问权限检查<br/>
`Field.setAccessible(Boolean flag)`设置是否进行访问权限检查<br/>
> **true**为**取消**权限检查;falss为进行权限检查。

### 方法参数反射(Java 8 新增)
- `Executable`:Java8新增的抽象基类，其有两个子类如下
    * `Constructor`
    * `Method`
    
Executable提供了如下两个方法获取方法的参数信息：
> 1) int getParameterCount():获取方法的形参个数；<br/>
> 2) Parameter[] getParameters():获取方法的所有形参。

Parameter:代表方法或构造器的一个参数。
> parameter.getModifiers(); 修饰符<br/>
> parameter.getName(); 形参名<br/>
> parameter.getParameterizedType(); 获取带泛型的形参类型<br/>
> parameter.getType(); 获取形参类型<br/>
> parameter.isNamePresent(); 返回类的class文件中是否包含了方法的形参名信息<br/>
> parameter.isVarArgs(); 判断该参数是否为可变参数

> **注意：**<br/>
> javac命令编译成的class文件默认不包含方法的形参名信息，调用`isNamePerm()`方法会返回false，调用`getName()`方法也得不到该参数的形参名。
> 如果希望javac命令编译保留形参信息，那么需要指定命令参数`-parameters`。


## 反射操作类
程序可以，
通过Method对象来执行方法，
通过Constructor对象来调用构造器创建实例，
通过Field对象操作成员变量
### 创建对象
1. `Class.newInstance()`调用默认构造器实例对象
2. `Constructor.newInstance(Object... initargs)`通过构造器对象调用对应构造器实例对象

```
Class<ClassTest> clazz = ClassTest.class;
clazz.newInstance();
clazz.getConstructor(String.class).newInstance("呵呵");
```
### 方法调用
反射执行方法API<br/>
`Object Method.invoke(Object [执行主体], Object... args)`

```
Method method = clazz.getMethod("publicMethod", String.class);
// 设置访问时不进行权限检查
if(!method.isAccessible())
    method.setAccessible(true);
// 执行方法
method.invoke(new ClassTest(), "hzw");
```

### 访问成员变量
Field类提供了如下两组方法来访问成员变量值

- getXxx(Object obj):<br/>
    获取obj对象的该成员变量的值。此处的Xxx对应8种基本类型，如果该成员变量的类型是引用类型，则取消get后面的Xxx。
- setXxx(Object obj, Xxx val):</br>
    将obj对象的该成员变量值设置成val，Xxx含义同上。

示例代码：
```
ClassTest classTest = new ClassTest();
Field field = clazz.getField("publicStr");
// 设置访问时不进行权限检查
if(!field.isAccessible())
    field.setAccessible(true);
// 取值
field.get(classTest);
// 赋值
field.set(classTest, "hzw");
```

### 操作数组
数组操作反射关键类：<br/>
**java.lang.reflect.Array**
示例：
```
Object arrayTest = Array.newInstance(String.class, 2);
Array.set(arrayTest, 0, "hhh");
Array.set(arrayTest, 1, "zzz");
System.out.println(""
    + "   0:" + Array.get(arrayTest, 0)
    + "   1:" + Array.get(arrayTest, 1)
);
```

## 动态代理:Proxy 和 InvocationHandler
### Proxy
Proxy是所有动态代理类的父类，他可以为程序中一个或多个接口动态的生成实现类。<br/>

1. 创建动态代理类<br/>
`static Class<?> getProxyClass(ClassLoader loader, Class<?>... inrerfaces)`:<br/>
创建一个动态代理类所对应的Class对象，该代理类将实现interface所指定的多个接口。第一个ClassLoader参数指定生成动态代理类的类加载器；
2. 创建动态代理实例<br/>
`static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)`:<br/>
直接创建一个动态代理对象，该代理对象的实现类实现了interface指定的系列接口，执行代理对象的每个方法时都会被替换执行**InvocationHandler**对象的invoke方法。

### InvocationHandler
InvocationHandler由代理对象的调用处理程序实现的接口，代理对象执行方法时，会替换成调用InvocationHandler对象的invoke方法。<br/>
invoke()方法签名：`Object invoke(Object proxy, Method method, Object[] args)`<br/>
参数解释：

- proxy: 动态代理对象
- methd: 正在执行的方法
- args: 正在执行方法的传入的实参

### 简单示例
```
public class ProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        // 创建InvocationHandler对象
        InvocationHandler handler = new MyInvocationHandler();
        // 使用制定的InvocationHandler生成一个动态代理对象
        // Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class[] {Person.class});
        // System.out.println(proxyClass.getName());
        // Constructor<?> ctor = proxyClass.getConstructor(InvocationHandler.class);
        // Object temp = ctor.newInstance(handler);
        // 下行为简化写法
        Object temp = Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class} , handler);
        // 方法调用方式-反射
        Method walkMethod = Person.class.getMethod("walk");
        walkMethod.invoke(temp);
        Method sayHelloMethod = Person.class.getMethod("sayHello", String.class);
        sayHelloMethod.invoke(temp,"HZW");
        // 方法调用方式-代理对象调用
        Person p = (Person)temp;
        p.walk();
        p.sayHello("hzw");
    }
}

interface Person {
    void walk();
    void sayHello(String name);
}

class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理对象名：" + proxy.getClass().getName());
        System.out.println("执行方法名：" + method.getName());
        System.out.println("执行方法参数：" + args);
        return null;
    }
}
```

### 动态代理和AOP

## 反射和泛型
### 泛型和Class类
JDK5以后，Class类增加了泛型功能，以限制Class类，例如，String.class的类型，实际上是Class<String>。
如果Class对应的类暂时未知，则使用Class<?>。
使用Class<T>泛型可以避免强制类型转换，如下案例：

**使用泛型改造数组实例化方法**
```
class HzwArray{
    public static <T> T[] newInstance(Class<T> clazz, int length) {
        return (T[]) Array.newInstance(clazz, length);
    }
}
```
**使用时可以避免强制类型转换**
```
// 通过反射创建String数组
String[] array1 = (String[])Array.newInstance(String.class, 5);
// 使用泛型，避免使用时的强制类型转换
String[] array2 = HzwArray.newInstance(String.class, 5);
```

### Field（属性类）和Parameter（参数类）获取泛型信息
#### 参数化类型 ParameterizedType
ParameterizedType是Type的子类，可以通过该类对象获取到类型中的泛型信息

- Type ParameterizedType.getRawType()<br/>
获取原始类型,返回 Type
- Type[] ParameterizedType.getActualTypeArguments<br/>
获取参数化类型的泛型参数数组,返回Type[]

#### 获取泛型类型
`Type = Field.getGenericType()`<br/>
获取属性类的类型对象（Type），**当属性类携带泛型时，此方法返回的Type可强转成ParameterizedType**，否则强转会异常。

`Type Parameter.getParameterizedType()`
获取参数类的类型对象（Type），**当参数类携带泛型时，此方法返回的Type可强转成ParameterizedType**，否则强转会异常。

#### 反射获取泛型信息案例代码

**案例代码：**
```
public class Field和Parameter获取泛型信息 {
    public static void main(String[] args) throws Exception {
        Field field1 = HzwClass.class.getField("arg1");
        Field field2 = HzwClass.class.getField("arg2");
        Parameter param1 = (Parameter) Array.get(HzwClass.class.getMethod("methodTest", Map.class, String.class).getParameters(), 0);
        Parameter param2 = (Parameter) Array.get(HzwClass.class.getMethod("methodTest", Map.class, String.class).getParameters(), 1);
        
        Type ft1 = field1.getGenericType();
        Type ft2 = field2.getGenericType();
        Type pt1 = param1.getParameterizedType();
        Type pt2 = param2.getParameterizedType();
        
        System.out.println("is ParameterizedType:\n"
            + "  " + (ft1 instanceof ParameterizedType) // true
            + "  " + (ft2 instanceof ParameterizedType) // false
            + "  " + (pt1 instanceof ParameterizedType) // true
            + "  " + (pt2 instanceof ParameterizedType) // false
        );
        
        ParameterizedType ppt = (ParameterizedType)pt1;
        Type rawType = ppt.getRawType();
        Type[] arguments = ppt.getActualTypeArguments();
        System.out.println("原始类型：" + rawType);
        System.out.println("泛型信息：");
        Arrays.asList(arguments).stream().forEach(type -> {
            System.out.print(type + "  ");
            System.out.println(type instanceof ParameterizedType);
        });
    }
}

class HzwClass{
    public Map<String, String> arg1;
    public String arg2;
    public void methodTest(Map<String, Map<String,Integer>> arg1, String arg2) {};
}
```

**输出内容：**
is ParameterizedType:
  true  false  true  false
原始类型：interface java.util.Map
泛型信息：
class java.lang.String  false
java.util.Map<java.lang.String, java.lang.Integer>  true
 */
