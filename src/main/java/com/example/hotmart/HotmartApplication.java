package com.example.hotmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class HotmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotmartApplication.class, args);

	}

	//db.Usuario.find({email:"Ian@gmail.com"}, {cursoList:0}); Query pra buscar sem retornar certos campos

}
