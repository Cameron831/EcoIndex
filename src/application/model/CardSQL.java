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
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, course.getID());
			preparedStatement.setString(2, card.getQuestion());
			preparedStatement.setString(3, card.getAnswer());
			preparedStatement.setInt(4, convertBooleanToInt(card.isLearned()));

			preparedStatement.executeUpdate();

			return new Card(card.getQuestion(), card.getAnswer(), card.isLearned(),
					preparedStatement.getGeneratedKeys().getInt(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("adding card broke");
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
						convertIntToBoolean(cardInfo.getInt("learned")), 
						cardInfo.getInt("ID")
						));
//				courses.add(new Course(courseInfo.getString("courseName"), courseInfo.getString("courseDescription"),
//						courseInfo.getString("cardNums"), courseInfo.getInt("ID")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cards;
	}
}
