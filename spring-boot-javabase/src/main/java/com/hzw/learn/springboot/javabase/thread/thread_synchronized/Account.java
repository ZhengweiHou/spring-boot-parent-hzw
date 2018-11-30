package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

/**
 * 账户类（普通模型）
 * 
 * @author houzw
 *
 */
public class Account {
	private String accountNo;
	private double balance;

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
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
			return this.getAccountNo().equals(((Account) obj).getAccountNo());
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
