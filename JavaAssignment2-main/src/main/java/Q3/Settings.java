package Q3;

import java.util.Date;

public class Settings {

    private String name;

    private double height;

    private double weight;

    private Date birthday;

    private double functionalThresholdPower;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getFunctionalThresholdPower() {
        return functionalThresholdPower;
    }

    public void setFunctionalThresholdPower(double functionalThresholdPower) {
        this.functionalThresholdPower = functionalThresholdPower;
    }
}
