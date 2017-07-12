package notes.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个最简单的线程池
 * @author wguo
 * @date 2017年5月4日 上午10:02:32
 */
public class ThreadPool {

	private static final ExecutorService threadPool = Executors.newFixedThreadPool(1);
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			final int taskI = i;
			Runnable r=new Runnable() {
				@Override
				public void run() {
					System.out.println(taskI+":"+System.nanoTime());
				}
			};
			threadPool.submit(r);
			Thread.currentThread().sleep(300);
		}

	}

}
