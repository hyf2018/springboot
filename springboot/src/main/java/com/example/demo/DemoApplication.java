package com.example.demo;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.property.Book;

//增加Mapper的扫描包配置
@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		Binder binder = Binder.get(ctx.getEnvironment());

		// 绑定简单配置
		Book book = binder.bind("book", Bindable.of(Book.class)).get();
		System.out.println(book.getName());
		// 绑定list配置
		List<String> url = binder.bind("spring.my-example.url", Bindable.listOf(String.class)).get();
		System.out.println(url);
		// 绑定map配置
		Map<String, String> map = binder.bind("spring.my-example2", Bindable.mapOf(String.class, String.class)).get();
		System.out.println(map);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}

}
