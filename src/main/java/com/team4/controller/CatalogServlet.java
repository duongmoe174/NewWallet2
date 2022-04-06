package com.team4.controller;

import com.team4.model.Category;
import com.team4.service.catalog.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CatalogServlet", value = "/Catalog")
public class CatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService catalogService;

    public void  init(){catalogService = new CategoryService();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCatalog(request, response);
                    break;
                default:
                    listCatalog(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> listCatalog = catalogService.selectAllCategory();
        request.setAttribute("listCatalog", listCatalog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category existingUser = catalogService.selectCategory(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void deleteCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        catalogService.deleteCategory(id);

        List<Category> listCatalog = catalogService.selectAllCategory();
        request.setAttribute("listCatalog", listCatalog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog/list.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertCatalog(request, response);
                    break;
                case "edit":
                    updateCatalog(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String name = request.getParameter("name");
        String note = request.getParameter("note");

        Category newUser = new Category(name,note);
        catalogService.insertCategory(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog/create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String note = request.getParameter("note");


        Category book = new Category(id, name, note);
        catalogService.updateCategory(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog/edit.jsp");
        dispatcher.forward(request, response);
    }
}
