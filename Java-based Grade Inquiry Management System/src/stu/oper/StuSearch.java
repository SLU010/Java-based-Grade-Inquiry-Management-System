package stu.oper;

import java.util.ArrayList;

import stu.base.Student;

public class StuSearch {
		public StuSearch(){
			
		}
		//显示所有
		public ArrayList<Student> showAll(ArrayList<Student> arlist) {
			return arlist;
		}//end of showall
		
		//搜索ID
		public ArrayList<Student> searchById(ArrayList<Student> arlist,String input) {
			ArrayList<Student> slist=new ArrayList<Student>();
			for (Student w:arlist){		
				if(w.getId().indexOf(input)!=-1)
					slist.add(w);	
			}
			return slist;
		}//end of searchbyid
		
		//搜索名字
		public ArrayList<Student> searchByName(ArrayList<Student> arlist,String input) {
			ArrayList<Student> slist=new ArrayList<Student>();
			for (Student w:arlist){		
				if(w.getName().indexOf(input)!=-1)
					slist.add(w);	
			}
			return slist;
		}//end of searchbyname
		
		//搜索分数
		public ArrayList<Student> searchByScore(ArrayList<Student> arlist,String input) {
			ArrayList<Student> slist=new ArrayList<Student>();
			for (Student w:arlist){		
				if(w.getScore().indexOf(input)!=-1)
					slist.add(w);	
			}
			return slist;
		}//end of searchbyname
				
}
