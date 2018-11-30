package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

/**
 * 使用同步锁控制线程同步的测试
 * 
 * @author houzw
 *
 */
public class DrawLock extends Thread {
	private AccountLock accountLock;
	private double drawAmount;

	public DrawLock(String name, AccountLock accountLock, double drawAmount) {
		super(name);
		this.accountLock = accountLock;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		// accountLock的draw()方法使用了lock对象进行锁定处理
		accountLock.draw(drawAmount);
	}

	/* ============测试============= */
	public static void main(String[] args) {
		AccountLock acct = new AccountLock("11111111", 888);
		new DrawLock("甲", acct, 666).start();
		new DrawLock("乙", acct, 666).start();
	}

}
