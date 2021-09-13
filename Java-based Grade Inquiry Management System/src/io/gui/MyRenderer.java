package io.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyRenderer implements TableCellRenderer {
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	ArrayList<Integer> row=new ArrayList<Integer>();
	
	//构造函数 实例化时传入不及格人员的下标
	public MyRenderer(ArrayList<Integer> row) {
		this.row=row;
	}
	//
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
		Color foreground=Color.BLACK, background=Color.WHITE;//设置初始化颜色 白底黑字
		int i=0;
		if(this.row.size()==0)
			return renderer;//没有不及格人员时直接返回
		while(true) {
			//将表格中的行与不及格的行数组进行比较 将不及格的行标记为 白底红字
			if (row==this.row.get(i)){
				foreground = Color.red;
				background = Color.WHITE;
			}
			i++;
			//比较次数为不及格人数
			if(i>this.row.size()-1)
				break;
		}
		//设置前景色和背景色
		renderer.setForeground(foreground);
		renderer.setBackground(background);

		return renderer;
	}
}
