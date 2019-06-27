package heerak.model.vo;

public class EventDate {
	
	private String date;
	private int what_button ;
	
	public EventDate() {
		
	}

	public EventDate(String date, int what_button) {
		super();
		this.date = date;
		this.what_button = what_button;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getWhat_button() {
		return what_button;
	}

	public void setWhat_button(int what_button) {
		this.what_button = what_button;
	}

	@Override
	public String toString() {
		return "EventDate [date=" + date + ", what_button=" + what_button + "]";
	}
	
	
	
}
