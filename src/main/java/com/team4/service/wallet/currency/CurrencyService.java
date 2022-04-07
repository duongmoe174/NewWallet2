package com.team4.service.wallet.currency;

import com.team4.config.SingletonConnection;
import com.team4.model.CurrencyWallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService implements ICurrencyService {
    private static final String SELECT_ALL_CURRENCY = "select currency.id_currency, name_currency, u.id_user, " +
            "u.name_user from currency join wallet w on currency.id_currency = w.id_currency join user u on u.id_user" +
            " = w.id_user;";

    @Override
    public List<CurrencyWallet> selectAll() {
        List<CurrencyWallet> currencyWallets = new ArrayList<>();
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENCY);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_currency");
                String name = resultSet.getString("name_currency");
                int id_user = resultSet.getInt("id_user");
                String name_user = resultSet.getString("name_user");
                currencyWallets.add(new CurrencyWallet(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencyWallets;
    }

    @Override
    public void insert(CurrencyWallet currencyWallet) {

    }

    @Override
    public CurrencyWallet getById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(CurrencyWallet currencyWallet) throws SQLException {
        return false;
    }
}
