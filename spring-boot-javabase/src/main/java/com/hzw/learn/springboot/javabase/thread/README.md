# 多线程
## 线程通信
### 传统线程通信
Object类提供的```wait()```、```notify()```、```notifyAll()```的三个方法必须由同步监视器对象类调用。
- ```wait()```
	使当前线程等待；
- ```notify()```
	唤醒在当前同步监视器上等待的单个线程（多个线程，只会唤醒其中一个）;
- ```notifyAll()```
	唤醒在当前同步监视器上等待的所有线程

### 使用Condition控制线程通信
若程序中不使用synchronized关键字来保证同步，而是使用Lock对象来保证同步，Lock代替了同步方法或同步代码块。
Condition类提供了如下三个方法
+ ```await()```
	类似于隐式同步监视器上的wait()方法，该方法有更多的变体方式；
+ ```signal()```
	当前线程放弃对改Lock对象的锁定后（使用await()方法），才可以执行被唤醒的线程；
+ ```signalAll()```
	唤醒此Lock对象上等待的所有线程；

### 使用阻塞队列BlockingQueue控制线程通信
BlockQueue提供了两个支持阻塞的方法
+ ```put(E e)```
	尝试把E元素放入BlockQueue中，如果该队列的元素已满，则阻塞该线程。
+ ```take()```
	尝试从BlockeQueue的头部取出元素，如果该队列的元素已空，则阻塞该线程。
**实现类**
+ ArrayBlockingQueue
+ LinkedBlockingQueue
+ PriorityBlockingQueue
+ SyschronousQueue
+ DelayQueue






