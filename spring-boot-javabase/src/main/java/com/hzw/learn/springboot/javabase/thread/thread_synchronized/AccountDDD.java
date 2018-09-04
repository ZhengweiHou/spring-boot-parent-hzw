package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

/**
 * 账户类（领域驱动设计 - Domain Driven Design）
 * @author houzw
 *
 */
public class AccountDDD {
	private String accountNo;
	private double balance;
	public AccountDDD(String accountNo, double balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	// 账户类提供一个线程安全的取钱方法
	public synchronized void draw(double drawAmount){
		
		if(balance < drawAmount){
			System.out.println(Thread.currentThread().getName() + "取钱【" + drawAmount + "】,余额不足！");
			return;
		}
		System.out.println(Thread.currentThread().getName() + "取钱成功！取出金额：" + drawAmount);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		balance -= drawAmount;
		System.out.println("\t余额为：" + balance);
		
	}
	
	
	// 重写hashCode和equals方法，由accountNo来确定账户是否为同一对象
	@Override
	public int hashCode() {
		return this.accountNo.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(null != obj && obj.getClass() == this.getClass()){
			return this.getAccountNo().equals(((AccountDDD)obj).getAccountNo());
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
	// 余额不可随意修改
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
}
