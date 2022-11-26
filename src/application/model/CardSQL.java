package application.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardSQL implements DBHandler {
	// singleton essentials
	private static CardSQL singleInstance = new CardSQL();

	private CardSQL() {
	};

	public static CardSQL getSingle() {
		return singleInstance;
	}

	public Card addCard(Course course, Card card) {
		try {
			PreparedStatement preparedStatement;
			String query = "INSERT INTO tbCards (courseID,cardQuestion,cardAnswer,learned) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // get value from autoincrement
			preparedStatement.setInt(1, course.getID());
			preparedStatement.setString(2, card.getQuestion());
			preparedStatement.setString(3, card.getAnswer());
			preparedStatement.setInt(4, convertBooleanToInt(card.isLearned()));

			preparedStatement.executeUpdate();

			return new Card(card.getQuestion(), card.getAnswer(), card.isLearned(),
					preparedStatement.getGeneratedKeys().getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int convertBooleanToInt(boolean bool) {
		return bool ? 1 : 0;
	}

	private boolean convertIntToBoolean(int i) {
		return i == 1;
	}

	public List<Card> getAllCardsFromCourse(Course c) {
		List<Card> cards = new ArrayList<>();
		try {
			PreparedStatement preparedStatement;
			String query = "SELECT * FROM tbCards WHERE courseID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, c.getID());
			ResultSet cardInfo = preparedStatement.executeQuery();

			while (cardInfo.next())
				cards.add(new Card(cardInfo.getString("cardQuestion"), cardInfo.getString("cardAnswer"),
						convertIntToBoolean(cardInfo.getInt("learned")), cardInfo.getInt("ID")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cards;
	}

	public void updateCard(Card card) {
		try {
			PreparedStatement preparedStatement;
			String query = "UPDATE tbCards SET cardQuestion = ?, cardAnswer = ?, learned = ? WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, card.getQuestion());
			preparedStatement.setString(2, card.getAnswer());
			preparedStatement.setInt(3, convertBooleanToInt(card.isLearned()));
			preparedStatement.setInt(4, card.getID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCard(Card card) {
		try {
			PreparedStatement preparedStatement;
			String query = "DELETE FROM tbCards WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, card.getID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
