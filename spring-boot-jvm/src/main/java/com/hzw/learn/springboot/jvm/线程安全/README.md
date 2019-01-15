## 线程安全
> 线程安全：当多个线程访问一个对象时，如果不用考虑这些线程在运行时环境下的调度和交替执行，也不需要进行额外的同步，或者在调用方进行任何其他的协调操作，调用这个对象的行为都可以获得正确的结果，那这个对象是线程安全的。

### 数据的安全级别
1. 不可变
    Java语言中，如果共享数据是一个基本数据类型，那么只要在定义时使用final关键字修饰它就可以保证 它是不可变的。如果共享数据是一个对象，那么需要保证对象的行为不会对其状态产生任何影响，最简单的就是把对象中带有状态的变量声明为final，构造函数结束后，它就是不可变的。
2. 绝对线程安全
    "不管运行时环境如何，调用者都不需要任何额外的同步措施"，这是一个很严格的定义，Java API中标注线程安全的类，大多是不是绝对线程安全的。
    >例如`java.util.Vector`,其所有方法都被修饰成同步的，但当`get()`和`remove()`方法被多个线程同时调用时往往也会出现数组越界问题。
3. 相对线程安全
    通常意义上线程安全就是相对线程安全，对象的单独操作是线程安全的，调用时不用做额外的保障措施。
    >例如`Vector`就是相对线程安全的，其只能保证`remove()`、`get()`方法在单独使用时是安全的，但多方法同时使用功能时却无法保证真正的线程安全。
4. 线程兼容
    本身不是线程安全的，但可以通过同步手段保证对象在并发中安全使用的类称为线程兼容。大部分的类都是属于线程兼容的。
5. 线程对立
    线程对立是指无论调用端是否采取同步措施，都无法在多线程环境中使用的代码。
    >例如Java中已声明废弃的`Thread`类的`suspend()`和`resume()`方法,两个线程同时拥有同一个线程对象，一个尝试去中断线程，一个去尝试恢复线程，并发进行时，无论调用时是否进行了同步，目标线程都是存在死锁风险的。
* Vector案例(Vector是相对线程安全的不是绝对线程安全的)：
```java
 private static Vector<Integer> vector = new Vector<Integer>();
 public static void main(String[] args) {
  for (int n = 0; n < 100; n++) {
   for (int i = 0; i < 10; i++) {
    vector.add(i);
   }
   Thread removeThread = new Thread(new Runnable() {
    public void run() {
     for (int i = 0; i < vector.size(); i++) {
      vector.remove(i);
     }
    }
   });
   Thread getThread = new Thread(new Runnable() {
    public void run() {
     for (int i = 0; i < vector.size(); i++) {
      vector.get(i);
     }
    }
   });
   removeThread.start();
   getThread.start();
   while (Thread.activeCount() > 20);
  }
 }
```
结果：
>Exception in thread "Thread-5" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 8
 at java.util.Vector.get(Vector.java:751)
 at com.hzw.learn.springboot.jvm.线程安全.绝对安全和相对安全$2.run(绝对安全和相对安全.java:29)
 at java.lang.Thread.run(Thread.java:748)

 如果一个线程恰好下错误的时间里删除了一个元素，导致序号i不再可用，恰巧另一个线程使用i去访问数组就会抛出ArrayIndexOutOfBoundsException。若要保证这段代码正确执行需进行如下改造
改造成线程安全：
```java
   Thread removeThread = new Thread(new Runnable() {
    public void run() {
     synchronized (vector) {
        for (int i = 0; i < vector.size(); i++) {
            vector.remove(i);
        }
     }
    }
   });
   Thread getThread = new Thread(new Runnable() {
    public void run() {
     synchronized (vector) {
        for (int i = 0; i < vector.size(); i++) {
            vector.get(i);
        }
     }
    }
   });
```
### 线程安全的实现
#### 1. 互斥同步
互斥同步已称为阻塞同步（Blocking Synchronization）
同步：指多个线程并发访问共享数据时，保证共享数据在同一个时刻只被一个线程使用。
互斥：实现同步的一种手段，临界区（CriticalSection）、互斥量（Mutex）和信号量（Semaphore）都是主要的互斥实现方式。
##### synchronized
- synchronized指定了参数对象，则这个对象的reference就是锁对象；修饰实例方法时，对应对象实例为锁对象；修饰类方法时，对应Class对象为锁对象；
- synchronized编译后会在同步块前后形成`monitorenter`和`monitorexit`两个字节码指令；monitorenter指令执行时，尝试获取对象锁，若对象未锁定或当前线程已拥有锁时，将锁的计数器加1；monitorenter指令执行时，锁计数器减1，当计数器为0时，锁就释放；  
- synchronized同步块对同一条线程来说是可重入的，不会出现线程自己锁死自己的问题；同步块在已经入的线程执行完之前，会阻塞后面其他线程的进入。
- Java的线程是映射到操作系统原生线程上的，线程的阻塞和唤醒需要操作系统来帮助完成，需要从`用户态`转换到`核心态`中，而状态转换要消耗很多的处理器时间。所以synchronized是Java中一个`重量级操作`，在必要情况下才使用。
- 为避免频繁切入到核心态中，虚拟机会进行一些优化，例如线程阻塞前加入`自旋`过程；
##### ReentranLock
重入锁ReentrantLock比synchronized增加了一些高级功能：
- 等待可中断
    当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情；
    `lock.lockInterruptibly(); // 以可以响应中断的方式加锁`
    `thread.interrupt(); // 线程标记中断`
