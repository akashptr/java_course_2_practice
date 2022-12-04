package exe9;

public class PlatinumPlan extends HealthInsurancePlan{
    public PlatinumPlan() {
        super(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return salary * 0.08 + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}