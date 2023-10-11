package com.example.unitconverter;

public class Mass {
    public enum mass {
        Tons, Pounds, Ounces, Kilogrammes, Grams,
    }


    // this "units" array of string will be used for dropdown
    public static String[] units = {"Tons", "Pounds", "Ounces", "Kilogrammes", "Grams",};


    // first the value in source unit is first converted to the value in kilogrammes
    // then the value in kilogrammes is converted to value in target unit
    private static double assignWeight(mass unit) {
        double weight;

        if (unit == mass.Tons) {
            // 1 tons = 1000 kilogrammes
            weight = 1000;
        } else if (unit == mass.Pounds) {
            weight = 0.45359237;
        } else if (unit == mass.Ounces) {
            weight = 0.0283495231;
        } else if (unit == mass.Grams) {
            weight = 0.001;
        } else {
            weight = 1;
        }
        return weight;
    }

    static public double convert(double source, Mass.mass sourceUnit, Mass.mass targetUnit) {
        double sourceWeight;
        double targetWeight;

        // assigning weight to source unit
        sourceWeight = assignWeight(sourceUnit);

        // assigning weight to target unit
        targetWeight = assignWeight(targetUnit);

        return (source * sourceWeight) / targetWeight;
    }

}
