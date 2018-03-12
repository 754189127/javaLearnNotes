package notes.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedTest {

	public static void main(String[] args){
		new LockTest().init();
	}
	
	public void init(){
		final Outputer outPuter = new Outputer();
	}
	
	
	static class Outputer{
		Lock lock = new ReentrantLock();
		public void output(String name){
			int len = name.length();
			lock.lock();
			synchronized (Outputer.class) {
				for (int i = 0; i < len; i++) {
					System.out.println(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		public synchronized void output2(String name){
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.println(name.charAt(i));
			}
			System.out.println();
		}
		
		
		public static synchronized void output3(String name){
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.println(name.charAt(i));
			}
			System.out.println();
		}
	}
}
