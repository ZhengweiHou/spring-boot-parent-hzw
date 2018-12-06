# 反射

`Object obj = new Student()`，这段代码中对于变量obj来说，Object是**编译时类型**，Student是**运行时类型**，
但是程序中通过变量obj并不能直接调用起运行是类型中的相关方法;<br/>
两个方案：1.强制类型转换 2.**反射**

## 获取Class对象

1. Class对象的forName方法：`Class.forName("[全类名]")`
2. 目标类的class属性：`Student.class`
3. 目标类对象的getClass()方法：`new Student().getClass()`

## 




