package application.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String name;
	private String description;
	private int numCards;
	private int learnedTotal = 0;
	private List<Card> cards = new ArrayList<>();

	public List<Card> getCards() {
		return cards;
	}

	private int ID;

	// creating a brand new course with no cards
	public Course(String name, String desc) {
		this.name = name;
		this.description = desc;
		this.numCards = 0;
		this.learnedTotal = 0;
		this.ID = -1;
	}

	// retrieving old courses saved from database
	public Course(String name, String description, String numCards, int ID) {
		this.name = name;
		this.description = description;
		this.numCards = Integer.parseInt(numCards);
		this.ID = ID;
		// TODO need to add learned
	}

	public Course(String name, String description, int numCards, int ID) {
		this.name = name;
		this.description = description;
		this.numCards = numCards;
		this.ID = ID;
		// TODO need to add learned
	}

	int getID() {
		return ID;
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

	public void updateCourse() {
		CourseSQL courseDB = CourseSQL.getSingle();
		courseDB.updateCourse(this);
	}

	public Course addCourse(User u) {
		CourseSQL courseDB = CourseSQL.getSingle();
		return courseDB.addCourse(u, this);
	}

	public Card addCard(Card tempCard) {
		Card newCard = tempCard.addCard(this);
		cards.add(newCard);
		numCards++;
		return newCard;
	}

	public void initializeCards() {
		CardSQL db = CardSQL.getSingle();
		cards = db.getAllCardsFromCourse(this);
//		numCards = cards.size();
//		for (Card i : cards)
//			if (i.isLearned())
//				learnedTotal++;
		// TODO Auto-generated method stub
		
	}
}
