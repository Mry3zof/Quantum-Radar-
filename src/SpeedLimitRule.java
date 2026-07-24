public class SpeedLimitRule implements Rule {

    private CarType carType;
    private double maxSpeed;
    private double fee;

    public SpeedLimitRule(CarType carType, double maxSpeed, double fee) {
        this.carType = carType;
        this.maxSpeed = maxSpeed;
        this.fee = fee;
    }

    public String getRuleName() {
        return "Max speed exceeded (" + carType + ")";
    }

    public boolean isViolated(Observation observation) {
        return observation.getCarType() == carType && observation.getSpeed() > maxSpeed;
    }

    public String describeViolation(Observation observation) {
        return String.format("speed of %.0f exceeded max allowed %.0f", observation.getSpeed(), maxSpeed);
    }

    public double getFee() {
        return fee;
    }
}