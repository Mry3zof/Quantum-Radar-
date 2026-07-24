import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadarSystem {

    private List<Rule> rules = new ArrayList<>();
    private List<Fine> issuedFines = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void handleObservation(Observation observation) {
        List<Violation> allViolations = new ArrayList<>();

        for (Rule rule : rules) {
            if (rule.isViolated(observation)) {
                String description = rule.describeViolation(observation);
                allViolations.add(new Violation(rule.getRuleName(), description, rule.getFee()));
            }
        }

        if (allViolations.isEmpty()) {
            System.out.println("No violations for car " + observation.getPlateNumber() + " - no fine issued.");
            return;
        }

        Fine fine = new Fine(observation.getPlateNumber(), allViolations);
        issuedFines.add(fine);
        System.out.println(fine);
    }

    public List<Fine> getAllFines() {
        return issuedFines;
    }

    public Map<String, Double> getFineTotalsByPlate() {
        Map<String, Double> totals = new HashMap<>();
        for (Fine fine : issuedFines) {
            double current = totals.getOrDefault(fine.getPlateNumber(), 0.0);
            totals.put(fine.getPlateNumber(), current + fine.getTotalAmount());
        }
        return totals;
    }

    public Map<String, Integer> getViolationCountsByRule() {
        Map<String, Integer> violationCounts = new HashMap<>();
        for (Fine fine : issuedFines) {
            if (fine == null) {
                continue;
            }
            for (Violation violation : fine.getViolations()) {
                if (violation == null || violation.getRuleName() == null || violation.getRuleName().isBlank()) {
                    continue;
                }
                String ruleName = violation.getRuleName();
                violationCounts.put(ruleName, violationCounts.getOrDefault(ruleName, 0) + 1);
            }
        }
        return violationCounts;
    }

    public void printFineTotals() {
        System.out.println("All fines (plate -> total amount):");
        for (var entry : getFineTotalsByPlate().entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " EGP");
        }
    }

    public void printViolationCounts() {
        System.out.println("All violated rules (rule -> count):");
        for (var entry : getViolationCountsByRule().entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}