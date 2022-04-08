package com.team4.service.transaction;

import com.team4.config.SingletonConnection;
import com.team4.model.Category;
import com.team4.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.team4.config.SingletonConnection.getConnect;

public class TransactionService implements ITransactionService{

    public TransactionService() {
    }

    public List<Transaction> selectAll() {
        List<Transaction> transactionList = new ArrayList<>();
        try(Connection connection = SingletonConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from transaction;");){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_transaction");
                String name = resultSet.getString("name_transaction");
                transactionList.add(new Transaction(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return transactionList;
    }


    public void insert(Transaction transaction) {
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into transaction(name_transaction) values (?)")) {
            preparedStatement.setString(1, transaction.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Transaction getById(int id) {
        Transaction transaction = null;
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from transaction where id_transaction = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name_transaction");
                transaction = new Transaction(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return transaction;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement statement = connection.prepareStatement("delete from transaction where id_transaction = ?;")) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            }
        return rowDeleted;
    }

    @Override
    public boolean update(Transaction transaction) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement statement = connection.prepareStatement("update transaction set name_transaction = ? where id_transaction = ?");) {
            statement.setString(1, transaction.getName());
            statement.setInt(2, transaction.getId());
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

//    public User selectUser(int id) {
//        User user = null;
//        // Step 1: Establishing a Connection
//        try (Connection connection = getConnection();
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
//            preparedStatement.setInt(1, id);
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id, name, email, country);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return user;
//    }
//
//    public List<User> selectAllUsers() {
//
//        // using try-with-resources to avoid closing resources (boiler plate code)
//        List<User> users = new ArrayList<>();
//        // Step 1: Establishing a Connection
//        try (Connection connection = getConnection();
//
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                users.add(new User(id, name, email, country));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return users;
//    }
//
//    public boolean deleteUser(int id) throws SQLException {
//        boolean rowDeleted;
//        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
//            statement.setInt(1, id);
//            rowDeleted = statement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//
//    public boolean updateUser(User user) throws SQLException {
//        boolean rowUpdated;
//        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
//            statement.setString(1, user.getName());
//            statement.setString(2, user.getEmail());
//            statement.setString(3, user.getCountry());
//            statement.setInt(4, user.getId());
//
//            rowUpdated = statement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//
//    public User getUserById(int id) {
//        User user = null;
//        String query = "{CALL get_user_by_id(?)}";
//        // Step 1: Establishing a Connection
//        try (Connection connection = getConnection();
//             // Step 2:Create a statement using connection object
//             CallableStatement callableStatement = connection.prepareCall(query);) {
//            callableStatement.setInt(1, id);
//            // Step 3: Execute the query or update query
//            ResultSet rs = callableStatement.executeQuery();
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id, name, email, country);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return user;
