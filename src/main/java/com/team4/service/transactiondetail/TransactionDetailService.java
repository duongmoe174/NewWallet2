package com.team4.service.transactiondetail;

import com.team4.config.SingletonConnection;
import com.team4.model.Category;
import com.team4.model.Transaction;
import com.team4.model.TransactionDetail;
import com.team4.model.Wallet;
import com.team4.service.catalog.CategoryService;
import com.team4.service.transaction.TransactionService;
import com.team4.service.wallet.WalletService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDetailService implements ITransactionDetailService{
    Connection connection = SingletonConnection.getConnect();

    public TransactionDetailService() {
    }

    @Override
    public List<TransactionDetail> selectAll() {
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        try (Connection connection = SingletonConnection.getConnect();
                PreparedStatement preparedStatement = connection.prepareStatement("select td.id_detail, td.amount_money, td.date_transaction,\n" +
                        "       t.id_transaction, t.name_transaction,\n" +
                        "       category.id_category, category.name_category,\n" +
                        "       w.id_wallet, w.name_wallet,\n" +
                        "       td.notes\n" +
                        "from category\n" +
                        "join transaction_detail td on category.id_category = td.id_category\n" +
                        "join transaction t on t.id_transaction = td.id_transaction\n" +
                        "join wallet w on w.id_wallet = td.id_wallet;")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idDetail = resultSet.getInt("id_detail");
                    double amount = resultSet.getDouble("amount_money");
                    java.sql.Date date = resultSet.getDate("date_transaction");
                    int idTransaction = resultSet.getInt("id_transaction");
                    String nameTransaction = resultSet.getString("name_transaction");
                    int idCategory = resultSet.getInt("id_category");
                    String nameCategory = resultSet.getString("name_category");
                    int idWallet = resultSet.getInt("id_wallet");
                    String nameWallet = resultSet.getString("name_wallet");
                    String notes = resultSet.getString("notes");

                    Transaction transaction = new Transaction(idTransaction, nameTransaction);
                    Category category = new Category(idCategory, nameCategory);
                    Wallet wallet = new Wallet(idWallet, nameWallet);
                    transactionDetailList.add(new TransactionDetail(idDetail, amount, date, transaction, category, wallet, notes));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDetailList;
    }

    @Override
    public void insert(TransactionDetail transactionDetail) {
        try (Connection connection = SingletonConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into transaction_detail" +
                    "(amount_money, date_transaction, id_transaction, id_category, id_wallet, notes) values (?,?,?,?,?,?);")){
            preparedStatement.setDouble(1,transactionDetail.getAmount());
            preparedStatement.setDate(2, transactionDetail.getDate());
            preparedStatement.setInt(3, transactionDetail.getTransaction().getId());
            preparedStatement.setInt(4, transactionDetail.getCategory().getId());
            preparedStatement.setInt(5, transactionDetail.getWallet().getId());
            preparedStatement.setString(6,transactionDetail.getNotes());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransactionDetail getById(int id) {
        TransactionDetail transactionDetail = null;
        try (Connection connection = SingletonConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement("select td.id_detail, td.amount_money, td.date_transaction,\n" +
                    "       t.id_transaction, t.name_transaction,\n" +
                    "       category.id_category, category.name_category,\n" +
                    "       w.id_wallet, w.name_wallet,\n" +
                    "       td.notes\n" +
                    "from category\n" +
                    "         join transaction_detail td on category.id_category = td.id_category\n" +
                    "         join transaction t on t.id_transaction = td.id_transaction\n" +
                    "         join wallet w on w.id_wallet = td.id_wallet\n" +
                    "where td.id_detail = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount_money");
                java.sql.Date date = resultSet.getDate("date_transaction");
                int idTransaction  = resultSet.getInt("id_transaction");
                Transaction transaction = new TransactionService().getById(idTransaction);
                int idCategory = resultSet.getInt("id_category");
                Category category = new CategoryService().selectCategory(idCategory);
                int idWallet = resultSet.getInt("id_wallet");
                Wallet wallet = new WalletService().getById(idWallet);
                String notes = resultSet.getString("notes");
                transactionDetail = new TransactionDetail(id, amount, date, transaction, category, wallet, notes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDetail;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = SingletonConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from transaction_detail where id_detail = ?;")){
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(TransactionDetail transactionDetail) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = SingletonConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement("update transaction_detail set amount_money = ?, date_transaction = ?, id_transaction = ?, id_category = ?, id_wallet = ?, notes = ? where id_detail = ?;")) {
            preparedStatement.setDouble(1, transactionDetail.getAmount());
            preparedStatement.setDate(2, transactionDetail.getDate());
            preparedStatement.setInt(3, transactionDetail.getTransaction().getId());
            preparedStatement.setInt(4, transactionDetail.getCategory().getId());
            preparedStatement.setInt(5, transactionDetail.getWallet().getId());
            preparedStatement.setString(6, transactionDetail.getNotes());
            preparedStatement.setInt(7, transactionDetail.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
