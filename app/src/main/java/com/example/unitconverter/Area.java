package com.example.unitconverter;

public class Area {
    // to avoid string matching and lots of bugs enum is created
    public enum area {
        Acres, Ares, Hectares, Square_centimetres, Square_feet, Square_inches, Square_metres
    }

    // this "units" array will be used in dropdown (spinner)
    public static String[] units = {"Acres", "Ares", "Hectares", "Square centimetres", "Square feet", "Square inches", "Square metres",};

    // idea is
    // first convert the value in any unit to the value in "Square_metres" and then to target value
    // the above approach will reduces the no of if else (conditions)
    // the method is public so that it will inaccessible outside the class
    private static double assignWeight(area unit) {
        // weight is simply the value of unit(passed in argument) in "Square_metres"
        double weight;
        if (unit == area.Acres) {
            // 1 acres = 4046.8564224 square metres
            weight = 4046.8564224;
        } else if (unit == area.Ares) {
            weight = 100;
        } else if (unit == area.Hectares) {
            weight = 10_000;
        } else if (unit == area.Square_centimetres) {
            weight = 0.00_01;
        } else if (unit == area.Square_feet) {
            weight = 0.09290304;
        } else if (unit == area.Square_inches) {
            weight = 0.00064516;
        } else {
            weight = 1;
        }
        return weight;
    }


    static public double convert(double source, Area.area sourceUnit, Area.area targetUnit) {
        double sourceWeight;
        double targetWeight;

        // assigning weight to source unit
        sourceWeight = assignWeight(sourceUnit);

        // assigning weight to target unit
        targetWeight = assignWeight(targetUnit);

        // this will convert the source unit value to target unit value
        return (source * sourceWeight) / targetWeight;
    }
}
