package yan.sqliteDemo;
import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

@SpringBootApplication
public class SqliteDemoApplication {
	private Connection conn;
	public static void main(String[] args) {
		SpringApplication.run(SqliteDemoApplication.class, args);
		System.out.println("hello springboot!");
		SqliteDemoApplication app = new SqliteDemoApplication();
		app.connect();
	}

	public void connect() {
		conn = null;
		try {
			String url = "jdbc:sqlite:C:/Users/ywang/Dropbox/sqlite/classification.db";
			conn = DriverManager.getConnection(url);
			System.out.println("connection established! Please input description key words. ");
			select(getClientInput());
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
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}




	}
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
