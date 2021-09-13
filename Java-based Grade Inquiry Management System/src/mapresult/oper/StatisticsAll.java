package mapresult.oper;

import java.util.ArrayList;

import mapresult.base.MapResult;
import stu.base.Student;

public class StatisticsAll {
	ArrayList<Student> arlist=new ArrayList<Student>();
	ArrayList<MapResult> mapresult=new ArrayList<MapResult>();
	public StatisticsAll(ArrayList<Student> arlist) {
		this.arlist=arlist;
	}
	
	public ArrayList<MapResult> statisticsAll() {
		
		StatisticsOneSection sta=new StatisticsOneSection(arlist);//统计
		MapResult m=new MapResult();
		m=sta.staticresult("A",0.1,0.5,0.9);
		mapresult.add(m);
		m=new MapResult();
		m=sta.staticresult("B",0.1,0.5,0.9);
		mapresult.add(m);
		m=new MapResult();
		m=sta.staticresult("C",0.1,0.5,0.9);
		mapresult.add(m);
		m=new MapResult();
		m=sta.staticresult("D",0.1,0.5,0.9);
		mapresult.add(m);
		
		return mapresult;
	}
}
