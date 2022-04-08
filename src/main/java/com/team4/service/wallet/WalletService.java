package com.team4.service.wallet;

import com.team4.config.SingletonConnection;
import com.team4.model.CurrencyWallet;
import com.team4.model.User;
import com.team4.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletService implements IWalletService {
    Connection connection = SingletonConnection.getConnect();

    @Override
    public List<Wallet> selectAllWalletById(int idUser) {
        List<Wallet> wallets = new ArrayList<>();
        String query = "{CALL selectAllWalletById(?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, idUser);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String nameWallet = resultSet.getString("name_wallet");
                double amount = resultSet.getDouble("current_amount");
                String currency = resultSet.getString("name_currency");
                String nameUser = resultSet.getString("name_user");
                CurrencyWallet currencyWallet = new CurrencyWallet(currency);
                User user = new User(nameUser);
                Wallet wallet = new Wallet(nameWallet, amount, user, currencyWallet);
                wallets.add(wallet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }

    @Override
    public List<Wallet> selectAll() {
        List<Wallet> wallets = new ArrayList<>();
        String query = "{CALL selectAllWallet()}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_wallet");
                String name = resultSet.getString("name_wallet");
                double currentAmount = resultSet.getDouble("current_amount");
                String description = resultSet.getString("description");
                int id_cur = resultSet.getInt("id_currency");
                String name_cur = resultSet.getString("name_currency");
                int id_user = resultSet.getInt("id_user");
                String name_user = resultSet.getString("name_user");
                CurrencyWallet currencyWallet = new CurrencyWallet(id_cur, name_cur);
                User user = new User(id_user, name_user);
                Wallet wallet = new Wallet(id, name, currentAmount, description, user, currencyWallet);
                wallets.add(wallet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }

    @Override
    public void insert(Wallet wallet) {
        String query = "{CALL insertNewWallet(?,?,?,?,?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, wallet.getName());
            callableStatement.setInt(2, wallet.getCurrencyWallet().getId());
            callableStatement.setInt(3, wallet.getUser().getId());
            callableStatement.setDouble(4, wallet.getBalance());
            callableStatement.setString(5, wallet.getDescription());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Wallet getById(int id) {
        Wallet wallet = null;
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("select id_wallet, name_wallet from wallet where id_wallet = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name_wallet");
                wallet = new Wallet(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Wallet wallet) throws SQLException {
        return false;
    }
}
