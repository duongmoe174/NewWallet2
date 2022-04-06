package com.team4.service.wallet;

import com.team4.config.SingletonConnection;
import com.team4.model.Wallet;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletService implements IWalletService {
    private static final String SELECT_ALL_WALLET = "select id_wallet, name_wallet, current_amount, description from wallet";

    @Override

    public List<Wallet> selectAll() {
        List<Wallet> wallets = new ArrayList<>();
        try (Connection connection = SingletonConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WALLET);
        ) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_wallet");
                String name = resultSet.getString("name_wallet");
                double currency = resultSet.getDouble("current_amount");
                String description = resultSet.getString("description");
                wallets.add(new Wallet(id, name, currency, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }

    @Override
    public void insert(Wallet wallet) {

    }

    @Override
    public Wallet getById(int id) {
        return null;
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
