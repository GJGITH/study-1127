package com.example;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.pojo.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class OmApplication {

	@Autowired
	private ObjectMapper mapper;
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OmApplication.class, args);
		ObjectMapper mapper = context.getBean(ObjectMapper.class);
		String json =  "{\"name\":\"zhangsan\", \"age\":20}";
		System.out.println("json为："+json);
		//json转为对象
		Person p1;
		try {
			p1 = mapper.readValue(json, Person.class);
			System.out.println("json转换成对象的结果为"+p1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//对象转为json
		Person p2 = new Person("李四", 22);
		try {
			json = mapper.writeValueAsString(p2);
			System.out.println("对象转换为json的结果为"+json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Bean
	public ApplicationRunner runner() {
		
		return args->{
//			ObjectMapper mapper = new ObjectMapper();
			String json =  "{\"name\":\"zhangsan\", \"age\":20}";
			System.out.println("json为："+json);
			
			//json转为对象
			Person p1 = mapper.readValue(json, Person.class);
			System.out.println("json转换成对象的结果为"+p1);
			
			//对象转为json
			json = mapper.writeValueAsString(p1);
			System.out.println("对象转换为json的结果为"+json);
			
		};
	}*/
	
	@Bean
	ObjectMapper mapper() {
		return new ObjectMapper();
	}
	
}
