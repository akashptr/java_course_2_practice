package com.akash.util;

public class TemperatureConverter {
	public static float celsiusToFahrenheit(float celsius) {
		return (celsius * (9.0f / 5.0f)) + 32;
	}
	public static float fahrenheitToCelsius(float fahrenheit) {
		return (fahrenheit - 32) * (5.0f / 9);
	}
}