- 锁申请等待限时
    线程可以通过`ReentrantLock.tryLock()`或`ReentrantLock.tryLock(long timeout, TimeUtil unit) `方法进行一次限时的锁等待，线程指定参数时间内(无参为立刻返回)获取到锁返回true，若未能获取到锁返回false，**当前线程继续执行而不会一直锁等待**；
    **例：**`lock.tryLock(1, TimeUnit.SECONDS)    // 等待1秒`
- 公平锁
    指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁；非公平锁，在锁释放时所有等待的线程有相同机会获得锁；ReentranLock默认情况下也是非公平的，但是可以通过boolean值的构造函数创建`公平锁`
```java
public ReentrantLock(boolean fair) { // 构造器
    sync = fair ? new FairSync() : new NonfairSync();
}
```
- 锁绑定多个条件（配合Conditiond使用）???

案例代码：[ReentrantLock绑定多Condition.java](https://github.com/ZhengweiHou/spring-boot-parent-hzw/blob/master/spring-boot-jvm/src/main/java/com/hzw/learn/springboot/jvm/%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8/%E9%87%8D%E5%85%A5%E9%94%81_ReentranLock/ReentrantLock%E7%BB%91%E5%AE%9A%E5%A4%9ACondition.java)

一个ReentrantLock对象可以同时绑定多个Conditond对象    
    `Condition condition = lock.newCondition()`

[重入锁：ReentrantLock详解](https://blog.csdn.net/Somhu/article/details/78874634) - Somhu|csdn
#### 2. 非阻塞同步
互斥同步属于悲观并发策略，其主要问题是线程阻塞和唤醒带来的性能消耗；而非阻塞同步属于乐观并发策略，但其需要**操作**和**冲突检测**两个步骤**具有原子性**，如果使用互斥同步来保证原子性那就没意思了，所以这种原子性只能**依靠硬件指令集**来完成。

硬件指令要保证多次操作行为只通过一条处理器指令完成，以保证多个操作的原子性，这类常用指令有：

* 测试并设置    Test-and-Set
* 获取并增加    Fetch-and_Increment
* 交换    Swap
* **比较并交换    Compare-and-Swap (CAS)**
* 加载链接/条件存储    Load-Linked/Store-Conditional (LL/SC)

CAS指令有3个操作数：**内存位置V**、**旧的预期值A**、**新值B**，指令执行逻辑：**当且仅当V符合A时，使用B更新V的值，否则不更新，无论是否更新V的值，都返回V的旧值**。

JDK1.5之后，Java程序支持使用CAS操作，由sun.misc.Unsafe类的compareAndSwapInt()和compareAndSwapLong()等方法包装提供。
因为Unsafe获取实例的方法getUnsafe()中限制了只有根类加载器加载的Class才能访问，所以**用户程序不能直接调用**(可以使用反射)，但是可以通过其他使用了Unsafe的Java API来间接调用，如java.util.concurrent（J.U.C）包下的各种atomic-原子类；
例如：AtomicInteger类里的方法
```java
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
    public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
```
案例代码：
```java
 public static void main(String[] args){
  int COUNT = 20;
  for(int i = 0 ; i < COUNT ; i++) {
   new Thread(new OrdinaryTest()).start();
   new Thread(new AutoicTest()).start();
  }
  System.out.println("O_race = " + OrdinaryTest.race);
  System.out.println("A_race = " + AutoicTest.race);
 }

class OrdinaryTest implements Runnable{
 public static int race = 0;
 @Override
 public void run() {
  for(int i = 0; i < 10000 ; i++) {    ++race;    }
 }
}

class AutoicTest implements Runnable{
 // 使用AtomicInteger代替int
 public static AtomicInteger race = new AtomicInteger(0);
 @Override
 public void run() {
  for(int i = 0; i < 10000 ; i++) {     race.incrementAndGet();   } // 类似于++race  
 }
}
```
 >测试结果<br/>
 O_race = 195109<br/>
 A_race = 200000

使用AtomicInteger代替int后，程序输出了正确的结果，其得益于incrementAndGet()方法的原子性
Java8该方法的实现如下：
```java
    public final int incrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }
```
Java6方法的实现如下：
```java
    public final int incrementAndGet() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
                return next;
        }
    }
```
J.U.C包下还有其他大量的这种
原子类（`AtomicBoolean`、`AtomicLong`等等）
原子操作方法（`getAndDecrement()` = i--、`decrementAndGet()` = --i、 `getAndIncrement()` = i++等等）

#### 3. 无同步方案
* 可重入代码
如果一个方法，它的返回结果是可以预测的，只要输入了相同的数据，就都能返回相同的结果，那它就是可重入代码，满足可重入性要求，**可重入的代码都是线程安全的**，线程安全的代码不一定是可重入的。
* 线程本地存储
如果一个变量被某个线程独享，那么可以通过java.lang.ThreadLocal类来实现线程本地存储的功能。每个线程的Thread对象中都有一个ThreadLocalMap对象，这个对象存储了一组以ThreadLocal.threadLocalHashCode为键，以本地线程变量为值的K-V值对，ThreadLocal对象就是当前线程的ThreadLocalMap的访问入口，每一个ThreadLocal对象都包含了一个独一无二的threadLocalHashCode值，使用这个值就可以在线程K-V值对中找会对应的本地线程变量。






