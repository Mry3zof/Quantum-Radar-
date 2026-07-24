import java.util.List;

public class Fine {

    private String plateNumber;
    private List<Violation> violations;
    private double totalAmount;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = violations;

        double sum = 0;
        for (Violation v : violations) {
            sum += v.getFee();
        }
        this.totalAmount = sum;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Traffic fine for car ").append(plateNumber).append("\n");
        sb.append("Total amount: ").append(formatAmount(totalAmount)).append(" EGP\n");
        sb.append("Violations:");
        for (Violation v : violations) {
            sb.append("\n- ").append(v.getDescription()).append(": ").append(formatAmount(v.getFee())).append(" EGP");
        }
        return sb.toString();
    }

    private String formatAmount(double amount) {
        if (amount == Math.floor(amount)) {
            return String.valueOf((long) amount);
        }
        return String.valueOf(amount);
    }
}