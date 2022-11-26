package application.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String name;
	private String description;
	private int numCards;
	private int learnedTotal;
	private List<Card> cards = new ArrayList<>();

	private int ID;

	// package new course information
	public Course(String name, String desc) {
		this.name = name;
		this.description = desc;
		this.numCards = 0;
		this.learnedTotal = 0;
		this.ID = -1;
	}

	public Course(String name, String description, int numCards, int learnedTotal, int ID) {
		this.name = name;
		this.description = description;
		this.numCards = numCards;
		this.learnedTotal = learnedTotal;
		this.ID = ID;
	}

	public List<Card> getCards() {
		return cards;
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
		return cards.size();
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

	public void updateLearnedSingle(boolean b) {
		learnedTotal += b ? 1 : -1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean updateCourse() {
		CourseSQL courseDB = CourseSQL.getSingle();
		return courseDB.updateCourse(this);
	}

	public Course addCourse(User u) {
		CourseSQL courseDB = CourseSQL.getSingle();
		return courseDB.addCourse(u, this);
	}

	public Card addCard(Card tempCard) {
		Card newCard = tempCard.addCard(this);
		cards.add(newCard);

		if (tempCard.isLearned())
			learnedTotal++;

		return newCard;
	}

	public void initializeCards() {
		CardSQL db = CardSQL.getSingle();
		cards = db.getAllCardsFromCourse(this);
	}

	public void deleteCard(Card card) {
		cards.remove(card);
		if (card.isLearned())
			learnedTotal--;
		card.deleteCard();
	}

	public void deleteCourse() {
		for (Card c : cards)
			c.deleteCard();

		CourseSQL db = CourseSQL.getSingle();
		db.deleteCourse(this);
	}
}
