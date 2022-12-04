package exe8;

public class SilverPlan extends HealthInsurancePlan {

    public SilverPlan() {
        super(0.7);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        // TODO Auto-generated method stub
        return salary * 0.06;
    }
}