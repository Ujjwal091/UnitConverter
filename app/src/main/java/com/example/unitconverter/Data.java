package com.example.unitconverter;

public class Data {
    public enum data {
        Bits, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes,
    }

    // this "units" array will be used in dropdown (spinner)
    public static String[] units = {"Bits", "Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes",};


    // method is private so that it will inaccessible outside the class
    // here we first convert the source value in any unit to the value in Megabytes
    // then we convert convert the value in Megabytes to the value in target value
    private static double assignWeight(data unit) {
        double weight;
        if (unit == data.Bits) {
            // 1 bits = 1.19209290E-7 megabytes
            weight = 1.19209290E-7;
        } else if (unit == data.Bytes) {
            weight = 9.53674316E-7;
        } else if (unit == data.Kilobytes) {
            weight = 0.0009765625;
        } else if (unit == data.Gigabytes) {
            weight = 1024;
        } else if (unit == data.Terabytes) {
            weight = 1024 * 1024;
        } else {
            weight = 1;
        }
        return weight;
    }

    static public double convert(double source, Data.data sourceUnit, Data.data targetUnit) {
        double sourceWeight;
        double targetWeight;

        // assigning weight to source unit
        sourceWeight = assignWeight(sourceUnit);

        // assigning weight to target unit
        targetWeight = assignWeight(targetUnit);

        // this will convert the data in one unit to the data in another unit
        return (source * sourceWeight) / targetWeight;
    }

}
