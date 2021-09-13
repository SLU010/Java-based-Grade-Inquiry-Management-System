package stu.base;

import java.io.Serializable;


public class Student implements Serializable{
	    
		private String id;
		private String name;
		private String profession;
		private String score;
		private String mapscore;
		private String rank;
		
	public Student() {	
		
	}
	 
	public Student( String id, String name, String profession, String score) {
			this.id=id;
			this.name=name;
			this.profession=profession;
			this.score=score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMapscore() {
		return mapscore;
	}

	public void setMapscore(String mapscore) {
		this.mapscore = mapscore;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String toString() {
		return this.getId()+" "+this.getName()+" "+this.getProfession()+" "+this.getScore()+" "+this.getRank()+" "+this.getMapscore();
	}
	
}