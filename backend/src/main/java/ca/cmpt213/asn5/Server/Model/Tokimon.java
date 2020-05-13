package ca.cmpt213.asn5.Server.Model;

/**
 * Class to represent Tokimon
 */

public class Tokimon {
    private long id;
    private String name;
    private double weight;
    private double height;
    private Double fire;
    private Double fly;
    private Double water;
    private Double electric;
    private Double freeze;
    private double strength;
    private String color;


    public Tokimon(String Name, double Weight, double Height, Double Fly, Double Fire, Double Water, Double Electric, Double Freeze, double Strength, String Color) {
        this.name = Name;
        this.weight = Weight;
        this.height = Height;
        this.fly = Fly;
        this.water = Water;
        this.electric = Electric;
        this.freeze = Freeze;
        this.fire = Fire;
        this.strength = Strength;
        this.color = Color;
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Double getFire() {
        return fire;
    }

    public Double getFly() {
        return fly;
    }

    public Double getWater() {
        return water;
    }

    public Double getElectric() {
        return electric;
    }

    public Double getFreeze() {
        return freeze;
    }
}
