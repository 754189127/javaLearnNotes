package notes.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

	 public static void main(String[] args) {
	        // ��ȡJava�̹߳���MXBean
	        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	        // ����Ҫ��ȡͬ����monitor��synchronizer��Ϣ������ȡ�̺߳��̶߳�ջ��Ϣ
	        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
	        // �����߳���Ϣ������ӡ�߳�ID���߳�������Ϣ
	        for (ThreadInfo threadInfo : threadInfos) {
	            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
	            getThreadName());
	        }
	    }
}