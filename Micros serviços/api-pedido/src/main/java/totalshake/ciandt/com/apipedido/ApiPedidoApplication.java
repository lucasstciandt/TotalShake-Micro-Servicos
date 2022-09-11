package totalshake.ciandt.com.apipedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPedidoApplication.class, args);
	}
}
