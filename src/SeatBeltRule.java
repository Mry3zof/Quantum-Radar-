public class SeatBeltRule implements Rule {

    private double fee;

    public SeatBeltRule(double fee) {
        this.fee = fee;
    }

    public String getRuleName() {
        return "Seatbelt not fastened";
    }

    public boolean isViolated(Observation observation) {
        return !observation.isSeatbeltFastened();
    }

    public String describeViolation(Observation observation) {
        return "Seatbelt not fastened";
    }

    public double getFee() {
        return fee;
    }
}