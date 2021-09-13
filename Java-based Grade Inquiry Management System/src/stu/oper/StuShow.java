package stu.oper;

import java.util.ArrayList;

import stu.base.Student;

public class StuShow {
	ArrayList<Student> relist=new ArrayList<Student>();
	StuSearch search=new StuSearch();//对Stu的查询显示操作
	
	//构造
	public StuShow(ArrayList<Student> relist){
		this.relist=relist;
	}
	public StuShow(){

	}
	
	//显示函数
	public ArrayList<Student> ShowStuInfo(String input,String searchflag,String sortorderflag,String sortkeyflag){
		StuSort sort=new StuSort(relist);//对Stu的排序操作
		if(input.length()==0) {//判断输入是否为空
			relist=search.showAll(relist);	
			sort.sortAscScore(relist);//为空时为成绩升序排序
		}else {
			//不为空则根据复选框条件查询
			if(searchflag.equals("by id")) {		
				relist=search.searchById(relist,input);	
			}else if(searchflag.equals("by name")) {
				relist=search.searchByName(relist,input);
				
			}else if(searchflag.equals("by score")) {
				relist=search.searchByScore(relist,input);							
			}
			relist=sort.allSort(relist,searchflag, sortorderflag, sortkeyflag);//不为空时根据关键字选择排序函数
		}
			
		return relist;
	}
}
