package io.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class IOCloseRead {
	
	public void closeRead(FileInputStream fis,InputStreamReader isr,BufferedReader br) {
		try {
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "IO error", "Fail", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
