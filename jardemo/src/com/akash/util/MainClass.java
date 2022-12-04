package com.akash.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Enter temperature in celsius: ");
			float celsius = Float.parseFloat(br.readLine());
			System.out.println("Temperature converted in fahrenheit: " + TemperatureConverter.celsiusToFahrenheit(celsius));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
