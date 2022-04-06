package com.team4.service.wallet;

import com.team4.model.Wallet;

import java.sql.SQLException;
import java.util.List;

public class WalletService implements IWalletService {
    @Override
    public List<Wallet> selectAll() {
        return null;
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
