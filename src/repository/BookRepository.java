package repository;

import model.Book;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepository {

    public void insertBook(Book book) {
        String sql = "INSERT INTO book(title, author, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getStock());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting book.");
            e.printStackTrace();
        }
    }
}
