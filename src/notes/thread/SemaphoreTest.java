package notes.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * �����ź��� ����  ʵ���źŵ�
 * @author wguo
 * @date 2017��5��4�� ����11:42:34
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
					System.out.println("�߳� "+Thread.currentThread().getName()+" ���룬��ǰ���� "+(semaphoreCount-sp.availablePermits())+" ������");
					try {
						Thread.sleep((long) (Math.random()*10000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("�߳� "+Thread.currentThread().getName()+" �����뿪");
					sp.release();
					System.out.println("�߳� "+Thread.currentThread().getName()+" ���뿪����ǰ���� "+(semaphoreCount-sp.availablePermits())+" ������");
					
				}
			};
			executorService.execute(rn);
		}
		
	}
}
