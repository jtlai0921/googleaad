package com.edu.vincenttuan.app_viewmodel;

public class BMI {
    private double height;
    private double weight;
    private double bmi;

    public BMI(double height, double weight) {
        this.height = height;
        this.weight = weight;
        double bmi = weight / Math.pow(height/100, 2);
        setBmi(bmi);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "BMI{" +
                "height=" + height +
                ", weight=" + weight +
                ", bmi=" + String.format("%.2f", bmi) +
                '}' + "\n";
    }
}
