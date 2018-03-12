package notes.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderUtil implements Runnable{
	
	private static Lock lock=new ReentrantLock();
	
	private static int NUM=20;
	private static CountDownLatch cdl = new CountDownLatch(NUM);
	
	public static void main(String[] args) {
		for (int i = 0; i < NUM; i++) {
			new Thread(new OrderUtil()).start();
			cdl.countDown();
		}
		
	}

	
	public static void createOrder(){
		String orderCode=null;
		lock.lock();
		try{
			orderCode=OrderCodeGenerator.getCode();
		}finally{
			lock.unlock();
		}
		System.out.println(Thread.currentThread().getName()+"=========orderCode:"+orderCode);
	}


	public void run() {
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createOrder();
	}
	
}
