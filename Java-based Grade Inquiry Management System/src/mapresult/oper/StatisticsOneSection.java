package mapresult.oper;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mapresult.base.MapResult;
import stu.base.Student;
import stu.oper.StuSort;

public class StatisticsOneSection {
	ArrayList<Student> arlist=new ArrayList<Student>();
	ArrayList<MapResult> maplist=new ArrayList<MapResult>();
	public StatisticsOneSection(ArrayList<Student> arlist) {
		this.arlist=arlist;
	}
	
	public MapResult staticresult(String rank,double d,double e,double f) {
		double min=0;//原始分数段位最小值
		double max=0;//原始分数段位最大值
		
		StuSort sort=new StuSort();
		sort.sortDownScore(arlist);
		
		MapResult m=new MapResult();
		m.setRank(rank);
		DecimalFormat df = new DecimalFormat("#");
		DecimalFormat df1 = new DecimalFormat("#");
		DecimalFormat df2 = new DecimalFormat("#");
		String s,smin,smax;
		String section;
		int count=0;
		int j=0;
		for(int i=0;i<arlist.size();i++) {
			if(arlist.get(i).getRank().indexOf(rank)!=-1) {
				count++;				
				if(count==1) {
					j=i;//记下从第几个开始用于计算最小值
					max=Double.valueOf(arlist.get(i).getScore());//原始分数段位最大值
				}
			}
		}
		min=Double.valueOf(arlist.get(j+count-1).getScore());//原始分数段位最大值
		s=df.format(count);
		m.setNumber(s);
		smin=df1.format(min);
		smax=df2.format(max);
		section="["+smin+","+smax+"]";
		m.setSection(section);
		return m;
		
	}//end of fun
	
	
}//end of class
