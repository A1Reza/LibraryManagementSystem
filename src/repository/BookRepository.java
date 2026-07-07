package repository;

import model.Book;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Book findBookById(int id) throws BookNotFoundException {

        String sql = "SELECT * FROM book WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                );
            }

            throw new BookNotFoundException("Book with id " + id + " not found.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBookPrice(int id, double newPrice) throws BookNotFoundException {

        String sql = "UPDATE book SET price = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2, id);

            int rows = preparedStatement.executeUpdate();

            if (rows == 0) {
                throw new BookNotFoundException("Book with id " + id + " not found.");
            }

            System.out.println("Book price updated successfully.");

        } catch (SQLException e) {
            throw new RuntimeException("Error updating book price.", e);
        }
    }

    public void deleteBook(int id) throws BookNotFoundException {

        String sql = "DELETE FROM book WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            if (rows == 0) {
                throw new BookNotFoundException("Book with id " + id + " not found.");
            }

            System.out.println("Book deleted successfully.");

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book.", e);
        }
    }



}
