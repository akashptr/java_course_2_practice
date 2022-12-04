package exe8;

public class BronzePlan extends HealthInsurancePlan{

    public BronzePlan() {
        super(0.6);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        // TODO Auto-generated method stub
        return salary * 0.05;
    }
}
