package com.spring.task.ntc_twoo.dao;

import com.spring.task.ntc_twoo.model.Student;
import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDAOConection implements DAOConnection {

    private static OracleDAOConection oracleDAOConection;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    // private PreparedStatement preparedStatement;
    private Driver driver;


    private OracleDAOConection() {
        super();
    }

    public static OracleDAOConection getInstance() {
        if (oracleDAOConection != null) {
            return oracleDAOConection;
        } else {
            oracleDAOConection = new OracleDAOConection();
            return new OracleDAOConection();
        }
    }

    @Override
    public void conect() throws SQLException {

        driver = new OracleDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "STUDENT", "tasher32212");
        if (!connection.isClosed()) {
            System.out.println("Connect is ok");
        }
    }

    @Override
    public void disconect() throws SQLException {
        connection.close();
        resultSet.close();
        statement.close();
        if (connection.isClosed()) {
            System.out.println("closed");
        }
    }

    //------READ
    @Override
    public List<Student> selectAllStudents() throws SQLException {
        conect();
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM STUDENTS ORDER BY STUDENT_NAME ASC");
            while (resultSet.next()) {
                studentList.add(parseStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconect();
        return studentList;
    }

    //--------CRATE
    @Override
    public void createStudent(String name, float salary) throws SQLException {
        conect();
        try {
            statement = connection.prepareStatement("INSERT INTO STUDENTS (STUDENT_ID, STUDENT_NAME, STUDENT_SALARY) " +
                    "VALUES (NULL, ?, ?)");
            ((PreparedStatement) statement).setString(1, name);
            ((PreparedStatement) statement).setFloat(2, salary);
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconect();
    }

    @Override
    public void updateStudent(int id, float sum) throws SQLException {
        conect();
        try {
            statement = connection.prepareStatement("UPDATE STUDENTS SET STUDENT_SALARY = ? WHERE STUDENT_ID = ? ");
            ((PreparedStatement) statement).setFloat(1, sum);
            ((PreparedStatement) statement).setInt(2, id);
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconect();
    }

    //---------DELETE
    @Override
    public void deleteStudent(int id) throws SQLException {
        conect();
        try {
            statement = connection.prepareStatement("DELETE  FROM STUDENTS  WHERE STUDENT_ID = ? ");
            ((PreparedStatement) statement).setInt(1, id);
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconect();
    }


    private Student parseStudent(ResultSet resultSet) {
        Student student = null;
        try {
            int id = resultSet.getInt("STUDENT_ID");
            String name = resultSet.getString("STUDENT_NAME");
            float salary = resultSet.getFloat("STUDENT_SALARY");
            student = new Student(id, name, salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

}
