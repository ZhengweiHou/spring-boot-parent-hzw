# 输入/输出
## Nio
### Buffer
#### 概述

**Java Nio 的核心类库多路复用器Selector是基于epoll的多路复用技术实现。**
Buffer像一个数组，它可以保存多个类型相同的数据。Buffer是一个抽象类，其最常用的子类是ByteBuffer，它可以在底层字节数组上进行get/set操作。除了ButeBuffer之外，对应于其他基本数据类型（boolean除外）都有对应的Buffer类：```CharBuffer```、```ShortBuffer```、```IntBuffer```、```LongBuffer```、```FloatBuffer```、```DoubleBuffer```;

上面这些Buffer类，除了ByteBuffer之外，它们都采用相同或相似的方法来管理数据;

#### 创建

```static XxxBuffer allocate(int capacity)```
	创建一个容量为capacity的XxxBuffer对象;
	
#### Buffer中重要概念
- **容量(capacity)**
	<br/>缓冲区的容量表示该Buffer的最大数据容量。不可能为负值，创建后不能改变。
- **界限(limit)**
	<br/>第一个不应该被读出或者写入的缓冲区位置索引。位于limit后(包括limit位置)的数据不可以被读写。
- **位置(position)**
	<br/>下一个可以被读或写的缓冲区位置索引。初始值为0(第一个位置的索引)。
- **标记(mark)**
	<br/>类似于传统IO流中的mark，Buffer可以直接将position定位到mark处。
	
> 上述值满足如下关系<br/>
```0 <= mark <= position <= limit <= capacity```
	
#### put()/get()
put()和get()方法，用于向Buffer中放入/取出数据，Buffer支持单个数据访问和批量数据访问（以数组为参数）;

- 相对（Relative）：从Buffer的当前position处开始读取或写入数据，然后将position按处理元素个数增加；
- 绝对（Absolute）：直接根据索引处理数据就，position不会被影响；

#### 其他常用方法
- ```int capacity()```返回capacity大小;
- ```int limit()```返回limit位置;
- ```int position()```返回position位置;
- ```Buffer limit(int newLt)```重新设置limit值，返回具有新limit的Buffer对象;
- ```Buffer position(int newPs)```重新设置position，返回position被修改的Buffer对象;
- ```Buffer mark()```设置标记，标记位置为当前position位置;
- ```boolean hasRemaining()```position和limit之间是否有可处理元素;
- ```int remaining()```position和limit之间元素个数;
- ```Buffer reset()```设置position为mark;
- ```Buffer rewind()```设置position=0，mark会被取消;
- ```Buffer flip()```设置limit = position，封印position和capacity之间的区域;
	

