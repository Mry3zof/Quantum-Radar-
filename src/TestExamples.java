import java.time.LocalDate;

public class TestExamples {

    public static void example01_MultipleViolationsOneFine() {
        RadarSystem radar = buildDefaultRadar();
        radar.handleObservation(new Observation(
                "ABC1234", LocalDate.of(2026, 7, 23), CarType.PRIVATE, 94, false));
    }

    public static void example02_SingleViolation() {
        RadarSystem radar = buildDefaultRadar();
        radar.handleObservation(new Observation(
                "TRK555", LocalDate.of(2026, 7, 23), CarType.TRUCK, 75, true));
    }

    public static void example03_CompliantCarNoFine() {
        RadarSystem radar = buildDefaultRadar();
        radar.handleObservation(new Observation(
                "COOL001", LocalDate.of(2026, 7, 23), CarType.PRIVATE, 60, true));
    }

    public static void example04_SpeedExactlyAtLimitIsNotAViolation() {
        RadarSystem radar = buildDefaultRadar();
        radar.handleObservation(new Observation(
                "EDGE001", LocalDate.of(2026, 7, 23), CarType.PRIVATE, 80, true));
    }

    public static void example05_NoRulesRegistered() {
        RadarSystem radar = new RadarSystem();
        radar.handleObservation(new Observation(
                "NORULE1", LocalDate.of(2026, 7, 23), CarType.TRUCK, 999, false));
    }

    public static void example06_CarTypeWithNoMatchingRule() {
        RadarSystem radar = buildDefaultRadar(); // only has rules for PRIVATE and TRUCK
        radar.handleObservation(new Observation(
                "BUS0007", LocalDate.of(2026, 7, 23), CarType.BUS, 150, true));
    }

    public static void example07_SamePlateFinedTwiceTotalsSum() {
        RadarSystem radar = buildDefaultRadar();
        radar.handleObservation(new Observation(
                "REPEAT1", LocalDate.of(2026, 7, 23), CarType.PRIVATE, 90, true));
        radar.handleObservation(new Observation(
                "REPEAT1", LocalDate.of(2026, 7, 24), CarType.PRIVATE, 85, true));
        System.out.println();
        radar.printFineTotals();
    }

    public static void example08_AggregateReportsAcrossMultipleCars() {
        RadarSystem radar = buildDefaultRadar();
        radar.handleObservation(new Observation(
                "CAR0001", LocalDate.of(2026, 7, 23), CarType.PRIVATE, 95, false));
        radar.handleObservation(new Observation(
                "CAR0002", LocalDate.of(2026, 7, 23), CarType.TRUCK, 70, true));
        radar.handleObservation(new Observation(
                "CAR0003", LocalDate.of(2026, 7, 23), CarType.PRIVATE, 60, true));
        System.out.println();
        radar.printFineTotals();
        System.out.println();
        radar.printViolationCounts();
    }

    private static RadarSystem buildDefaultRadar() {
        RadarSystem radar = new RadarSystem();
        radar.addRule(new SeatBeltRule(100));
        radar.addRule(new SpeedLimitRule(CarType.PRIVATE, 80, 300));
        radar.addRule(new SpeedLimitRule(CarType.TRUCK, 60, 300));
        return radar;
    }
}