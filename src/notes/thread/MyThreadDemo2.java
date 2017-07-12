package notes.thread;

public class MyThreadDemo2 implements Runnable{
	
	private String msg;
	private int count;
	private Thread t;
	
	public MyThreadDemo2(String msg,int count){
		this.msg = msg;
		this.count = count;
		t = new Thread(this);
		t.setName(msg+" printer thread");
		t.start();
	}
	

	public static void main(String[] args) {
		new MyThreadDemo2("Hello From X", 1);
		new MyThreadDemo2("Hello From Y", 2);
	}


	public void run(){
		while(count-->0){
			System.out.println(msg);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(msg+" all done");
		}
	}
}
