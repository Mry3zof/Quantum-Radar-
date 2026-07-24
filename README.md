# Quantum Radar - Fawry N² Internship

This is my solution to the Quantum Radar assessment task for Fawry N² Internship.

## What it does

Each reading (an `Observation`) carries a plate number, date, car type, speed, and seatbelt status. Every registered `Rule` gets a look at it and reports whether it was broken. If anything was, a `Fine` is built and printed listing every violation and its fee, plus the total. Two extra reports are available on demand: total fines per plate, and how many times each rule has been broken across all traffic seen so far.

**Extending it doesn't require touching existing code.** A new rule is just a new class implementing `Rule`, registered with `radar.addRule(...)`. `RadarSystem` never needs to change.

## Running it

From the `src` folder:

```bash
javac radar/*.java -d out
java -cp out radar.Main
```

Or open it in any IDE and run `Main.main()` directly.

## Trying the different scenarios

`TestExamples` holds a set of independent, runnable scenarios — each builds its own radar and rules, so they don't affect one another. Point `Main` at whichever one you want to see:

```java
TestExamples.example01_MultipleViolationsOneFine();
```

| Method | What it demonstrates |
|---|---|
| `example01_MultipleViolationsOneFine` | One car breaks two rules at once — a single fine, both violations listed, fees summed |
| `example02_SingleViolation` | A straightforward one-rule-broken fine |
| `example03_CompliantCarNoFine` | Fully compliant reading — no fine issued |
| `example04_SpeedExactlyAtLimitIsNotAViolation` | Boundary check: hitting the limit exactly is fine — only going *over* it counts |
| `example05_NoRulesRegistered` | A radar with zero rules registered lets everything through, however extreme |
| `example06_CarTypeWithNoMatchingRule` | A car type nothing was configured for (e.g. a Bus, when only Private/Truck rules exist) triggers nothing |
| `example07_SamePlateFinedTwiceTotalsSum` | Same plate fined on two separate occasions — the totals report adds them up rather than overwriting |
| `example08_AggregateReportsAcrossMultipleCars` | Several cars, mixed outcomes — both summary reports aggregate correctly |
