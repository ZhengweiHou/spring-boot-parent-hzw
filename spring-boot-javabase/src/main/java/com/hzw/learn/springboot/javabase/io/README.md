# 输入/输出
## Nio
### Buffer
**概述**

Buffer像一个数组，它可以保存多个类型相同的数据。Buffer是一个抽象类，其最常用的子类是ByteBuffer，它可以在底层字节数组上进行get/set操作。除了ButeBuffer之外，对应于其他基本数据类型（boolean除外）都有对应的Buffer类：```CharBuffer```、```ShortBuffer```、```IntBuffer```、```LongBuffer```、```FloatBuffer```、```DoubleBuffer```;

上面这些Buffer类，除了ByteBuffer之外，它们都采用相同或相似的方法来管理数据;

**创建**

```static XxxBuffer allocate(int capacity)```
	创建一个容量为capacity的XxxBuffer对象;
	
**Buffer中重要概念**
- 容量(capacity)
	<br/>缓冲区的容量表示该Buffer的最大数据容量。不可能为负值，创建后不能改变。
- 界限(limit)
	<br/>第一个不应该被读出或者写入的缓冲区位置索引。位于limit后(包括limit位置)的数据不可以被读写。
- 位置(position)
	<br/>下一个可以被读或写的缓冲区位置索引。初始值为0(第一个位置的索引)。
- 标记(mark)
	<br/>类似于传统IO流中的mark，Buffer可以直接将position定位到mark处。
	
> asdfasf
	

	