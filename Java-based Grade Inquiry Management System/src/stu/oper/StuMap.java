package stu.oper;

import java.text.DecimalFormat;
import java.util.ArrayList;

import stu.base.Student;


public class StuMap {
	//构造函数
	public StuMap() {
		
	}
	//映射分数
	public ArrayList<Student> mapScore(ArrayList<Student> arlist,double d,double e,double f) {//传进来的为降序排好的数组
		int last1=(int) (arlist.size()*d);//第一段位的最后一个人的序号
		int last2=(int) (arlist.size()*e);//第二段位的最后一个人的序号
		int last3=(int) (arlist.size()*f);//第三段位的最后一个人的序号
		
		int first=0;//每个段位的第一个
		int last=0;//每个段位的最后一个
		
		double min=0;//原始分数段位最小值
		double max=0;//原始分数段位最大值
		
		double value=0;//映射前的成绩
		double mapvalue=0;//映射后的成绩
		
		double leftdouble=0;//double型的区间左端点
		double rightdouble=0;//double型的区间右端点
		String rank=new String();
		String left=new String();//String型的区间左端点
		String right=new String();//String型的区间右端点
		
		DecimalFormat df = new DecimalFormat("#");//四舍五入
		String s;
		
		//分了4个段 所以循环4次
		for(int j=0;j<4;j++) {
			//为每个段的计算公式做配置
			switch(j) {
			case 0:
				first=0;
				last=last1-1;//因为数组从0开始所以最后一位人的序号要减1
				rank=new String("A");
				left=new String("91");
				right=new String("100");
				break;
			case 1:
				first=last1;
				last=last2-1;
				rank=new String("B");
				left=new String("78");
				right=new String("90");
				break;
			case 2:
				first=last2;
				last=last3-1;
				rank=new String("C");
				left=new String("65");
				right=new String("77");
				break;
			case 3:
				first=last3;
				last=arlist.size()-1;
				rank=new String("D");
				left=new String("30");
				right=new String("64");
				break;
			}

			min=Double.valueOf(arlist.get(last).getScore());//原始分数段位最小值
			max=Double.valueOf(arlist.get(first).getScore());//原始分数段位最大值
			
			for(int i=first;i<last+1;i++) {
				value=Double.valueOf(arlist.get(i).getScore());
				leftdouble=Double.valueOf(left);
				rightdouble=Double.valueOf(right);
				arlist.get(i).setRank(rank);
				if(value==min) {
					arlist.get(i).setMapscore(left);
				}else if(value==max) {
					arlist.get(i).setMapscore(right);
				}else {
					mapvalue=(rightdouble*(value-min)+leftdouble*(max-value))/(max-min);
					s=df.format(mapvalue);
					arlist.get(i).setMapscore(s);
				}
			}

		}
		return arlist;
	}
	
	//找出不及格的人对应的下标(即JTable中的行号)
	public ArrayList<Integer> selectRow(ArrayList<Student> arlist) {
		ArrayList<Integer> row=new ArrayList<Integer>();
		double score=0;
		for(int i=0;i<arlist.size();i++) {
			score=Double.valueOf(arlist.get(i).getScore());
			if(score<60)
				row.add(i);
		}
		return row;
	}
}//end of class
