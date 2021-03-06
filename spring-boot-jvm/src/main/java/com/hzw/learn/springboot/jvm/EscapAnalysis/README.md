## 逃逸分析
### 逃逸分析的基本行为就是分析对象动态作用域：
- 方法逃逸：当一个对象在方法中定义后，可能被外部方法引用，例如作为另一个方法的参数传递到其他方法中，此称为方法逃逸；
- 线程逃逸：对象可能被外部线程访问到，例如赋值给类变量或能被其他线程访问的实例变量，称为线程逃逸；
### 基于对象的逃逸分析，jvm可以对该对象或变量进行一些高效优化
1. **栈分配(Stack Allocation)**：
    对象的内存空间一般创建在Java堆中，但如果可以确定一个方法中定义的对象不会逃逸到当前方法之外，那么可以将该对象的内存空间分配到方法栈上，随方法的栈帧出栈该对象被销毁，从而降低gc系统的压力。
2. **同步消除(Synchronization Elimination)**:
    当逃逸分析能够确定变量不会出现线程逃逸，那这个变量被施加的同步措施就可以消除掉，从而减少线程同步过程的耗时。
3. **标量替换(Scalar Replacement)**:
    > **标量(scalar)**：一个数据无法被分解成更小数据表示，如int、long等数值类型和referrence(引用)类型等Java虚拟机中的原始数据类型，都不能再进一步分解，它们可以称为标量；
    > **聚合量(aggregate)**：如果一个数据可以继续分解，那它称为聚合量，Java中的对象就是典型的聚合量。
    将一个Java对象拆散，根据程序访问情况，将其使用到的成员变量恢复为原始类型来访问就叫做标量替换；<br/>
    逃逸分析证明一个可分解的对象不会被外部访问，那程序真正执行时可能不会创建该对象，而改为直接创建它的若干个被这个方法使用到的成员变量来代替;<br/>
    对象拆分后，除了可以让对象的成员变量在栈上分配读写之外，还可以为后续的进一步优化手段创建条件。
### 逃逸分析相关jvm参数
- 手动开启逃逸分析 `-XX:+DoEscapeAnalysis`
- 查看分析结果 `-XX:+PrintEscapeAnalysis`
在有逃逸分析支持后
- 开启同步消除 `-XX:+EliminateLocks`
- 开启标量替换 `-XX:+EliminateAllocations`
- 查看标量的替换情况 `-XX:+PrintEliminateAllocations`
### 案例演示
- 示例代码
```
public static int test(int age) {
    User usr = new User(age);
    return usr.getAge()
}
```
说明： User对象的作用域不会逃逸出test方法，当启用逃逸分析，标量替换优化会在栈上分配对象，而不会生成 User对象，减低GC的压力
```
// 通过方法调用创建2000000个该方法内对象
for(int i=0 ; i <2000000 ; i++) {
    test(i);
  }
```
- 差异对比
    1. 关闭逃逸分析
        jvm参数：`-Xmx3G -Xmn2G -server -XX:-DoEscapeAnalysis`
        效果：
        ```
        houzw@sirius:~$ jps -v
        26232 StackAllocation -Xmx3G -Xmn2G -XX:+DoEscapeAnalysis -Dfile.encoding=UTF-8
        26204 StackAllocation -Xmx3G -Xmn2G -XX:-DoEscapeAnalysis -Dfile.encoding=UTF-8
        houzw@sirius:~$ jmap -histo 26204 | grep User
        5: 2000000 32000000 com.hzw.learn.springboot.jvm.EscapAnalysis.User
        ```
        **User对象一个不少的分配在堆中**
    2. 开启逃逸分析
        jvm参数：`-Xmx3G -Xmn2G -server -XX:+DoEscapeAnalysis`
        效果：
        ```
        houzw@sirius:~$ jps -v
        26232 StackAllocation -Xmx3G -Xmn2G -XX:+DoEscapeAnalysis -Dfile.encoding=UTF-8
        26204 StackAllocation -Xmx3G -Xmn2G -XX:-DoEscapeAnalysis -Dfile.encoding=UTF-8
        houzw@sirius:~$ jmap -histo 26232 | grep User
        5: 58974 943584  com.hzw.learn.springboot.jvm.EscapAnalysis.User
        ```
        **逃逸分析开启后，大部分对象被进行标量替换优化了**
### 其他资料
[浅谈HotSpot逃逸分析](https://www.jianshu.com/p/20bd2e9b1f03) - 简书|占小狼
