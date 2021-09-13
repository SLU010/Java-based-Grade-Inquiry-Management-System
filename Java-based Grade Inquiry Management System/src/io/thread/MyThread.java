package io.thread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import stu.base.Student;



public class MyThread extends Thread{
	private ArrayList<Student> ar=new ArrayList<Student>();
	private String filename=new String();
	public ArrayList<Student> getAr() {
		return ar;
	}

	public void setAr(ArrayList<Student> ar) {
		this.ar = ar;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void run() {
		try {
			filename=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date().getTime());
			filename+=".txt";
			ObjectOutputStream ous=new ObjectOutputStream(new FileOutputStream("F:\\"+filename));
			for(int i=0;i<ar.size();i++) {
				ous.writeObject(ar.get(i));
			}
			ous.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "IO error", "Fail", JOptionPane.INFORMATION_MESSAGE);
		}	
	}


}
