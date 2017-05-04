package notes.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 计数信号量 测试  实现信号灯
 * @author wguo
 * @date 2017年5月4日 上午11:42:34
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final int semaphoreCount = 3;
		final Semaphore sp =new Semaphore(semaphoreCount);
		for (int i = 1; i <= 10; i++) {
			Runnable rn = new Runnable() {
				@Override
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程 "+Thread.currentThread().getName()+" 进入，当前已有 "+(semaphoreCount-sp.availablePermits())+" 个并发");
					try {
						Thread.sleep((long) (Math.random()*10000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程 "+Thread.currentThread().getName()+" 即将离开");
					sp.release();
					System.out.println("线程 "+Thread.currentThread().getName()+" 已离开，当前已有 "+(semaphoreCount-sp.availablePermits())+" 个并发");
					
				}
			};
			executorService.execute(rn);
		}
		
	}
}
