package com.team4.controller;

import com.team4.model.Category;
import com.team4.model.Transaction;
import com.team4.service.transaction.TransactionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TransactionServlet", value = "/transaction")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionService transactionService;

    public void init() {
        transactionService = new TransactionService();
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
                    deleteTransaction(request,response);
                    break;
                default:
                    listTransaction(request,response);
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
                    insertTransaction(request,response);
                    break;
                case "edit":
                    updateTransaction(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException();
        }
    }

    private void listTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Transaction> listTransaction = transactionService.selectAll();
        request.setAttribute("listTransaction", listTransaction);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction/list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTransaction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        Transaction newTransaction = new Transaction(name);
        transactionService.insert(newTransaction);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction/create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateTransaction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Transaction updatedTransaction = new Transaction(id, name);
        transactionService.update(updatedTransaction);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Transaction existingTransaction = transactionService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction/edit.jsp");
        request.setAttribute("transaction", existingTransaction);
        dispatcher.forward(request, response);
    }

    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        transactionService.delete(id);
        List<Transaction> listTransaction = transactionService.selectAll();
        request.setAttribute("listTransaction", listTransaction);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction/list.jsp");
        dispatcher.forward(request, response);
    }
}
