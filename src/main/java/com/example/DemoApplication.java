package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.example.dao.EmployeeDAO;
import com.example.entityBeans.Employee;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		
		System.out.println("Let's inspect the beans provided by Spring Boot:");

        /*String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
        
        EmployeeDAO employeeDAO = (EmployeeDAO) ctx.getBean("employeeDAO");
        Employee emp = new Employee();
        emp.setUserName("uvks");
        emp.setPassword("password");
        emp.setFirstName("Venkat");
        emp.setLastName("Uppalapati");
        emp.setAddress("4701 Staggerbrush");
        employeeDAO.insertEmployee(emp);
        
        Employee emp1 = employeeDAO.getEmployee(10);
        System.out.println("*********");
		System.out.println(emp1.getUserName());
		System.out.println("*********");
	}
}
