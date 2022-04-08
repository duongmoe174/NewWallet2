package com.team4.controller;

import com.team4.model.Category;
import com.team4.model.Transaction;
import com.team4.model.TransactionDetail;
import com.team4.model.Wallet;
import com.team4.service.catalog.CategoryService;
import com.team4.service.transaction.TransactionService;
import com.team4.service.transactiondetail.TransactionDetailService;
import com.team4.service.wallet.WalletService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TransactionDetailServlet", value = "/transactiondetail")
public class TransactionDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDetailService transactionDetailService;
    private TransactionService transactionService;
    private CategoryService categoryService;
    private WalletService walletService;

    public void init() {
        transactionDetailService = new TransactionDetailService();
        transactionService = new TransactionService();
        categoryService = new CategoryService();
        walletService = new WalletService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try{
            switch (action) {
                case "create":
                    showNewForm(request,response);
                    break;
                case "edit":
                    showEditForm(request,response);
                    break;
                case "delete":
                    deleteTransactionDetail(request,response);
                    break;
                default:
                    listTransactionDetail(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try{
            switch (action) {
                case "create":
                    insertTransactionDetail(request,response);
                    break;
                case "edit":
                    updateTransactionDetail(request,response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listTransactionDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<TransactionDetail> listTransactionDetail = transactionDetailService.selectAll();
        request.setAttribute("listTransactionDetail", listTransactionDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactiondetail/list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTransactionDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        double amount = Double.parseDouble(request.getParameter("amount"));
        int year = Integer.parseInt(request.getParameter("year")) - 1900;
        int month = Integer.parseInt(request.getParameter("month")) - 1;
        int day = Integer.parseInt(request.getParameter("day"));
        java.sql.Date date = new java.sql.Date(year,month,day);
        int idTransaction = Integer.parseInt(request.getParameter("transaction"));
        Transaction transaction = transactionService.getById(idTransaction);
        int idCategory = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.selectCategory(idCategory);
        int idWallet = Integer.parseInt(request.getParameter("wallet"));
        Wallet wallet = walletService.getById(idWallet);
        String notes = request.getParameter("notes");
        TransactionDetail newTransactionDetail = new TransactionDetail(amount, date, transaction, category, wallet, notes);
        transactionDetailService.insert(newTransactionDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactiondetail/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listTransaction", transactionService.selectAll());
        request.setAttribute("listCategory", categoryService.selectAllCategory());
        request.setAttribute("listWallet", walletService.selectAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactiondetail/create.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteTransactionDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        transactionDetailService.delete(id);
        List<TransactionDetail> listTransactionDetail = transactionDetailService.selectAll();
        request.setAttribute("listTransactionDetail", listTransactionDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactiondetail/list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateTransactionDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        int year = Integer.parseInt(request.getParameter("year")) - 1900;
        int month = Integer.parseInt(request.getParameter("month")) - 1;
        int day = Integer.parseInt(request.getParameter("day"));
        java.sql.Date date = new java.sql.Date(year,month,day);
        int idTransaction = Integer.parseInt(request.getParameter("transaction"));
        Transaction transaction = transactionService.getById(idTransaction);
        int idCategory = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.selectCategory(idCategory);
        int idWallet = Integer.parseInt(request.getParameter("wallet"));
        Wallet wallet = walletService.getById(idWallet);
        String notes = request.getParameter("notes");
        TransactionDetail updatedTransactionDetail = new TransactionDetail(id, amount, date, transaction, category, wallet, notes);
        transactionDetailService.update(updatedTransactionDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactiondetail/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TransactionDetail existingTransaction = transactionDetailService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactiondetail/edit.jsp");
        request.setAttribute("transactiondetail", existingTransaction);
        request.setAttribute("listTransaction", transactionService.selectAll());
        request.setAttribute("listCategory", categoryService.selectAllCategory());
        request.setAttribute("listWallet", walletService.selectAll());
        dispatcher.forward(request, response);
    }
}
