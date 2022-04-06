package com.team4.service.user;

import com.team4.config.SingletonConnection;
import com.team4.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    Connection connection = SingletonConnection.getConnect();

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        String query = "{CALL selectAllUser()}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String name = resultSet.getString("name_user");
                User user = new User(id, name);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void insert(User user) {
        String query = "{CALL insertNewUser(?,?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, user.getName());
            callableStatement.setString(2, user.getPassword());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        String query = "{CALL getUserById(?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                user = new User(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowUser;
        String query = "{CALL deleteUserById(?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            rowUser = callableStatement.executeUpdate() > 0;
        }
        return rowUser;
    }

    @Override
    public boolean update(User user) throws SQLException {
        boolean rowUpdate;
        String query = "{CALL updateUser(?,?,?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getName());
            callableStatement.setString(3,  user.getPassword());
            rowUpdate = callableStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}
