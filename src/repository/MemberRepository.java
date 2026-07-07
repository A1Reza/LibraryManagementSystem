package repository;

import exception.MemberNotFoundException;
import model.Member;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepository {

    public void registerMember(Member member) {

        String sql = "INSERT INTO member(full_name, phone) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setString(2, member.getPhone());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Member registered successfully.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error registering member.", e);
        }
    }

    public Member findMemberById(int id) throws MemberNotFoundException {

        String sql = "SELECT * FROM member WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return new Member(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("phone")
                );
            }

            throw new MemberNotFoundException("Member with id " + id + " not found.");

        } catch (SQLException e) {
            throw new RuntimeException("Error finding member.", e);
        }
    }

    public void deleteMember(int id) throws MemberNotFoundException {

        String sql = "DELETE FROM member WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            if (rows == 0) {
                throw new MemberNotFoundException("Member with id " + id + " not found.");
            }

            System.out.println("Member deleted successfully.");

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting member.", e);
        }
    }
}