package com.akash.util;

public class DistanceConverter {
	public static double centimeterToInch(double cm) {
		return cm / 2.54;
	}
	public static double inchToCentimeter(double inch) {
		return inch * 2.54;
	}
	public static double inchToFoot(double inch) {
		return inch / 12;
	}
	public static double footToInch(double foot) {
		return foot * 12;
	}
}
