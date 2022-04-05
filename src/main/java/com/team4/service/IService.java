package com.team4.service;

import java.sql.SQLException;
import java.util.List;
//hihihihi
public interface IService<T>{
    List<T> selectAll();
    void insert(T t);
    T getById (int id);
    boolean delete (int id) throws SQLException;
    boolean update (T t) throws SQLException;
}
