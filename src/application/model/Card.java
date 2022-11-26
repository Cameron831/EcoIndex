package application.model;

public class Card {
	private String question;
	private String answer;
	private boolean learned;

	private int ID;

	public Card(String question, String answer, boolean learned) {
		this.question = question;
		this.answer = answer;
		this.learned = learned;
		this.ID = -1;

	}

	public Card(String question, String answer, boolean learned, int ID) {
		this.question = question;
		this.answer = answer;
		this.learned = learned;
		this.ID = ID;
	}

	int getID() {
		return ID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isLearned() {
		return learned;
	}

	public boolean setLearned(boolean learned) {
		boolean change = !(learned && this.learned);
		this.learned = learned;
		return change;
	}

	public Card addCard(Course course) {
		CardSQL db = CardSQL.getSingle();
		return db.addCard(course, this);
	}

	public void updateCard() {
		CardSQL db = CardSQL.getSingle();
		db.updateCard(this);
	}

	public void deleteCard() {
		CardSQL db = CardSQL.getSingle();
		db.deleteCard(this);
	}
}
