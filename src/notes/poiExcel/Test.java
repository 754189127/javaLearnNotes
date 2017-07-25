package notes.poiExcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import notes.util.ExcelUtil;

/**
 * ���� poi ʵ�� Excel ���� ����
 * @author wguo
 * @date 2017��7��24�� ����5:27:54
 */
public class Test {
	public static void main(String[] args) throws Exception {
		//1������workbook
		/*HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		//2������sheet
		HSSFSheet sheet = workbook.createSheet("ѧ����Ϣ");
		//3��������
		HSSFRow row= sheet.createRow(0);
		//4��������
		HSSFCell cell =row.createCell(0);
		//5���������
		cell.setCellValue("����");
		//6��excel�ļ������ǰ̨���ߴ���
		OutputStream out = new FileOutputStream("E:/test.xls");
		//ͨ��workbook��write����ʵ��excel�����
		workbook.write(out);
		out.close();*/
		
		
		/*List<Stu> data = new ArrayList<Stu>();
		Stu stu=new Stu();
		stu.setName("����");
		stu.setAge(25);
		stu.setIDNo("001");
		stu.setSex("��");
		data.add(stu);
		
		stu=new Stu();
		stu.setName("����");
		stu.setAge(29);
		stu.setIDNo("002");
		stu.setSex("��");
		data.add(stu);
		
		stu=new Stu();
		stu.setIDNo("003");
		stu.setSex("Ů");
		stu.setName("����");
		stu.setAge(21);
		
		data.add(stu);
		
		OutputStream out = new FileOutputStream("E:/test2.xls");
		Map<String,String> fields=new LinkedHashMap<String,String>();
		fields.put("IDNo", "���");
		fields.put("name", "����");
		fields.put("sex", "�Ա�");
		fields.put("age", "����");
		ExcelUtil.ListToExcel(data, out, fields);*/
		
		/*�žų˷���*/
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
		
		
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for (int i = 1; i <= 9; i++) {
			for (int j =9; j>i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

/**
 * ѧ��ʵ����
 * @author wguo
 * @date 2017��7��24�� ����8:30:19
 */
class Stu{
		
	private String name;
	private String sex;
	private int age;
	private String IDNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getIDNo() {
		return IDNo;
	}
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	
	
}
