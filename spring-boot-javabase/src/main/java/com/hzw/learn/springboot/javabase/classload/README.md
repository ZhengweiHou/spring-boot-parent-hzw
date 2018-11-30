# 类加载机制
## 类加载步骤
加载、连接、初始化

### 加载
将类的class文件读入内存，并为之创建一个java.lang.Class对象；

- 从本地文件系统加载class文件
- 从Jar包加载class文件
- **通过网络加载class文件**
- **动态编译Java源文件，并加载**

### 连接
连接阶段负责把类的二进制数据合并到JRE中。

- 验证

    验证阶段用于检验被加载的类是否有正确的内部结构，并和其他类协调一致；

- 准备

    类准备阶段负责为类的类变量分配内存，并设置默认初始值；

- 解析

    将类的二进制数据中的符号引用替换成直接引用。

### 初始化

## 类加载器
### 分类
- 根类加载器 Bootstrap ClassLoacer

    引导（原始或根）类加载器，负责加载Java的核心类。
> // 获取类加载起所加载的全部URL数组<br/>
> URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

- 扩展类加载器 Extension ClassLoader 

    扩展类加载器，负责加载JRE的扩展目录（%JAVA_HOME%/jre/lib/ext
    或者由java.ext.dirs系统属性制定的目录）中JAR包的类；

- 系统类加载器 System ClassLoader

    系统（应用）类加载器，它负责在JVM启动时加载来自java命令-classpath选项、java.class.path系统属性，
    或CLASSPATH环境变量所制定的JAR包和类路径。<br/>
    程序可以通过`ClassLoader`的静态方法`getSystemClassLoader()`来获取系统类加载器。
    
### 类加载机制
1. 阿斯蒂芬
    1. 地方哈士大夫
2. 阿斯蒂芬


#12. 流程图
```flow
st=>start: 开始
op=>operation: My Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
```






