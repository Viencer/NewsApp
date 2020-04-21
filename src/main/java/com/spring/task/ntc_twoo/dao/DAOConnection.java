package com.spring.task.ntc_twoo.dao;

import com.spring.task.ntc_twoo.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface DAOConnection {

    void conect() throws SQLException;

    void disconect() throws SQLException;

    List<Student> selectAllStudents() throws SQLException;

    void createStudent(String name, float salary) throws SQLException;

    void updateStudent(int id, float sum) throws SQLException;

    void deleteStudent(int id) throws SQLException;
}
