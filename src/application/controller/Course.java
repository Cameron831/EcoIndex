package application.controller;

public class Course {
	private String name;
	private int numCards;
	private int learnedTotal;
	
	// todo: implement index cards later
	
	public Course(String name) {
		this.name = name;
		
		// temporary for now
		this.numCards = 0;
		this.learnedTotal = 0;
	}

	public int getNumCards() {
		return numCards;
	}

	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	public int getLearnedTotal() {
		return learnedTotal;
	}

	public void setLearnedTotal(int learnedTotal) {
		this.learnedTotal = learnedTotal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
