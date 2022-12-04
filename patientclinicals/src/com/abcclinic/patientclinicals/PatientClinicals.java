package com.abcclinic.patientclinicals;

import com.abcclinic.patientregistration.PatientRegistration;

public class PatientClinicals {

	public void getClinicDetails() {
		PatientRegistration pr = new PatientRegistration();
		pr.getPatientDetails();
		System.out.println("is admitted in ABC Clinic");
	}

}
