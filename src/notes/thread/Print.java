package notes.thread;

public class Print {
	
	public static void main(String[] args){
		Print print = new Print();
		Teacher t1 =new Teacher(print,"��ͬѧ", 88, 70, 99);
		Teacher t2 =new Teacher(print,"��ͬѧ", 96, 96, 92);
		Teacher t3 =new Teacher(print,"��ͬѧ", 89, 79, 93);
		
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
		System.out.println(name+" ���ĳɼ� "+chineseScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+" ��ѧ�ɼ� "+mathScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+" Ӣ��ɼ� "+englishScore);
	}
}
