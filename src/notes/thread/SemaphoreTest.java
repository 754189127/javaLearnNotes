package notes.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
 * Semaphore的使用     计数信号量 测试  实现信号灯
 * Semaphore的主要方法摘要：
 * void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
 * void release():释放一个许可，将其返回给信号量。
 * int availablePermits():返回此信号量中当前可用的许可数。
 * boolean hasQueuedThreads():查询是否有线程正在等待获取。
 * 
 * @author wguo
 * @date 2017年5月4日 上午11:42:34
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		//创建一个可根据需要创建新线程的线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		final int semaphoreCount = 3;
		//创建Semaphore信号量，初始化许可大小为3
		final Semaphore sp =new Semaphore(semaphoreCount);
		for (int i = 1; i <= 10; i++) {
			Runnable rn = new Runnable() {
				@Override
				public void run() {
					try {
						//请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程 "+Thread.currentThread().getName()+" 进入，当前已有 "+(semaphoreCount-sp.availablePermits())+" 个并发");
					try {
						Thread.sleep((long) (Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程 "+Thread.currentThread().getName()+" 即将离开");
					//释放许可，许可数加1
					sp.release();
					 //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
					System.out.println("线程 "+Thread.currentThread().getName()+" 已离开，当前已有 "+(semaphoreCount-sp.availablePermits())+" 个并发");
					
				}
			};
			executorService.execute(rn);
		}
		
	}
}
