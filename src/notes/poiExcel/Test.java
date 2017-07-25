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
 * 利用 poi 实现 Excel 导入 导出
 * @author wguo
 * @date 2017年7月24日 下午5:27:54
 */
public class Test {
	public static void main(String[] args) throws Exception {
		//1、创建workbook
		/*HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		//2、创建sheet
		HSSFSheet sheet = workbook.createSheet("学生信息");
		//3、创建行
		HSSFRow row= sheet.createRow(0);
		//4、创建列
		HSSFCell cell =row.createCell(0);
		//5、填充数据
		cell.setCellValue("姓名");
		//6、excel文件输出到前台或者磁盘
		OutputStream out = new FileOutputStream("E:/test.xls");
		//通过workbook的write方法实现excel的输出
		workbook.write(out);
		out.close();*/
		
		
		/*List<Stu> data = new ArrayList<Stu>();
		Stu stu=new Stu();
		stu.setName("张三");
		stu.setAge(25);
		stu.setIDNo("001");
		stu.setSex("男");
		data.add(stu);
		
		stu=new Stu();
		stu.setName("李四");
		stu.setAge(29);
		stu.setIDNo("002");
		stu.setSex("男");
		data.add(stu);
		
		stu=new Stu();
		stu.setIDNo("003");
		stu.setSex("女");
		stu.setName("王五");
		stu.setAge(21);
		
		data.add(stu);
		
		OutputStream out = new FileOutputStream("E:/test2.xls");
		Map<String,String> fields=new LinkedHashMap<String,String>();
		fields.put("IDNo", "编号");
		fields.put("name", "姓名");
		fields.put("sex", "性别");
		fields.put("age", "年龄");
		ExcelUtil.ListToExcel(data, out, fields);*/
		
		/*九九乘法表*/
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
 * 学生实体类
 * @author wguo
 * @date 2017年7月24日 下午8:30:19
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
