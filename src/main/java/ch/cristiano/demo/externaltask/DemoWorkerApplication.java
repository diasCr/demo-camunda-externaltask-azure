package ch.cristiano.demo.externaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWorkerApplication {

	public static void main(String[] args) {
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		SpringApplication.run(DemoWorkerApplication.class, args);
	}
}
