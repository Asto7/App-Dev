package com.example.unitconverter;

import android.util.Log;

public class Converter {
    String FROM, TO, TYPE;

    public Converter(String from, String to, String type) {
        FROM = from.toUpperCase();
        TO = to.toUpperCase();
        TYPE = type.toUpperCase();
    }

    public double convert(double input) {

        if (TO.equals(FROM))
            return input;

        switch (TYPE) {
            case "LENGTH":
                return convertLength(FROM, TO, input);
            case "TEMPERATURE":
                return convertTemperature(input);
            case "TIME":
                return convertTime(input);
            case "MASS":
                return convertMass(input);
            case "ANGLE":
                return convertAngle(input);
            case "SPEED":
                return convertSpeed(input);
            case "PRESSURE":
                return convertPressure(input);
        }

        return input;
    }

    public double convertLength(String from, String to, double input) {
        double constant = 1;
        from = from.toUpperCase();

        switch (from) {

            case "MILLIMETER":
                constant = (1 / convertLength("KILOMETER", "MILLIMETER", input)) * convertLength("KILOMETER", to, input);
                break;

            case "CENTIMETER":
                constant = (1 / convertLength("KILOMETER", "CENTIMETER", input)) * convertLength("KILOMETER", to, input);
                break;

            case "METER":
                constant = (1 / convertLength("KILOMETER", "METER", input)) * convertLength("KILOMETER", to, input);
                break;

            case "KILOMETER":
                if (to.equals("MILLIMETER"))
                    constant = 1000000;

                else if (to.equals("CENTIMETER"))
                    constant = 100000;

                else if (to.equals("METER"))
                    constant = 1000;

                else if (to.equals("INCH"))
                    constant = 39370.079;

                else if (to.equals("FOOT"))
                    constant = 3280.84;

                else if (to.equals("YARD"))
                    constant = 1093.6133;

                else if (to.equals("MILE"))
                    constant = 0.6213712;
                break;

            case "INCH":
                constant = (1 / convertLength("MILE", "INCH", input)) * convertLength("MILE", to, input);
                break;

            case "FOOT":
                constant = (1 / convertLength("MILE", "FOOT", input)) * convertLength("MILE", to, input);
                break;

            case "YARD":
                constant = (1 / convertLength("MILE", "YARD", input)) * convertLength("MILE", to, input);
                break;

            case "MILE":
                if (to.equals("MILLIMETER"))
                    constant = 1609344;

                else if (to.equals("CENTIMETER"))
                    constant = 160934.4;

                else if (to.equals("METER"))
                    constant = 1609.344;

                else if (to.equals("KILOMETER"))
                    constant = 1.609344;

                else if (to.equals("INCH"))
                    constant = 63360;

                else if (to.equals("FOOT"))
                    constant = 5280;

                else if (to.equals("YARD"))
                    constant = 1760;

                break;
        }
        return constant * input;
    }

    private double convertPressure(double input) {

        double value = input;
        switch (TO) {
            case "TORR":
                value = (value * 750.0616828);
                break;
            case "PASCAL":
                value = (value * 100000);
                break;
        }
        switch (FROM) {
            case "TORR":
                value = (value / 750.0616828);
                break;
            case "PASCAL":
                value = (value / 100000);
                break;
        }
        return value;
    }

    private double convertSpeed(double input) {
        double value = input;
        switch (TO) {
            case "MILES_PER_HOUR":
                value = (value * 2.237);
                break;
            case "KILOMETER_PER_HOUR":
                value = (value * 3.6);
                break;
        }
        switch (FROM) {
            case "MILES_PER_HOUR":
                value = (value / 2.237);
                break;
            case "KILOMETER_PER_HOUR":
                value = (value / 3.6);
                break;
        }
        return value;
    }

    private double convertAngle(double input) {
        double value = input;
        switch (TO) {
            case "DEGREE":
                value = (value * Math.PI * 180);
                break;
            case "GRADIAN":
                value = (value * 63.6619783228);
                break;
        }
        switch (FROM) {
            case "DEGREE":
                value = (value / (Math.PI * 180));
                break;
            case "GRADIAN":
                value = (value / 63.6619783228);
                break;
        }
        return value;
    }

    private double convertMass(double input) {

        double value = input;
        switch (TO) {
            case "KILOGRAM":
                value = (value * 1000);
                break;
            case "GRAM":
                value = value * 1000000;
                break;
            case "MILLIGRAM":
                value = value * 1000000000;
                break;
            case "POUND":
                value = (value / 0.00045359237);
                break;
        }
        switch (FROM) {
            case "KILOGRAM":
                value = (value / 1000);
                break;
            case "GRAM":
                value = value / 1000000;
                break;
            case "MILLIGRAM":
                value = value / 1000000000;
                break;
            case "POUND":
                value = value * 0.00045359237;
                break;
        }
        return value;
    }

    private double convertTime(double input) {

        double value = input;
        switch (TO) {
            case "SECOND":
                value = (value * 86400);
                break;
            case "MINUTE":
                value = value * 1440;
                break;
            case "HOUR":
                value = value * 24;
                break;
            case "MILLISECOND":
                value = (value * (8.64 * 10000000));
                break;
        }
        switch (FROM) {
            case "SECOND":
                value = (value / 86400);
                break;
            case "MINUTE":
                value = value / 1440;
                break;
            case "HOUR":
                value = value / 24;
                break;
            case "MILLISECOND":
                value = (value / (8.64 * 10000000));
                break;
        }
        return value;
    }

    private double convertTemperature(double input) {
        double value = input;

        if (FROM.equals("FAHRENHEIT")) value = ((value - 32) * 5.0 / 9);
        else if (FROM.equals("KELVIN")) value = value - 273.15;

        if (TO.equals("FAHRENHEIT")) value = ((value * 9 / 5) + 32);
        else if (TO.equals("KELVIN")) value = value + 273.15;

        return value;
    }
}
