package notes.thread;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * ҵ�񳡾�һ���࿼������
 * ��������м�������Ҫע�⣺
	����ʱ��Ϊ120���ӣ�30���Ӻ�ſɽ�����ʼ����������Ծ�ʱ����СӦΪ30����
	�����ܹ���120�����ڽ���Ŀ��������ʵ����Щ��������
	����120������û����ɿ��ԵĿ�������120���ӿ���ʱ�䵽����Ҫ������ǿ�ƽ���
	�����еĿ�������������Ҫ�������̹߳ر�
	
 	ʵ��˼�룺��DelayQueue�洢������Student�ࣩ��ÿһ�����������Լ������ֺ�����Ծ��ʱ�䣬
 	Teacher�̶߳�DelayQueue���м�أ���ȡ����Ծ�С��120���ӵ�ѧ�����Ծ�������ʱ��120���ӵ�ʱ��
 	�ȹر�Teacher�̣߳�Ȼ��ǿ��DelayQueue�л����ڵĿ�������ÿһ���������������һ��countDownLatch.countDown()��
 	��countDownLatch.await()��������˵�����п�����������ˣ�����������ԡ�
 * @author wguo
 * @date 2017��7��12�� ����3:22:49
 */
public class DelayQueueTest {
	 public static void main(String[] args) throws InterruptedException {
	        // TODO Auto-generated method stub
	        int studentNumber = 20;
	        CountDownLatch countDownLatch = new CountDownLatch(studentNumber+1);
	        DelayQueue< Student> students = new DelayQueue<Student>();
	        Random random = new Random();
	        for (int i = 0; i < studentNumber; i++) {
	            students.put(new Student("student"+(i+1), 30+random.nextInt(120),countDownLatch));
	        }
	        Thread TeachersThread =new Thread(new Teachers(students));
	        students.put(new EndExam(students, 120,countDownLatch,TeachersThread));
	        TeachersThread.start();
	        countDownLatch.await();
	        System.out.println(" ����ʱ�䵽��ȫ������");  
	 }
}

class Student implements Runnable,Delayed{

    private String name;
    private long workTime;
    private long submitTime;
    private boolean isForce = false;
    private CountDownLatch countDownLatch;
    
    public Student(){}
    
    public Student(String name,long workTime,CountDownLatch countDownLatch){
        this.name = name;
        this.workTime = workTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS)+System.nanoTime();
        this.countDownLatch = countDownLatch;
    }
    
    @Override
    public int compareTo(Delayed o) {
        // TODO Auto-generated method stub
        if(o == null || ! (o instanceof Student)) return 1;
        if(o == this) return 0; 
        Student s = (Student)o;
        if (this.workTime > s.workTime) {
            return 1;
        }else if (this.workTime == s.workTime) {
            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // TODO Auto-generated method stub
        return unit.convert(submitTime - System.nanoTime(),  TimeUnit.NANOSECONDS);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (isForce) {
            System.out.println(name + " ����, ϣ����ʱ" + workTime + "����"+" ,ʵ����ʱ 120����" );
        }else {
            System.out.println(name + " ����, ϣ����ʱ" + workTime + "����"+" ,ʵ����ʱ "+workTime +" ����");  
        }
        countDownLatch.countDown();
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean isForce) {
        this.isForce = isForce;
    }
}



class EndExam extends Student{

    private DelayQueue<Student> students;
    private CountDownLatch countDownLatch;
    private Thread TeachersThread;
    
    public EndExam(DelayQueue<Student> students, long workTime, CountDownLatch countDownLatch,Thread TeachersThread) {
        super("ǿ���վ�", workTime,countDownLatch);
        this.students = students;
        this.countDownLatch = countDownLatch;
        this.TeachersThread = TeachersThread;
    }
    
    
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        TeachersThread.interrupt();
        Student tmpStudent;
        for (Iterator<Student> iterator2 = students.iterator(); iterator2.hasNext();) {
            tmpStudent = iterator2.next();
            tmpStudent.setForce(true);
            tmpStudent.run();
        }
        countDownLatch.countDown();
    }
    
}


class Teachers implements Runnable{

    private DelayQueue<Student> students;
    public Teachers(DelayQueue<Student> students){
        this.students = students;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println(" test start");
            while(!Thread.interrupted()){
                students.take().run();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
}