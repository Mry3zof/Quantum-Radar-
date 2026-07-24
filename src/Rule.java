public interface Rule {

    String getRuleName();

    boolean isViolated(Observation observation);

    String describeViolation(Observation observation);

    double getFee();
}