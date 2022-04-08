package com.team4.service.catalog;

import com.team4.config.SingletonConnection;
import com.team4.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    private static final String INSERT_CATALOG_SQL = "INSERT INTO category (name_category,note) VALUES (?,?);";
    private static final String SELECT_CATALOG_BY_ID = "select id_category,name_category,note from category where id_category =?";
    private static final String SELECT_ALL_CATALOG = "select * from category";
    private static final String DELETE_CATALOG_SQL = "delete from category where id_category = ?;";
    private static final String UPDATE_CATALOG_SQL = "update category set name_category = ?, note = ? where id_category = ?;";

    public CategoryService(){}

    public void insertCategory(Category category) throws SQLException {
        System.out.println(INSERT_CATALOG_SQL);
        try(Connection connection = SingletonConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATALOG_SQL);
        ) {
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getNote());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public Category selectCategory(int id) {
        Category category = null;
        // Step 1: Establishing a Connection
        try (Connection connection = SingletonConnection.getConnect();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATALOG_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name_category");
                String note = rs.getString("note");

                category = new Category(id,name,note);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    public List<Category> selectAllCategory() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Category> category = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = SingletonConnection.getConnect();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATALOG);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_category");
                String name = rs.getString("name_category");
                String note = rs.getString("note");

                category.add(new Category(id,name,note));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    public boolean deleteCategory(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = SingletonConnection.getConnect(); PreparedStatement statement = connection.prepareStatement(DELETE_CATALOG_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCategory(Category catalog) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = SingletonConnection.getConnect(); PreparedStatement statement = connection.prepareStatement(UPDATE_CATALOG_SQL);) {
            statement.setString(1, catalog.getName());
            statement.setString(2, catalog.getNote());

            statement.setInt(3, catalog.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
