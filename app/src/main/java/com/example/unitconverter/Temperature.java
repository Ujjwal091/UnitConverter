package com.example.unitconverter;

public class Temperature {
    public enum temperature {
        Celsius, Fahrenheit, Kelvin

    }

    public static String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

    public static double convert(double source, Temperature.temperature sourceUnit, Temperature.temperature targetUnit) {

        if (sourceUnit == temperature.Fahrenheit) {
            // fahrenheit to celsius
            // (°F − 32) × 5/9 = °C
            if (targetUnit == temperature.Celsius) {
                return ((source - 32) * 5 / 9);
            }
            // fahrenheit to kelvin
            // (°F − 32) × 5/9 + 273.15 = K
            else if (targetUnit == temperature.Kelvin) {
                return ((source - 32) * 5 / 9 + 273.15);
            } else {
                return source;
            }
        } else if (sourceUnit == temperature.Celsius) {
            // celsius to fahrenheit
            // (°C × 9/5) + 32 = °F
            if (targetUnit == temperature.Fahrenheit) {
                return ((source * 9.0 / 5.0) + 32);
            }
            // celsius to kelvin
            // °C + 273.15 = K
            else if (targetUnit == temperature.Kelvin) {
                return (source + 273.15);
            } else {
                return source;
            }
        } else {
            // kelvin to celsius
            // K − 273.15 = °C
            if (targetUnit == temperature.Celsius) {
                return (source - 273.15);
            }
            // kelvin to fahrenheit
            // (K − 273.15) × 9/5 + 32 = °F
            else if (targetUnit == temperature.Fahrenheit) {
                return ((source - 273.15) * 9.0 / 5.0 + 32);
            } else {
                return source;
            }
        }

    }
}
