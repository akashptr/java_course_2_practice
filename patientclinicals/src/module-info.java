module patientclinicals {
	requires transitive patientregistration; //Any module dependent on this module can use patientregistration module.
	exports com.abcclinic.patientclinicals; //transitive keyword gives access to patientregistration, not this modules packages.
	//exports com.abcclinic.patientclinicals to patientbilling; //Exposing only to patientbilling module.
}