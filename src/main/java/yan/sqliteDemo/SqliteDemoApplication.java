package yan.sqliteDemo;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class SqliteDemoApplication {
	private Connection conn;
	public static void main(String[] args) {
		SpringApplication.run(SqliteDemoApplication.class, args);
		System.out.println("hello springboot!");
		SqliteDemoApplication app = new SqliteDemoApplication();
		app.connect();
		app.select("PSU");
	}

	public void connect() {
		conn = null;
		try {
			String url = "jdbc:sqlite:C:/Users/ywang/Dropbox/sqlite/classification.db";
			conn = DriverManager.getConnection(url);
			System.out.println("connection established!");
			select("dummy");
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

	public void select(String description) {
		if (conn == null) {
			System.out.println("not connected!");
			return;
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PASSWDS");
			while (rs.next()) {
				int id = rs.getInt("id");
				System.out.println(id);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}





	}
}
