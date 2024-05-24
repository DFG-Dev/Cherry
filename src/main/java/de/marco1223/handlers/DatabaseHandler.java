package de.marco1223.handlers;

import de.marco1223.interfaces.IDatabaseHandler;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler implements IDatabaseHandler {

    private Connection connection;

    @Override
    public void connect(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            JDALogger.getLog(DatabaseHandler.class).info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            JDALogger.getLog(DatabaseHandler.class).error("Failed to connect to the PostgreSQL server. Following error occurred:" + e.getMessage());
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                JDALogger.getLog(DatabaseHandler.class).info("Database connection closed.");
            }
        } catch (SQLException e) {
            JDALogger.getLog(DatabaseHandler.class).error("Failed to close the database connection. Following error occurred:" + e.getMessage());
        }
    }

    @Override
    public void query(String sql) {
        try (Statement statement = connection.createStatement()) {
            JDALogger.getLog(DatabaseHandler.class).info("Query executed successfully. Query: " + sql);
        } catch (SQLException e) {
            JDALogger.getLog(DatabaseHandler.class).error("Failed to execute the query. Following error occurred:" + e.getMessage());
        }
    }

    @Override
    public List<String> fetchData(String sql) {
        List<String> results = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String row = resultSet.getString(1);
                results.add(row);
            }
            JDALogger.getLog(DatabaseHandler.class).info("Data fetched successfully. Query: " + sql);
        } catch (SQLException e) {
            JDALogger.getLog(DatabaseHandler.class).error("Failed to fetch data. Following error occurred:" + e.getMessage());
        }
        return results;
    }

    @Override
    public DatabaseMetaData getPostgresMetadata() {
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            JDALogger.getLog(DatabaseHandler.class).error("Failed to get PostgreSQL version. Following error occurred:" + e.getMessage());
            return null;
        }
    }

}
