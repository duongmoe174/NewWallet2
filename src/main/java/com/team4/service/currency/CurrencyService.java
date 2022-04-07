package com.team4.service.currency;

import com.team4.config.SingletonConnection;
import com.team4.model.CurrencyWallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CurrencyService implements ICurrencyService {
    Connection connection = SingletonConnection.getConnect();

    @Override
    public List<CurrencyWallet> selectAll() {
        List<CurrencyWallet> currencyWallets = new ArrayList<>();
        String query = "{CALL selectAllCurrency()}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_currency");
                String name = resultSet.getString("name_currency");
                CurrencyWallet currency = new CurrencyWallet(id, name);
                currencyWallets.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencyWallets;
    }

    @Override
    public void insert(CurrencyWallet currency) {

    }

    @Override
    public CurrencyWallet getById(int id) {
        CurrencyWallet currencyWallet = null;
        String query = "{CALL getCurrencyById()}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name_currency");
                currencyWallet = new CurrencyWallet(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencyWallet;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(CurrencyWallet currency) throws SQLException {
        return false;
    }
}
