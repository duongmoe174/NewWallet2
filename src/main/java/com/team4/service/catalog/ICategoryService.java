package com.team4.service.catalog;

import com.team4.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryService {
    public void insertCategory(Category category) throws SQLException;

    public Category selectCategory(int id);

    public List<Category> selectAllCategory();



    public boolean deleteCategory(int id) throws SQLException;

    public boolean updateCategory(Category category) throws SQLException;
}
