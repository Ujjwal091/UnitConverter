package com.example.unitconverter;

public class Volume {
    public enum volume {
        Litres, Millilitres, Cubic_centimetres, Cubic_metres, Cubic_inches,
    }

    public static String[] units = {"Litres", "Millilitres", "Cubic centimetres", "Cubic metres", "Cubic inches",};

    // value in source unit is first converted to the value in cubic metres
    // then the value in cubic metres is converted to the value in target unit
    private static double assignWeight(volume unit) {
        double weight;

        if (unit == volume.Litres) {
            weight = 0.001;
        } else if (unit == volume.Millilitres || unit == volume.Cubic_centimetres) {
            weight = 0.000_001;
        } else if (unit == volume.Cubic_inches) {
            weight = 0.0000163871;
        } else {
            weight = 1;
        }
        return weight;
    }

    static public double convert(double source, Volume.volume sourceUnit, Volume.volume targetUnit) {
        double sourceWeight;
        double targetWeight;

        // assigning weight to source unit
        sourceWeight = assignWeight(sourceUnit);

        // assigning weight to target unit
        targetWeight = assignWeight(targetUnit);

        return (source * sourceWeight) / targetWeight;
    }
}
