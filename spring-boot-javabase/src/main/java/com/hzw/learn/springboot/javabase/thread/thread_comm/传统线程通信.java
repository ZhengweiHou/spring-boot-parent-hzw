package com.hzw.learn.springboot.javabase.thread.thread_comm;

import com.hzw.learn.springboot.javabase.thread.thread_synchronized.AccountDDD;

public class 传统线程通信 {
	public static void main(String[] args) {
		Account account = new Account("0000", 100);
		new Drawer("债主1", account, 2000).start();
		new Drawer("债主2", account, 1000).start();
//		new Drawer("债主3", account, 3000).start();
		new Depositer("借钱的", account, 500).start();
	}
}

// 债主
class Drawer extends Thread {
	private Account account;
	private double amount;

	public Drawer(String name, Account account, double amount) {
		super(name);
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		account.draw(amount);
	}
}

// 借钱的
class Depositer extends Thread {
	private Account account;
	private double amount;

	public Depositer(String name, Account account, double amount) {
		super(name);
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		for (int i = 0; i <= 100; i++) {
			account.deposit(amount);
		}
	}
}

class Account {
	private String accountNo;
	private double balance;

	// 是否有人等待取钱（true），当存钱后此标记复原（false）
	private boolean flag = false;

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public synchronized void draw(double drawAmount) {
		this.draw(drawAmount, 1);
	}

	private synchronized void draw(double drawAmount, int time) {
		try {
			System.out.println(Thread.currentThread().getName() + "第【" + time + "】 次取钱...");
			if (drawAmount > balance) {
				flag = true; // 账户标记有人等待取钱
				System.out.println("\t取钱【" + drawAmount + "】,余额不足,等待其他人操作！");
				this.notify();
				this.wait(); // 当前account对象为同步监视器，使用同步监视器调用wait()方法

				this.draw(drawAmount, ++time);
			} else {
				balance -= drawAmount;
				System.out.println("\t取钱成功！取出金额：" + drawAmount + "余额：" + balance);

				this.notify(); // 唤醒其他进程操作当前对象
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void deposit(double drawAmount) {
		try {
			System.out.println(Thread.currentThread().getName() + "准备存入金额...");
			if (!flag) {
				System.out.println("未存入");
				this.wait();
			}

			balance += drawAmount;
			System.out.println("\t存入金额：" + drawAmount + "余额：" + balance);
			flag = false;

			notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 重写hashCode和equals方法，由accountNo来确定账户是否为同一对象
	@Override
	public int hashCode() {
		return this.accountNo.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null != obj && obj.getClass() == this.getClass()) {
			return this.getAccountNo().equals(((AccountDDD) obj).getAccountNo());
		}
		return false;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
