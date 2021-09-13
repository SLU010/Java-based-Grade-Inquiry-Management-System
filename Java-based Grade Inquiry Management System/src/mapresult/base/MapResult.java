package mapresult.base;

public class MapResult {
	private String rank;
	private String section;
	private String number;
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString() {
		return this.getRank()+" "+this.getSection()+" "+this.getNumber();
	}
}
