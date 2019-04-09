mina客户端和服务端在一起，死锁测试

数据流：c1 -> sc2 -> s3

sc2中接收到socket请求，**同一线程中**再用socket请求s3，若s3处理时间超过5秒，则sc2会发生死锁<br/>
若sc2中非单线程接收转发，则不会有死锁出现

最佳解决方案：
sc2中在s端的SocketAcceptor上添加ExecutorFilter，该Filter会在messageReceived和messageSent中使用Executor启动新线程进行后续操作，从而避免sc处于单线程中造成死锁。
```
...
private SocketAcceptor acceptor = new NioSocketAcceptor();
private DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
chain.addFirst("S2ExecutorFilter", new ExecutorFilter());
...
```
