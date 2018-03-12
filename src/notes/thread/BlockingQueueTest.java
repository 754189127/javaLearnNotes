package notes.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {
		// 声明一个容量为10的缓存队列
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		
		//三个生产者
		Producer producer1 = new Producer(queue);
		Producer producer2 = new Producer(queue);
		Producer producer3 = new Producer(queue);
		
		//一个消费者
		Consumer consumer1 = new Consumer(queue);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		// 启动线程
		executor.execute(producer1);
		executor.execute(producer2);
		executor.execute(producer3);
		
		executor.execute(consumer1);
		
		// 执行10s后停止生产
		Thread.sleep(10000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        
        Thread.sleep(2000);
		
		
		executor.shutdown();
	}

}


/**
 * 生产者
 * @author wguo
 * @date 2017年7月11日 下午3:59:50
 */
class Producer implements Runnable{
	private volatile boolean isRunning= true;
	private static AtomicInteger  count = new AtomicInteger();
	private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
	private BlockingQueue queue;
	public Producer(BlockingQueue queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		String data = null;
		Random rd = new Random();
		System.out.println("启动生成者线程！");
		while(isRunning){
			System.out.println("正在生产数据...");
			try {
				Thread.sleep(rd.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				//数据产生
				data = "data:"+count.incrementAndGet();
				System.out.println("将数据 "+data+" 放入队列...");
				if(!queue.offer(data,2,TimeUnit.SECONDS)){
					System.out.println("数据放入队列失败："+data);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}finally {
				System.out.println("退出生产者线程！");
			}
		}
		
	}
	
	public void stop() {
        isRunning = false;
    }
}


/**
 * 消费者
 * @author wguo
 * @date 2017年7月11日 下午4:00:57
 */
class Consumer implements Runnable{
	private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
	private BlockingQueue queue;
	public Consumer(BlockingQueue queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		 System.out.println("启动消费者线程！");
		 Random r = new Random();
	     boolean isRunning = true;
		while(true){
			System.out.println("正从队列中获取数据...");
			try {
				String data = (String) queue.poll(2, TimeUnit.SECONDS);
				if(data==null){
					// 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
				}else{
					System.out.println("从队列拿到数据：" + data);
                    System.out.println("正在消费数据：" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}finally {
				System.out.println("退出消费者线程！");
			}
			
		}
	}
	
	
}
