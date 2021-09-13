package io.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import stu.base.Student;


public class IORead {
	
	public ArrayList<Student> getFileData(String filepath) {
		ArrayList<Student> ar=new ArrayList<Student>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(filepath);
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
			BufferedReader br=new BufferedReader(isr);//带有缓冲区的高效读文件 BufferedReader行阅读器
			String line=null;
			
			while((line=br.readLine())!=null) {//文件的结尾是null
				String[] fld;
				if(line.indexOf("|")!=-1) {//判断分割符
					fld=line.split("\\|");
				}else {
					fld=line.split("\\s+");
				}
				if(line.indexOf("姓名")==-1) {//去表头
					Student s=new Student(fld[0],fld[1],fld[2],fld[3]);
					ar.add(s);
				}		
			}
			//资源回收
			IOCloseRead ic=new IOCloseRead();
			ic.closeRead(fis, isr, br);
			
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
		return ar;		
	}
}
