package exe9;

public abstract class HealthInsurancePlan {
    // Code for 'coverage' field goes here
    private double coverage;
    // Don't worry about the below code and also the InsuranceBrand class
	private InsuranceBrand offeredBy;

    public HealthInsurancePlan(double coverage) {
        this.coverage = coverage;
    }
    
	public InsuranceBrand getOfferedBy() {
		return offeredBy;
	}

	public void setOfferedBy(InsuranceBrand offeredBy) {
		this.offeredBy = offeredBy;
	}

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);
}