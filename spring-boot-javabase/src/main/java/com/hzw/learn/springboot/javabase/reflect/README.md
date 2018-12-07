# 反射

`Object obj = new Student()`，这段代码中对于变量obj来说，Object是**编译时类型**，Student是**运行时类型**，
但是程序中通过变量obj并不能直接调用起运行是类型中的相关方法;<br/>
两个方案：1.强制类型转换 2.**反射**

## 获取Class对象

1. Class对象的forName方法：`Class.forName("[全类名]")`
2. 目标类的class属性：`Student.class`
3. 目标类对象的getClass()方法：`new Student().getClass()`

## 反射获取类信息

Class类中的getxxx()和getDeclaredxxx()方法区别：

1. getxxx()只获取public内容，getDeclaredxxx()获取所有内容；
1. getxxx()能获取父类或接口的资源，而Declared类方法获取的内容**不包含**目标类继承过来的内容。

例如getFields()和getDeclaredFields()的描述差异<br/>

* getFields():
    > Returns an array containing Field objects reflecting **all the accessible public fields** of the class or interface represented by this Class object.
* getDeclaredFields():

    > Returns an array of Field objects reflecting **all the fields** declared by the class or interface represented by this Class object. 
    This includes public, protected, default (package) access, and private fields, **but excludes inherited fields**.

## 方法参数反射(Java 8 新增)
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









