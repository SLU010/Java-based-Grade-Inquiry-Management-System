package io.file;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import stu.base.Student;



public class IOWrite {
	//保存到txt
	public void setFileDataToTxt(String filepath,ArrayList<Student> relist) {
		try {
			FileOutputStream fos = new FileOutputStream(filepath);
			OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
			BufferedWriter bw=new BufferedWriter(osw);
			for(Student s:relist) {
				bw.write(s.toString());
				bw.newLine();//一行一行显示
			}
			//资源回收
			IOCloseWrite ic=new IOCloseWrite();
			ic.closeWrite(fos, osw, bw);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File was not found", "Fail", JOptionPane.INFORMATION_MESSAGE);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Charset not supported", "Fail", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "IO error", "Fail", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//保存到Excel表格
	@SuppressWarnings("deprecation")//抑制警告
	public void setFileDataToExcel(String filepath,ArrayList<Student> relist) {
		try {
			FileOutputStream fos = new FileOutputStream(filepath);
			OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
			BufferedWriter bw=new BufferedWriter(osw);
			
			//创建工作薄对象
		    HSSFWorkbook wb=new HSSFWorkbook();
		    //创建工作表对象
		    HSSFSheet sheet = wb.createSheet();//这里也可以设置sheet的Name
		    //创建工作表的行
			HSSFRow row1 = sheet.createRow(0);//设置第一行，从零开始	
//			HSSFCell cell = row1.createCell((short) 0);// 创建行的单元格,也是从0开始

			row1.createCell((short) 0).setCellValue("学号");// 设置单元格内容,重载
			row1.createCell((short) 1).setCellValue("姓名");
			row1.createCell((short) 2).setCellValue("班级");
			row1.createCell((short) 3).setCellValue("分数");
			row1.createCell((short) 4).setCellValue("等级");
			row1.createCell((short) 5).setCellValue("转换分数");
		    wb.setSheetName(0,"Exam");//设置sheet的Name
			
		    for(int i=0;i<relist.size();i++) {	    	
		    	HSSFRow rowi = sheet.createRow((short) i+1);
		    	rowi.createCell((short) 0).setCellValue(relist.get(i).getId());// 设置单元格内容,重载
		    	rowi.createCell((short) 1).setCellValue(relist.get(i).getName());
		    	rowi.createCell((short) 2).setCellValue(relist.get(i).getProfession());
		    	rowi.createCell((short) 3).setCellValue(relist.get(i).getScore());
		    	rowi.createCell((short) 4).setCellValue(relist.get(i).getRank());
		    	rowi.createCell((short) 5).setCellValue(relist.get(i).getMapscore());
		    }
			wb.write(fos);
			//资源回收
			IOCloseWrite ic=new IOCloseWrite();
			ic.closeWrite(fos, osw, bw);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File was not found", "Fail", JOptionPane.INFORMATION_MESSAGE);;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Charset not supported", "Fail", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "IO error", "Fail", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
