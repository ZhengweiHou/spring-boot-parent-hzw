package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 账户类使用lock控制线程同步
 * @author houzw
 * 
 *	Java5提供的锁接口
		1. Lock				<=	ReentrantLock			(可重入锁)
		2. ReadWriteLock	<=	ReentrantReadWriteLock	(可重入读写锁)
			为读写操作提供了三种模式：Writing、ReadingOptimistic、Reading
	Java8提供新型的类StampedLock
		StampedLock 大多数场景中可以替代传统的ReentrantReadWriteLock
 */
public class AccountLock {
	
	private final ReentrantLock lock = new ReentrantLock();
	
	private String accountNo;
	private double balance;
	public AccountLock(String accountNo, double balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	public void draw(double drawAmount){
		
		// 加锁
		lock.lock();
		try{
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
		}finally{
			// 最后释放锁
			lock.unlock();
		}
		
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

}
