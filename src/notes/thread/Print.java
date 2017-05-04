package notes.thread;

public class Print {
	
	public static void main(String[] args){
		Print print = new Print();
		Teacher t1 =new Teacher(print,"张同学", 88, 70, 99);
		Teacher t2 =new Teacher(print,"李同学", 96, 96, 92);
		Teacher t3 =new Teacher(print,"孙同学", 89, 79, 93);
		
		Thread td1 = new Thread(t1);
		Thread td2 = new Thread(t2);
		Thread td3 = new Thread(t3);
		
		td1.start();
		td2.start();
		td3.start();
	}
	
	public synchronized void printScore(String name,int chineseScore,int mathScore,int englishScore){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+" 语文成绩 "+chineseScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+" 数学成绩 "+mathScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+" 英语成绩 "+englishScore);
	}
}
