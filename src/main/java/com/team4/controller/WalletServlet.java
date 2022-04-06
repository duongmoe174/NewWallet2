package com.team4.controller;

import com.team4.model.Wallet;
import com.team4.service.wallet.IWalletService;
import com.team4.service.wallet.WalletService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "WalletServlet", urlPatterns = "/wallets")
public class WalletServlet extends HttpServlet {
    IWalletService walletService = new WalletService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    break;
                default:
                    listWallets(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void listWallets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        List<Wallet> wallets = walletService.selectAll();
        request.setAttribute("wallets", wallets);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("wallet/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }
}
