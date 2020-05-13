package ca.cmpt213.asn5.Client.Info;

import javafx.scene.text.Text;

/**
 * A class to handle Tokimon information
 */

public class Tokimon {
    private String  name;
    private double weight;
    private double height;
    private double strength;
    private String color;
    private double electric;
    private double freeze;
    private double fire;
    private double fly;
    private double water;
    private Integer id;

    public double getElectric() {
        return electric;
    }
    public Integer getId() {
        return id;
    }

    public double getFreeze() {
        return freeze;
    }

    public double getFire() {
        return fire;
    }

    public double getFly() {
        return fly;
    }

    public double getWater() {
        return water;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getStrength() {
        return strength;
    }

    public String getColor() {
        return color;
    }
}
