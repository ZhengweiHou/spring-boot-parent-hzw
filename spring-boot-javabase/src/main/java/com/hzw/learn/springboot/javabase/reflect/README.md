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

