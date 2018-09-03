package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

/**
 * 展示非线程安全导致的取钱业务出现的错误
 * @author houzw
 *
 */
public class DrawSync extends Thread{
	private Account account;
	private double drawAmount;
	
	public DrawSync(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		// 使用account作为同步监视器，任何线程进入下面同步代码块之前必须先获取对account账户的锁定
		synchronized(account){
			if(account.getBalance() < drawAmount){
				System.out.println(Thread.currentThread().getName() + "取钱【" + drawAmount + "】,余额不足！");
				return;
			}
			System.out.println(Thread.currentThread().getName() + "取钱成功！取出金额：" + drawAmount);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			account.setBalance(account.getBalance() - drawAmount);
			
			System.out.println("\t余额为：" + account.getBalance());
		}
	}
	
	/*============测试=============*/
	public static void main(String[] args) {
		Account acct = new Account("11111111", 888);
		new DrawSync("甲", acct, 666).start();
		new DrawSync("乙", acct, 666).start();
	}
	
}

