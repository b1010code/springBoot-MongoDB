package springmongo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoDbApplication.class, args);
	}
		@Bean
		public ModelMapper getModelMapper() {
			return new ModelMapper();

	}

}
