package exe8;

public class Billing {
	public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];
        
        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        // your logic
        double coverage = 0.0;
        if(patientInsurancePlan != null) {
            coverage = patientInsurancePlan.getCoverage();
        }
        payments[0] = amount * coverage;
        payments[1] = amount - payments[0];
        int discount = 0;
        if(patientInsurancePlan instanceof PlatinumPlan)
            discount = 50;
        else if(patientInsurancePlan instanceof GoldPlan)
            discount = 40;
        else if(patientInsurancePlan instanceof SilverPlan)
            discount = 30;
        else if(patientInsurancePlan instanceof BronzePlan)
            discount = 25;
        else 
            discount = 20;
        payments[1] = payments[1] - discount;

        return payments;
    }
}