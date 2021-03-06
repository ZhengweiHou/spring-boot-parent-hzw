## 内存模型
### 1.主内存和工作内存
Java内存模型规定了所有的变量都存储在**主内存**中，主内存可以类比于物理硬件上的**内存**；每条线程有自己的**工作内存**，工作内存可以类比于**处理器高速缓存**，工作内存中保存当前线程使用到的变量的**主内存拷贝副本**。线程对变量的所有操作必须在工作内存中进行，不同的线程之间不能直接访问对方工作内存中的变量，线程之间变量值的传递需要通过主内存来完成。<br/>


线程、主内存、工作内存关系图<br/>
![线程、主内存、工作内存关系](https://raw.githubusercontent.com/ZhengweiHou/spring-boot-parent-hzw/master/spring-boot-jvm/src/main/resources/docs/%E7%BA%BF%E7%A8%8B%E3%80%81%E4%B8%BB%E5%86%85%E5%AD%98%E3%80%81%E5%B7%A5%E4%BD%9C%E5%86%85%E5%AD%98%E5%85%B3%E7%B3%BB.png)

从Java内存区域来看，**主内存**主要对应Java**堆**中的数据部分；**工作内存**对应与**栈**中的部分区域。<br/>
从分配来看，主内存直接对应于物理硬件的内存，工作内存可能会被虚拟机（甚至是硬件系统优化）优先存储于寄存器和高速缓存中。

### 2.主内存和工作内存间交互
**主内存和工作内存中内容拷贝和同步有以下8种操作完成**：

1. lock（锁定）
	主内存中的变量标识为线程独占状态
2. unlock（解锁）
	将线程独占状态的主内存变量解除锁定
3. read（读取）
	主内存变量值传输到线程工作内存中
4. load（载入）
	将read操作传输到工作内存中的值放入工作内存的变量副本中
5. use（使用）
	使用到变量值的字节码指令执行时，将工作内存中的变量值传递给执行引擎
6. assign（赋值）
	变量赋值字节码指令执行时，讲执行引擎输出的值赋给工作内存中的变量
7. store（存储）
	工作内存中变量值传给主内存
8. write（写入）
	将store操作传输到主内存中的变量值放入到主内存的变量中

**上述8种操作必须满足以下规则：**

- read和load、store和write两组操作不可单独出现（两个操作中间可以出现其他操作），不允许一个变量从主内存读取了但工作内存不接受，或者从工作内存发起回写了但主内存不接受
> 两对操作之间可以插入其他指令，如 read a、read b、load b、load a。
- 线程不可丢弃最近的assign操作，工作内存中变量改变后必须同步回主内存中
- 工作内存中未发生过assign操作的变量不可同步回主内存
- 新变量只能在主内存中产生,工作内存中不能直接使用未被初始化（load或assign）的变量，use和store操作之前，必须执行了load和assign操作
- 变量同一时刻只能被一个线程lock，线程可以执行多次lock，对应只有执行相同次数的unlock，变量才会被解锁
- 对一个变量执行lock操作时会清空工作内存中此变量的值，执行引擎使用这个变量前，需要重新执行load或assign操作初始化变量的值
- 未lock的变量不可执行unlock，线程不可unlock其他线程锁定的变量
- 变量unlock之前，必须先store和write同步回主内存中


### 3.volatile修饰的变量
两种特性

1. volatile修饰的变量对所有线程是立即可见的
	-使用变量时必须先从主内存刷新最新的值到工作内存中
	-修改变量后必须立刻同步回主内存中
即 **use的前的操作必须是read->load；assign后的操作必须是store->write**
> 这条特性只能保证变量可见性，变量的操作并不是原子性的，所以在不符合下两条规则的运算中，**仍需要通过加锁来保证原子性以保证线程安全**
>- 运算结果不依赖变量的当前值，或只有单一线程修改变量值。
>- 变量不需要与其他的状态变量共同参与不变约束。

2. volatile变量禁止机器级的指令重排序优化
	-变量前后的指令不会被重排序优化，保证代码的执行顺序与程序的顺序相同
  

### 4.long和double变量的特殊规则
java内存模型要求lock、unlock、read、load、use、assign、store、write这8个操作都具有原子性，但对64为的long和double却**允许**虚拟机将**没有被volatile修饰**的**64位**的数据的读写操作分为两次32位的操作（long和double的非原子性协定）。
>如果有多个线程共享一个未声明为volatile的long和double类型的变量，多个线程同时对变量读取和修改时，某些线程有可能会读取到一个代表了半个变量的数值，这个数值既不是原值，也不是其他线程修改值。

另一方面，java内存模型“**强烈建议**”虚拟机将这些操作实现为具有原子性的，目前各种虚拟机几乎都将64位数据的读写操作作为原子操作来处理，因此编写代码时一般不用特意将long和double变量声明为volatile。
	
	
### 5.原子性、可见性和有序性的实现
#### 原子性
- 基本数据类型的读写都是具有原子性的（例外：long和double的非原子性协定，见上）
- synchronize通过字节码指令monitorenter和monitorexit来隐式的使用lock和unloc操作来实现更大范围的原子性保证
#### 可见性
可见性指一个线程修改了共享变量的值，其他线程能立刻得知这个修改

- 变量修改后将新值同步会主内存，变量在使用前从主内存刷新变量值到工作内存。volatile变量和普通变量的区别是：volatile保证变量在使用前和修改后必须立即和主内存同步，普通变量不能保证。
- final保证变量在初始化后不会被修改，但在构造器中this引用逃逸时不能保证变量正确被访问。
```
public class final在this引用逃逸时的不安全 {
	public static void main(String[] args) {
		new A();
	}
}
class A{
	public final int a;
	public A() {
		new B(this);
		Thread.sleep(1l);
		a = 2;
	}
}
class B{
	public B(A a) {
		new Thread(() -> {
			System.out.println(a.a);	// 0
			Thread.sleep(50l);
			System.out.println(a.a);	// 2
		}).start();
	}
}

```
- synchronized规定，一个变量执行unlock前必须同步变量回主内存，此规则实现同步块的可见性。
#### 有序性
- volatile本身包含禁止指令重排序的语义。
- synchronized规定“一个变量在同一时刻只允许一条线程对其进行lock操作”，此规则决定了持有同一个锁的两个同步块只能串行处理。

	
### 6.先行发生原则
下列是Java内存模型一些先行发生的规则，如果两个操作之间的关系不在此列，且无法被下列规则推导出来的话，则它们没有顺序性保障，虚拟机可以对其随意进行重排序。

- **程序次序规则**	
	同一个线程内按照程序控制流（分之、循环等）顺序，书写在前的操作先行发生与后书写的。
- **管程锁定规则**
	unlock先行发生与后面（时间先后）对**同一个锁**的lock操作。
- **volatile规则**
	volatile变量的写操作先行发生与后面（时间先后）对**同一个volatile变量**的读操作。
- **线程启动规则**
	Thread对象的start()方法先行发生与此线程的每一个动作。
- **线程终止规则**
	线程中的所有动作都先行发生于该线程的终止检测。
- **线程中断规则**
	线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过Thread.interrupted()方法检测到是否有中断发生。
- **对象终结规则**
	一个对象的初始化完成先行于它的finalize()方法的开始。
- **传递性**
	操作A先于B，B先于C，则A先于C
	
	
	
	
	
	
	
	
	
	
	
	
