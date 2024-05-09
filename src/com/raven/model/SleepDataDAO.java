package com.raven.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import com.raven.form.Form_1 ;
public class SleepDataDAO {
    private String url = "jdbc:mysql://localhost:3306/loginschema";
    private String username = "root";
    private String password = "1234";
    private String bedTime;
    private String wakeTime;

    // Getter and Setter for bedTime
    public String getBedTime() {
        return bedTime;
    }

    public void setBedTime(String bedTime) {
        this.bedTime = bedTime;
    }

    // Getter and Setter for wakeTime
    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }
public void insertData(String date, String bedTime, String wakeTime) {
    String sql = "INSERT INTO sleep(date, bed_time, wake_time) VALUES (?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(url, username, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, date);
        pstmt.setString(2, bedTime);
        pstmt.setString(3, wakeTime);
        pstmt.executeUpdate();

        // Print a success message
        System.out.println("Data inserted successfully.");

    } catch (SQLException e) {
        // Print the error message
        System.out.println("Failed to insert data: " + e.getMessage());
    }
}
}



