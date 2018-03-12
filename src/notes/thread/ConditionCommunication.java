package notes.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.sub2(i);
				}

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.sub3(i);
				}

			}
		}).start();

		for (int i = 1; i <= 10; i++) {
			business.main(i);
		}
	}

	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		private int shouldSub = 1;

		/**
		 * 子线程循环
		 * 
		 * @param i
		 */
		public void sub2(int i) {
			lock.lock();
			try {
				while (shouldSub != 2) {
					try {
						condition2.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub2 thread seq of " + j + ", loop for " + i);
				}
				shouldSub = 3;
				condition3.signal();

			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			lock.lock();
			try {
				while (shouldSub != 3) {
					try {
						condition3.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub3 thread seq of " + j + ", loop for " + i);
				}
				shouldSub = 1;
				condition1.signal();

			} finally {
				lock.unlock();
			}
		}

		/**
		 * 主线程循环
		 * 
		 * @param i
		 */
		public void main(int i) {
			lock.lock();
			try {
				while (shouldSub != 1) {
					try {
						condition1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 3; j++) {
					System.out.println("main thread seq of " + j + ", loop for " + i);
				}
				shouldSub = 2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
