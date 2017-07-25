package notes.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSON;

/**
 * ExcelUtil 工具类 导出/导入excel
 * 
 * @author wguo
 * @date 2017年7月24日 下午7:06:30
 */
public class ExcelUtil {
	private static int sheetSize = 5000;// 单个sheet存储的数据

	/**
	 * @throws Exception 将列表数据excel导出 @param data 要导出的数据 @param out
	 * excel的输出目的地，通过流输出 @param fields 每个属性对应的中文名 @return void @throws
	 */
	public static <T> void ListToExcel(List<T> data, OutputStream out, Map<String, String> fields) throws Exception {
		if (null == data || data.size() == 0) {
			throw new Exception("Excel中无数据");
		}
		// xls格式的excel不能存储太多数据 65536最大值
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 计算一共有多少个sheet
		int sheetNum = data.size() / sheetSize;
		if (data.size() % sheetSize != 0) {
			sheetNum = sheetNum + 1;
		}
		String[] fieldNames = new String[fields.size()];
		String[] chinaNames = new String[fields.size()];
		int count = 0;
		for (Entry<String, String> entry : fields.entrySet()) {
			String fieldName = entry.getKey();
			String chinaName = entry.getValue();
			fieldNames[count] = fieldName;
			chinaNames[count] = chinaName;
			count++;
		}

		for (int i = 0; i < sheetNum; i++) {
			int rowNum = 0;
			HSSFSheet sheet = workbook.createSheet();
			int startIndex = i * sheetSize;
			int endIndex = (i + 1) * sheetSize - 1 > data.size() ? data.size() : (i + 1) * sheetSize - 1;
			HSSFRow row = sheet.createRow(rowNum);
			for (int j = 0; j < chinaNames.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(chinaNames[j]);// 设置中文标题
			}
			rowNum++;
			for (int index = startIndex; index < endIndex; index++) {
				T item = data.get(index);// 获取数据的实体对象
				row = sheet.createRow(rowNum);
				for (int j = 0; j < chinaNames.length; j++) {
					Field field = item.getClass().getDeclaredField(fieldNames[j]);
					field.setAccessible(true);
					Object obj = field.get(item);
					String value = obj == null ? "" : obj.toString();// 通过反射取值
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(value);// 设置单元格内容
				}
				rowNum++;
			}

		}
		workbook.write(out);
	}

	
	public static <T> List<T> excelToList() throws FileNotFoundException, IOException {
		List<Stu> data = new ArrayList<Stu>();
		HSSFWorkbook workBook=new HSSFWorkbook(new FileInputStream("E:/test2.xls"));
		//迭代每一个选项卡
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = workBook.getSheetAt(i);
			if(null==sheet){
				continue;
			}
			//循环行 
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				HSSFRow row=sheet.getRow(j);
				if(null==row){
					continue;
				}
				Stu stu=new Stu();
				stu.setIDNo(row.getCell(0).getStringCellValue());
				stu.setName(row.getCell(1).getStringCellValue());
				stu.setSex(row.getCell(2).getStringCellValue());
				stu.setAge(Integer.valueOf(row.getCell(3).getStringCellValue()));
				data.add(stu);
			}
		}
		return (List<T>) data;
	}
	
	public static void main(String[] args) throws Exception {
		/*List<Stu> data = new ArrayList<Stu>();
		Stu stu = new Stu();
		stu.setName("张三");
		stu.setAge(25);
		stu.setIDNo("001");
		stu.setSex("男");
		data.add(stu);

		stu = new Stu();
		stu.setName("李四");
		stu.setAge(29);
		stu.setIDNo("002");
		stu.setSex("男");
		data.add(stu);

		stu = new Stu();
		stu.setIDNo("003");
		stu.setSex("女");
		stu.setName("王五");
		stu.setAge(21);

		data.add(stu);

		OutputStream out = new FileOutputStream("E:/test2.xls");
		Map<String, String> fields = new LinkedHashMap<String, String>();
		fields.put("IDNo", "编号");
		fields.put("name", "姓名");
		fields.put("sex", "性别");
		fields.put("age", "年龄");
		ExcelUtil.ListToExcel(data, out, fields);*/
		
		List<T> data=ExcelUtil.excelToList();
		System.out.println(JSON.toJSON(data));
	}
}

/**
 * 学生实体类
 * 
 * @author wguo
 * @date 2017年7月24日 下午8:30:19
 */
class Stu {

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