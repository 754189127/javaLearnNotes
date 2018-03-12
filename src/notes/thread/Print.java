package notes.thread;

public class Print {

	public static void main(String[] args) {
		Print print = new Print();
		Teacher t1 = new Teacher(print, "2张同学", 88, 70, 99);
		Teacher t2 = new Teacher(print, "3李同学", 96, 96, 92);
		Teacher t3 = new Teacher(print, "4孙同学", 89, 79, 93);

		Thread td1 = new Thread(t1);
		Thread td2 = new Thread(t2);
		Thread td3 = new Thread(t3);

		td1.start();
		td2.start();
		td3.start();
	}

	public synchronized void printScore(String name, int chineseScore, int mathScore, int englishScore) {
		
		System.out.println(name + " 语文成绩 " + chineseScore);
		
		System.out.println(name + " 数学成绩 " + mathScore);
		
		System.out.println(name + " 英语成绩 " + englishScore);
	}
}

class Teacher implements Runnable {

	private Print print;

	private String name;

	private int chineseScore;

	private int mathScore;

	private int englishScore;

	public Teacher(Print print, String name, int chineseScore, int mathScore, int englishScore) {
		this.print = print;
		this.name = name;
		this.chineseScore = chineseScore;
		this.mathScore = mathScore;
		this.englishScore = englishScore;
	}

	public Print getPrint() {
		return print;
	}

	public void setPrint(Print print) {
		this.print = print;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChineseScore() {
		return chineseScore;
	}

	public void setChineseScore(int chineseScore) {
		this.chineseScore = chineseScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	@Override
	public void run() {
		print.printScore(name, chineseScore, mathScore, englishScore);
	}

}