package stu.oper;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import stu.base.Student;

public class StuSort {
		//姓名升序排序
	ArrayList<Student> arlist=new ArrayList<Student>();
	public StuSort(ArrayList<Student> relist) {
		this.arlist=relist;
	}
	public StuSort() {
	
	}
	public void sortAscName(ArrayList<Student> arlist) {//这里不用成员变量arlist，否则排序时需要点击两次Search，即重新创建对象后才能更新排序结果
		//按照中文拼音排序
		Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
		//外部比较器
		Collections.sort(arlist,new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				//return o1.score-o2.score;
				String compare1 = o1.getName();
				String compare2 = o2.getName();
				int flags = 0;
		        if (cmp.compare(compare1,compare2) < 0) {
		        	flags = -1;
		        }
		        else if(cmp.compare(compare1,compare2) > 0) {
		        	flags = 1;
		        }
		        else {
		        	flags = 0;
		        }
		        return flags;
			}			
		});
	}//end of AscSort
	
	//姓名降序排序
	public void sortDownName(ArrayList<Student> arlist) {
		//按照中文拼音排序
		Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
		//外部比较器
		Collections.sort(arlist,new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				//return o1.score-o2.score;
				String compare1 = o1.getName();
				String compare2 = o2.getName();
				int flags = 0;
		        if (cmp.compare(compare1,compare2) < 0) {
		        	flags = 1;
		        }
		        else if(cmp.compare(compare1,compare2) > 0) {
		        	flags = -1;
		        }
		        else {
		        	flags = 0;
		        }
		        return flags;
			}			
		});

	}//end of DownSort
		
	//成绩降序排序
	public void sortDownScore(ArrayList<Student> arlist) {
		Collections.sort(arlist,new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				double value1=Double.valueOf(o1.getScore());
				double value2=Double.valueOf(o2.getScore());
				int flags = 0;
				if(value1>value2)
					flags = -1;
				else if(value1<value2)
					flags = 1;
				else
					flags=0;
				return flags;
			}
		});
		
	}
	
	public void sortAscScore(ArrayList<Student> arlist) {
		Collections.sort(arlist,new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				double value1=Double.valueOf(o1.getScore());
				double value2=Double.valueOf(o2.getScore());
				int flags = 0;
				if(value1>value2)
					flags = 1;
				else if(value1<value2)
					flags = -1;
				else
					flags=0;
				return flags;
			}
		});
		
	}
	
	//汇总以上的排序函数 根据输入的关键标志对输入数组排序
	public ArrayList<Student> allSort(ArrayList<Student> arlist,String searchflag,String sortorderflag,String sortkeyflag) {
		if(sortkeyflag.equals("score")) {//按分数排序
			if(sortorderflag.equals("ascending"))
				this.sortAscScore(arlist);
			else
				this.sortDownScore(arlist);
		}else if(sortkeyflag.equals("name")){//按姓名排序
			if(sortorderflag.equals("ascending"))
				this.sortAscName(arlist);
			else
				this.sortDownName(arlist);
		}
		return arlist;
	}
}
