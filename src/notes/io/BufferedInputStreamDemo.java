package notes.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedInputStreamDemo {
	
	public static void main(String[] args) {	
		try {
			FileInputStream fis = new FileInputStream("E:/temp/fileinputStream.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			String text = "";
			StringBuilder textBuilder =new StringBuilder();
			//����һ��������
			byte[] buffer = new byte[10240];
			int flag=0;
            while((flag=bis.read(buffer))!=-1){
            	text+=new String(buffer, 0, flag);
            	textBuilder.append(new String(buffer,0,flag));
            }
            System.out.println(text);
            System.out.println(textBuilder.toString());
            bis.close();
            
            
			//�������
			FileOutputStream fos = new FileOutputStream("E:/temp/fileOutputStream.txt");
			BufferedOutputStream bfos = new BufferedOutputStream(fos);
			String content="���ǻ���������������ݣ�";
			bfos.write(content.getBytes(), 0, content.getBytes().length);
			bfos.flush();
			bfos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
