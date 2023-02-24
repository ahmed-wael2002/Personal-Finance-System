package com.example.project;

public class Currency {
    private double usdVal;
    private String [] converterStr = {"usd", "cny", "egp", "eur", "gbp", "cad"};
    private double [] converterVal = {1.000, 0.152, 0.054, 1.064, 1.257, 0.780};


    public Currency(double value, String currencyType) {
        usdVal = value / getConversionRate(currencyType);
    }

    private int getIndex(String arr[], String elem) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == elem) return i;
        }; return 0;
    }
    private double getConversionRate(String currencyType) {
        int indexOfCurrencyType = getIndex(converterStr, currencyType);
        return 1/converterVal[indexOfCurrencyType];
    }
    public double getVal(String currencyType) {
        return usdVal * getConversionRate(currencyType);
    }

    public double editMoney(double value, String currencyType) {
        double conversionRate = getConversionRate(currencyType);
        usdVal += value * conversionRate;
        return usdVal * conversionRate;
    }

    public String toString() {
        return  "Class Currency";
    }
}
