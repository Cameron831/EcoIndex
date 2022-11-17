package application.model;

public class Course {
	private String name;
	private String description;
	private int numCards;
	private int learnedTotal;

	// creating a brand new course with no cards
	public Course(String name, String desc) {
		this.name = name;
		this.description = desc;
		this.numCards = 0;
		this.learnedTotal = 0;
	}
	
	// retrieving old courses saved from database
	public Course(String name, String description, String numCards) {
		this.name = name;
		this.description = description;		
		this.numCards = Integer.parseInt(numCards);
		// TODO need to add learned
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "name: " + name + ", description: " + description + ", card numbers: " + numCards;
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
