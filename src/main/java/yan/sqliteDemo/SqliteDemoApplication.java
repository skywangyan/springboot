package yan.sqliteDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import yan.sqliteDemo.JMX.SQLJmxBean;
import yan.sqliteDemo.sqlite.Datebase;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages="yan.sqliteDemo")
public class SqliteDemoApplication {
	@Autowired
	Datebase sql;
	@Autowired
	SQLJmxBean jmxBean;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SQLJmxBean sqljmxb;

	public static void main(String[] args) {
		//disable hawtio authentication
		//System.setProperty("hawtio.authenticationEnabled", "false");
		SpringApplication.run(SqliteDemoApplication.class, args);
		System.out.println("hello springboot!");
	}
	@PostConstruct
	void init() {
		//sql.connect();
		displayAllBeans();
		System.out.println(sqljmxb.query());
	}

	private void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		for(String beanName: allBeanNames) {
			System.out.println(beanName);
		}
	}

}
