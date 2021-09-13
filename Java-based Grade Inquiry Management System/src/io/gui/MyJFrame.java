package io.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import io.file.IORead;
import io.file.IOWrite;
import io.thread.MyThread;
import mapresult.base.MapResult;
import mapresult.oper.StatisticsAll;
import stu.base.Student;
import stu.oper.StuMap;
import stu.oper.StuShow;
import stu.oper.StuSort;

public class MyJFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	
	private Timer time;//设置时间状态栏
	private Cursor cs;//在不同菜单下更换鼠标
	
	private String searchflag=new String("by id");//判断搜索条件
	private String sortorderflag=new String("ascending");//判断升降排序
	private String sortkeyflag=new String("name");//判断排序关键字
	//private String mapflag=new String("original");//判断显示映射成绩或原始成绩
	
	private ArrayList<Student> relist=new ArrayList<Student>();
	private ArrayList<Student> list=new ArrayList<Student>();
	private ArrayList<Integer> row=new ArrayList<Integer>();//保存不及格人员下标
	private ArrayList<MapResult> mapresult=new ArrayList<MapResult>();

	private IORead ir=new IORead();//打开
	private IOWrite iw=new IOWrite();//保存
	private StuSort sort=new StuSort();//对Stu的排序操作
	private StuMap map=new StuMap();//对Stu的成绩映射操作
	private StuShow show=new StuShow();//对Stu的显示操作
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyJFrame frame = new MyJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyJFrame() {
		setTitle("Query System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 454);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmOpen.setCursor(cs);
		mnFile.add(mntmOpen);
		
		JMenu mnSave = new JMenu("Save As");
		mnFile.add(mnSave);
		
		JMenuItem mntmTxt = new JMenuItem("TXT");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmTxt.setCursor(cs);
		mnSave.add(mntmTxt);
		
		JMenuItem mntmExcel = new JMenuItem("EXCEL");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmExcel.setCursor(cs);
		mnSave.add(mntmExcel);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		cs=new Cursor(Cursor.HAND_CURSOR);
		cs=new Cursor(Cursor.HAND_CURSOR);
		
		JMenu mnSort = new JMenu("Sort");
		menuBar.add(mnSort);
		
		JMenu mnDisplay = new JMenu("Order");
		mnSort.add(mnDisplay);
		
		JMenuItem mntmAscendingOrder = new JMenuItem("Ascending order");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmAscendingOrder.setCursor(cs);
		mnDisplay.add(mntmAscendingOrder);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Descending order");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmNewMenuItem.setCursor(cs);
		mnDisplay.add(mntmNewMenuItem);
		
		JMenu mnKeyWords = new JMenu("Key words");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mnKeyWords.setCursor(cs);
		mnSort.add(mnKeyWords);
		
		JMenuItem mntmName = new JMenuItem("Name");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmName.setCursor(cs);
		mnKeyWords.add(mntmName);
		
		JMenuItem mntmScore = new JMenuItem("Score");
		cs=new Cursor(Cursor.HAND_CURSOR);
		mntmScore.setCursor(cs);
		mnKeyWords.add(mntmScore);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Wizards");
		mnHelp.add(mntmNewMenuItem_1);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 487, 157);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(68, 40, 222, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("search");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(300, 38, 97, 23);
		contentPane.add(btnNewButton);
		cs=new Cursor(Cursor.HAND_CURSOR);//更改鼠标
		btnNewButton.setCursor(cs);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "by id", "by name", "by score"}));
		comboBox.setBounds(407, 39, 90, 21);
		contentPane.add(comboBox);
		
		JLabel lblPleaseInputKey = new JLabel("please input key words:");
		lblPleaseInputKey.setFont(new Font("Bell MT", Font.PLAIN, 16));
		lblPleaseInputKey.setBounds(10, 15, 166, 15);
		contentPane.add(lblPleaseInputKey);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 363, 487, 21);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//左下角显示状态栏
		JLabel lblNewLabel = new JLabel("Status Bar");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblNewLabel.setBounds(0, 0, 263, 15);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLUE);
		
		//右下角显示时间
		final JLabel timelabel = new JLabel("");
		timelabel.setForeground(Color.BLUE);
		timelabel.setBounds(273, 0, 204, 15);
		panel.add(timelabel);
		
		timelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));			
		timelabel.setForeground(Color.BLUE);		
		
		JLabel lblMappingInterval = new JLabel("Statistics:");
		lblMappingInterval.setFont(new Font("Bell MT", Font.PLAIN, 16));
		lblMappingInterval.setBounds(10, 238, 67, 15);
		contentPane.add(lblMappingInterval);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 263, 487, 90);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		time = new Timer(1000,new ActionListener() {
			@Override		
			public void actionPerformed(ActionEvent arg0) {		
				timelabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));	
			}			
		});			
		time.start();	
		
		//actions
		//打开文件
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf=new JFileChooser();
				if(jf.showOpenDialog(MyJFrame.this)==JFileChooser.APPROVE_OPTION) {
					String filepath=jf.getSelectedFile().getAbsolutePath();
					relist=ir.getFileData(filepath);
					JOptionPane.showMessageDialog(null, "File Opened", "Success", JOptionPane.INFORMATION_MESSAGE);
					lblNewLabel.setText("File Opened");
				}
			}
		});
		//TXT保存
		mntmTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf=new JFileChooser();
				if(jf.showSaveDialog(MyJFrame.this)==JFileChooser.APPROVE_OPTION) {
					String filepath=jf.getSelectedFile().getAbsolutePath();					
					iw.setFileDataToTxt(filepath,list);
					JOptionPane.showMessageDialog(null, "File saved as TXT", "Success", JOptionPane.INFORMATION_MESSAGE);
					lblNewLabel.setText("File Saved");
				}
			}
		});
		//EXCEL保存
		mntmExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf=new JFileChooser();
				if(jf.showSaveDialog(MyJFrame.this)==JFileChooser.APPROVE_OPTION) {
					String filepath=jf.getSelectedFile().getAbsolutePath();					
					iw.setFileDataToExcel(filepath,list);
					JOptionPane.showMessageDialog(null, "File saved as Excel", "Success", JOptionPane.INFORMATION_MESSAGE);
					lblNewLabel.setText("File Saved");
				}
			}
		});
		//搜索按钮
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(relist.size()!=0) {
					list=relist;//复原
					MyThread t1=new MyThread();//实例化线程对象
					String input=textField.getText().trim();
					//设置两个表头
					Vector<String> v=new Vector<String>();
					v.add("id");
					v.add("name");
					v.add("class");
					v.add("score");
					v.add("rank");
					v.add("mapscore");
					DefaultTableModel df=new DefaultTableModel(v,0);
					
					Vector<String> v1=new Vector<String>();
					v1.add("rank");
					v1.add("section");
					v1.add("number");
					DefaultTableModel df1=new DefaultTableModel(v1,0);
					//设置表头结束
					sort.sortDownScore(list);//讲数组降序排好再进行分数映射
					map.mapScore(list, 0.1,0.5,0.9);	//分数映射
						
					StatisticsAll sa=new StatisticsAll(list);
					mapresult=sa.statisticsAll();//统计所有映射区间(A-D)
								
					for(MapResult ma:mapresult) {
						String[] line= {ma.getRank(),ma.getSection(),ma.getNumber()};
						df1.addRow(line);
					}
					table_1.setModel(df1);
					
					show=new StuShow(list);
					list=show.ShowStuInfo(input, searchflag, sortorderflag, sortkeyflag);//根据所有标志位获得查询结果准备显示
								
					//搜索结果不为空时启动线程自动保存数据
					if(list.size()!=0) {
						t1.setAr(list);
						t1.setFilename("");//清空字符串 相当于重命名
						t1.start();
						
						for(Student s:list) {
							String[] line= {s.getId(),s.getName(),s.getProfession(),s.getScore(),s.getRank(),s.getMapscore()};
							df.addRow(line);
						}
						table.setModel(df);
						lblNewLabel.setText("Now Sort by "+sortkeyflag+" in "+sortorderflag+" order");//更新状态栏
					
					}else {//当无查询结果时在状态栏中进行提示
						lblNewLabel.setText("No query results");
						table.setModel(df);
					}	
					
					row=map.selectRow(list);//找出不及格的人对应的下标
					table.setDefaultRenderer(Object.class,new MyRenderer(row));//将表格对应行字体设置为红色 
				}
			}
		});//按钮响应结束
		
		//复选框
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					if(e.getItem().equals("by id")) {
						searchflag="by id";
						//按照学号查询时按照成绩升序排序
						sortkeyflag="score";
						sortorderflag="ascending";
					}else if(e.getItem().equals("by name")) {
						searchflag="by name";
						//按照姓名查询时按照姓名升序排序
						sortkeyflag="name";
						sortorderflag="descending";
					}else if(e.getItem().equals("by score")) {
						searchflag="by score";
					}
				}
			}
		});
		//按姓名排序
		mntmName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortkeyflag="name";
				lblNewLabel.setText("Now Sort by "+sortkeyflag+" in "+sortorderflag+" order");
			}
		});
		//按成绩排序
		mntmScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortkeyflag="score";
				lblNewLabel.setText("Now Sort by "+sortkeyflag+" in "+sortorderflag+" order");
			}
		});
		//升序排序
		mntmAscendingOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortorderflag="ascending";
				lblNewLabel.setText("Now Sort by "+sortkeyflag+" in "+sortorderflag+" order");
			}
		});
		//降序排序
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortorderflag="descending";
				lblNewLabel.setText("Now Sort by "+sortkeyflag+" in "+sortorderflag+" order");
			}
		});
		//使用帮助
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This is a score query system.\r\nYou can query by name, student number and score, or you can select sorting method.\r\n In addition, students who fail will be marked. ", "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}//end of MyJFrame
	
}//end of class
