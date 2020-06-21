package yan.sqliteDemo.sqlite;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

@Component
@EnableMBeanExport
@ManagedResource
public class Datebase {
    private Connection conn;
    public void connect() {
        conn = null;
        try {
            String url = "jdbc:sqlite:D:/Dropbox/sqlite/classification.db";
            conn = DriverManager.getConnection(url);
            System.out.println("connection established! Please input description key words. ");
            String des = getClientInput();
            System.out.println(String.format("query description key word : %s", des));
            select(des);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @ManagedAttribute
    public void select(String description) {
        if (conn == null) {
            System.out.println("not connected!");
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM PASSWDS WHERE DESCRIPTION LIKE '%%%s%%'", description));
            while (rs.next()) {
                int id = rs.getInt("id");
                String pw = rs.getString("passwd");
                String username = rs.getString("username");
                String trueDescirption = rs.getString("description");
                System.out.println(String.format("| %5d | %80s | %30s | %15s", id, trueDescirption, username, pw));

            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




    }

    @ManagedOperation
    private String getClientInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String res = "";
        try {
            res = br.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

}
