package exe9;

public class BronzePlan extends HealthInsurancePlan{

    public BronzePlan() {
        super(0.6);
    }
    
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return salary * 0.05 + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
