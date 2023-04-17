package com.mursey.account;

import com.mursey.account.model.Customer;
import com.mursey.account.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

	private final CustomerRepository customerRepository;

	public AccountApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = customerRepository.save(new Customer("Cagri", "Dursun"));
		Customer customer2 = customerRepository.save(new Customer("Alice", "Bob"));
		StringBuffer a = new StringBuffer();
		System.out.println(customer);
		System.out.println(customer2);
	}
}
