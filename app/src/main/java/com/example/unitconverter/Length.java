package com.example.unitconverter;

public class Length {
    public enum length {
        MilliMeters, Centimetres, Meters, Kilometres, Inches, Feet,
    }

    // this "units" array will be used in dropdown (spinner)
    public static String[] units = {"MilliMeters", "Centimetres", "Meters", "Kilometres", "Inches", "Feet",};

    // here the value in one source unit is first converted to the value in "Meter"
    // then the value in "Meter" is converted to the value in target unit
    private static double assignWeight(length unit) {
        double weight;
        if (unit == length.MilliMeters) {
            // 1 milli meter = 0.001 meter
            weight = 0.001;
        } else if (unit == length.Centimetres) {
            weight = 0.01;
        } else if (unit == length.Kilometres) {
            weight = 1000;
        } else if (unit == length.Inches) {
            weight = 39.37;
        } else if (unit == length.Feet) {
            weight = 3.28083;
        } else {
            weight = 1;
        }
        return weight;
    }

    static public double convert(double source, length sourceUnit, length targetUnit) {
        double sourceWeight;
        double targetWeight;

        // assigning weight to source unit
        sourceWeight = assignWeight(sourceUnit);

        // assigning weight to target unit
        targetWeight = assignWeight(targetUnit);

        return (source * sourceWeight) / targetWeight;
    }
}
