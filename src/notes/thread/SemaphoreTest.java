package notes.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SemaphoreҲ��һ���߳�ͬ���ĸ����࣬����ά����ǰ����������̸߳��������ṩ��ͬ�����ơ�ʹ��Semaphore���Կ���ͬʱ������Դ���̸߳��������磬ʵ��һ���ļ�����Ĳ�����������
 * Semaphore��ʹ��     �����ź��� ����  ʵ���źŵ�
 * Semaphore����Ҫ����ժҪ��
 * void acquire():�Ӵ��ź�����ȡһ����ɣ����ṩһ�����ǰһֱ���߳������������̱߳��жϡ�
 * void release():�ͷ�һ����ɣ����䷵�ظ��ź�����
 * int availablePermits():���ش��ź����е�ǰ���õ��������
 * boolean hasQueuedThreads():��ѯ�Ƿ����߳����ڵȴ���ȡ��
 * 
 * @author wguo
 * @date 2017��5��4�� ����11:42:34
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		//����һ���ɸ�����Ҫ�������̵߳��̳߳�
		ExecutorService executorService = Executors.newCachedThreadPool();
		final int semaphoreCount = 3;
		//����Semaphore�ź�������ʼ����ɴ�СΪ3
		final Semaphore sp =new Semaphore(semaphoreCount);
		for (int i = 1; i <= 10; i++) {
			Runnable rn = new Runnable() {
				@Override
				public void run() {
					try {
						//��������ɣ�����пɻ�õ�������������ִ�У��������1���������
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳� "+Thread.currentThread().getName()+" ���룬��ǰ���� "+(semaphoreCount-sp.availablePermits())+" ������");
					try {
						Thread.sleep((long) (Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳� "+Thread.currentThread().getName()+" �����뿪");
					//�ͷ���ɣ��������1
					sp.release();
					 //���������ʱ��ִ�в�׼ȷ����Ϊ��û�к�����Ĵ���ϳ�ԭ�ӵ�Ԫ
					System.out.println("�߳� "+Thread.currentThread().getName()+" ���뿪����ǰ���� "+(semaphoreCount-sp.availablePermits())+" ������");
					
				}
			};
			executorService.execute(rn);
		}
		
	}
}
