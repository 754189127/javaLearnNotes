package notes.thread;

public class Teacher implements Runnable{
	
	private Print print;
	
	private String name;
	
	private int chineseScore;
	
	private int mathScore;
	
	private int englishScore;
	
	public Teacher(Print print, String name, int chineseScore,int mathScore,int englishScore) {
        this.print=print;
        this.name = name;
        this.chineseScore=chineseScore;
        this.mathScore=mathScore;
        this.englishScore=englishScore;
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
