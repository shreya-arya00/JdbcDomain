package repository;

import exception.ProductException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseUtil {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/skumari";
    private static final String USERNAME = "skumari";
    private static final String PASSWORD = "skumari";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void execute(String query, Object... args) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters
            setParameters(statement, args);

            // Execute update or query
            if (query.trim().toLowerCase().startsWith("select")) {
                statement.executeQuery();
            } else {
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            // Log or print stack trace
            e.printStackTrace();
            throw new ProductException("Error executing SQL query: " + query, e);
        }
    }

    public static <T> T findOne(String query, Function<ResultSet, T> mapper, Object... args) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, args);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.apply(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new ProductException("Error executing SQL query: " + query, e);
        }
        return null;
    }

    public static <T> List<T> findMany(String query, Function<ResultSet, T> mapper, Object... args) {
        List<T> results = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, args);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.apply(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new ProductException("Error executing SQL query: " + query, e);
        }
        return results;
    }

    private static void setParameters(PreparedStatement statement, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }
    }
}
