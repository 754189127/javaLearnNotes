package notes.thread;

public class MyThreadDemo3 {
	
	private String msg;
	private int count;
	private Thread t;
	
	public MyThreadDemo3(String m,int c){
		this.msg = m;
		this.count = c;
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
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
		});
		t.start();
	}
	

	public static void main(String[] args) {
		new MyThreadDemo3("Hello From X", 2);
		new MyThreadDemo3("Hello From Y", 3);
	}
}
