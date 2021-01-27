### singleton 单例模式
- 饥饿模式
- 懒加载
- 注册式
### prototype 原型模式
1. 克隆
    - 浅克隆
只拷贝对象本身（包括对象中的基本变量），不会拷贝对象中引用指向的其他对象。Cloneable接口的Clone方法，默认就是浅拷贝;
    - 深克隆 
一种方式是通过将目标对象序列化后，再反序列化成一个新对象，这样能深度拷贝对象，包括对象本身和引用对象;