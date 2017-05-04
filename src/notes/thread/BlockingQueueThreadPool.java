package notes.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 基于队列的线程池
 * 
 * @author wguo
 * @date 2017年5月4日 上午10:02:21
 */
public class BlockingQueueThreadPool {
	
	public static ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			queue.add(new TestThread("初始化"));
		}

		final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 15, TimeUnit.SECONDS, queue);
		System.out.println("getActiveCount=" + executor.getActiveCount() + ";getKeepAliveTime="
				+ executor.getKeepAliveTime(TimeUnit.SECONDS) + ";getCompletedTaskCount="
				+ executor.getCompletedTaskCount() + ";getCorePoolSize=" + executor.getCorePoolSize()
				+ ";getLargestPoolSize=" + executor.getLargestPoolSize() + ";getMaximumPoolSize="
				+ executor.getMaximumPoolSize() + ";getPoolSize=" + executor.getPoolSize() + ";getTaskCount="
				+ executor.getTaskCount() + ";getQueue().size()=" + executor.getQueue().size());
		executor.execute(queue.poll());
		System.out.println("getActiveCount=" + executor.getActiveCount() + ";getKeepAliveTime="
				+ executor.getKeepAliveTime(TimeUnit.SECONDS) + ";getCompletedTaskCount="
				+ executor.getCompletedTaskCount() + ";getCorePoolSize=" + executor.getCorePoolSize()
				+ ";getLargestPoolSize=" + executor.getLargestPoolSize() + ";getMaximumPoolSize="
				+ executor.getMaximumPoolSize() + ";getPoolSize=" + executor.getPoolSize() + ";getTaskCount="
				+ executor.getTaskCount() + ";getQueue().size()=" + executor.getQueue().size());

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("getActiveCount=" + executor.getActiveCount() + ";getKeepAliveTime="
							+ executor.getKeepAliveTime(TimeUnit.SECONDS) + ";getCompletedTaskCount="
							+ executor.getCompletedTaskCount() + ";getCorePoolSize=" + executor.getCorePoolSize()
							+ ";getLargestPoolSize=" + executor.getLargestPoolSize() + ";getMaximumPoolSize="
							+ executor.getMaximumPoolSize() + ";getPoolSize=" + executor.getPoolSize()
							+ ";getTaskCount=" + executor.getTaskCount() + ";getQueue().size()="
							+ executor.getQueue().size());
					try {
						Thread.currentThread().sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (true) {
					queue.add(new TestThread("生产者"));
					try {
						Thread.currentThread().sleep(500L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
					if (i > 10)
						break;
				}
			}
		}).start();
	}
}

class TestThread implements Runnable {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String name; // 创建者
	private Date addDate; // 添加到队列的日期

	TestThread(String name) {
		this.name = name;
		this.addDate = new Date();
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "：创建者=" + name + "，创建时间=" + sdf.format(addDate) + "，执行时间="
				+ sdf.format(new Date()) + ",当前队列大小=" + BlockingQueueThreadPool.queue.size());
		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
