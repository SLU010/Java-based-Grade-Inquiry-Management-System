package io.file;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

public class IOCloseWrite {
	public void closeWrite(FileOutputStream fos,OutputStreamWriter osw,BufferedWriter bw) {
		try {
			bw.close();
			osw.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "IO error", "Fail", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
