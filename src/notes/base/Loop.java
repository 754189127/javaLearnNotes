package notes.base;

public class Loop {
	public static void main(String[] args)  {
		
		/**
		 * for ѭ��
		 * �žų˷���
		 */
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j<=i; j++) {
				System.out.print(i+"*"+j+"="+i*j+"\t");
			}
			System.out.println();
		}
		
		System.out.println();
		
		for (int i = 9; i >0; i--) {
			for (int j = 1; j<=i; j++) {
				System.out.print(i+"*"+j+"="+i*j+"\t");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 1; i <= 9; i++) {
			for (int j =9; j>i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		/**
		 * do while ѭ�� ����ִ��һ�����ж�����
		 * */
		int i=1;
		do{
			if(i%3==0 && i%9==0 && i%22==2){
				System.out.println("i:"+i);
			}
			i++;
		}while(i<1000);
	}
}
