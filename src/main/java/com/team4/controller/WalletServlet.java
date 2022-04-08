package com.team4.controller;

import com.team4.model.CurrencyWallet;
import com.team4.model.User;
import com.team4.model.Wallet;
import com.team4.service.currency.CurrencyService;
import com.team4.service.currency.ICurrencyService;
import com.team4.service.user.IUserService;
import com.team4.service.user.UserService;
import com.team4.service.wallet.IWalletService;
import com.team4.service.wallet.WalletService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "WalletServlet", value = "/wallets")
public class WalletServlet extends HttpServlet {
    ICurrencyService currencyService = new CurrencyService();
    IWalletService walletService = new WalletService();
    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(request, response);
                break;
            default:
                listWallet(request, response); //a du
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertWallet(request, response);
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currencies", currencyService.selectAll());
        request.setAttribute("users", userService.selectAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("wallet/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void listWallet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nameUser = (String) session.getAttribute("loginuser");
        int idUser = userService.getUserIdByName(nameUser);
        List<Wallet> wallets = walletService.selectAllWalletById(idUser);
        request.setAttribute("wallets", wallets);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("wallet/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void insertWallet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String idCurrencyStr = request.getParameter("id_cur");
        int idCur = Integer.parseInt(idCurrencyStr);
        String nameUser = (String) session.getAttribute("loginuser");
        int idUser = userService.getUserIdByName(nameUser);
        String amountStr = request.getParameter("current_amount");
        double currentAmount = Double.parseDouble(amountStr);
        String description = request.getParameter("description");
        User user = new User(idUser, nameUser);
        CurrencyWallet currencyWallet = new CurrencyWallet(idCur);
        Wallet wallet = new Wallet(name, currentAmount, description, user, currencyWallet);
        walletService.insert(wallet);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("wallet/create.jsp");
        requestDispatcher.forward(request, response);
    }
}
