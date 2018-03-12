package notes.thread;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 业务场景一：多考生考试
 * 这个场景中几个点需要注意：
	考试时间为120分钟，30分钟后才可交卷，初始化考生完成试卷时间最小应为30分钟
	对于能够在120分钟内交卷的考生，如何实现这些考生交卷
	对于120分钟内没有完成考试的考生，在120分钟考试时间到后需要让他们强制交卷
	在所有的考生都交完卷后，需要将控制线程关闭
	
 	实现思想：用DelayQueue存储考生（Student类），每一个考生都有自己的名字和完成试卷的时间，
 	Teacher线程对DelayQueue进行监控，收取完成试卷小于120分钟的学生的试卷。当考试时间120分钟到时，
 	先关闭Teacher线程，然后强制DelayQueue中还存在的考生交卷。每一个考生交卷都会进行一次countDownLatch.countDown()，
 	当countDownLatch.await()不再阻塞说明所有考生都交完卷了，而后结束考试。
 * @author wguo
 * @date 2017年7月12日 下午3:22:49
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
	        System.out.println(" 考试时间到，全部交卷！");  
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
            System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 120分钟" );
        }else {
            System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 "+workTime +" 分钟");  
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
        super("强制收卷", workTime,countDownLatch);
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