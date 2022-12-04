package com.abcclinic.patientbilling;

import com.abcclinic.patientclinicals.PatientClinicals;
import com.abcclinic.patientregistration.PatientRegistration;

public class PatientBilling {

	public static void main(String[] args) {
		PatientRegistration pr = new PatientRegistration();
		pr.registerPatient();
		PatientClinicals pc = new PatientClinicals();
		pc.getClinicDetails();
	}

}
