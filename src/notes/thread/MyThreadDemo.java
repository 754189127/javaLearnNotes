package notes.thread;

public class MyThreadDemo extends Thread{
	
	private String msg;
	private int count;
	
	public MyThreadDemo(String msg,int count){
		this.msg = msg;
		this.count = count;
		setName(msg + " runner Thread");
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
	public static void main(String[] args) {
		MyThreadDemo d1 = new MyThreadDemo("Hello From X", 10);
		MyThreadDemo d2 = new MyThreadDemo("Hello From Y", 15);
		d1.start();
		d2.start();
	}
}
