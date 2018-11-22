package com.challenge.question3;

import java.lang.reflect.Method;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Question3Application implements CommandLineRunner {

	public static final String DEFAULT_METHOD_NAME = "process";

	public static void main(String[] args) {
		SpringApplication.run(Question3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println();
		System.out.println("===== APPLICATION START =====");
		System.out.println();

		System.out.println("Please input a type");
		System.out.println("write \"exit\" to leave");

		String inputLine = "";

		while (!inputLine.equals("exit")) {
			inputLine = readString();
			System.out.println("input: " + inputLine);

			String[] inputLineWords = inputLine.split(" ");

			if (inputLineWords != null && inputLineWords.length > 0 && inputLineWords[0] != null
					&& !inputLineWords[0].equals("")) {
				String type = inputLineWords[0].substring(0, 1).toUpperCase() + inputLineWords[0].substring(1);
				String typePackage = "com.challenge.question3.types";

				Class c = null;
				try {
					c = Class.forName(typePackage + "." + type + "Type");
				} catch (ClassNotFoundException ex) {

				}

				if (c != null) {

					Object newInstance = c.newInstance();
					Method method = c.getMethod(DEFAULT_METHOD_NAME);

					if (newInstance != null && method != null) {

						method.invoke(newInstance);
						continue;
					}

				}

			}

			System.out.println("Type not found");
		}

		System.out.println();
		System.out.println("===== APPLICATION END =====");

	}

	private String readString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
