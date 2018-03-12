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
		// ����һ������Ϊ10�Ļ������
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		
		//����������
		Producer producer1 = new Producer(queue);
		Producer producer2 = new Producer(queue);
		Producer producer3 = new Producer(queue);
		
		//һ��������
		Consumer consumer1 = new Consumer(queue);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		// �����߳�
		executor.execute(producer1);
		executor.execute(producer2);
		executor.execute(producer3);
		
		executor.execute(consumer1);
		
		// ִ��10s��ֹͣ����
		Thread.sleep(10000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        
        Thread.sleep(2000);
		
		
		executor.shutdown();
	}

}


/**
 * ������
 * @author wguo
 * @date 2017��7��11�� ����3:59:50
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
		System.out.println("�����������̣߳�");
		while(isRunning){
			System.out.println("������������...");
			try {
				Thread.sleep(rd.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				//���ݲ���
				data = "data:"+count.incrementAndGet();
				System.out.println("������ "+data+" �������...");
				if(!queue.offer(data,2,TimeUnit.SECONDS)){
					System.out.println("���ݷ������ʧ�ܣ�"+data);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}finally {
				System.out.println("�˳��������̣߳�");
			}
		}
		
	}
	
	public void stop() {
        isRunning = false;
    }
}


/**
 * ������
 * @author wguo
 * @date 2017��7��11�� ����4:00:57
 */
class Consumer implements Runnable{
	private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
	private BlockingQueue queue;
	public Consumer(BlockingQueue queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		 System.out.println("�����������̣߳�");
		 Random r = new Random();
	     boolean isRunning = true;
		while(true){
			System.out.println("���Ӷ����л�ȡ����...");
			try {
				String data = (String) queue.poll(2, TimeUnit.SECONDS);
				if(data==null){
					// ����2s��û���ݣ���Ϊ���������̶߳��Ѿ��˳����Զ��˳������̡߳�
                    isRunning = false;
				}else{
					System.out.println("�Ӷ����õ����ݣ�" + data);
                    System.out.println("�����������ݣ�" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}finally {
				System.out.println("�˳��������̣߳�");
			}
			
		}
	}
	
	
}
