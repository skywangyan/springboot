package yan.sqliteDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yan.sqliteDemo.sqlite.Datebase;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SqliteDemoApplication {
	@Autowired
	Datebase sql = null;
	public static void main(String[] args) {
		SpringApplication.run(SqliteDemoApplication.class, args);
		System.out.println("hello springboot!");
	}
	@PostConstruct
	void init() {
		sql.connect();
	}
}
