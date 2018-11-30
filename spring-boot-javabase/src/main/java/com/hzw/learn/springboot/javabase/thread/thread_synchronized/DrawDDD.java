package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

/**
 * 使用同步方法控制线程同步的测试
 * 
 * @author houzw
 *
 */
public class DrawDDD extends Thread {
	private AccountDDD accountDDD;
	private double drawAmount;

	public DrawDDD(String name, AccountDDD accountDDD, double drawAmount) {
		super(name);
		this.accountDDD = accountDDD;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		// accountDDD的draw()方法使用了synchronized修饰成同步方法了
		accountDDD.draw(drawAmount);
	}

	/* ============测试============= */
	public static void main(String[] args) {
		AccountDDD acct = new AccountDDD("11111111", 888);
		new DrawDDD("甲", acct, 666).start();
		new DrawDDD("乙", acct, 666).start();
	}

}
