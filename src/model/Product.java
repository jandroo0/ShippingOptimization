package model;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private String name;
    private double length, width, height, weight;
    private double volume;
    private boolean isHazardous;


    // handling instructions as a map
    private Map<String, String> handlingInstructions;

    public Product(String name, double length, double height, double width, boolean isHazardous) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.width = width;
        this.volume = length * height * width;

        this.weight = length * height * width;
        this.isHazardous = isHazardous;

        // create a map to store the instructions
        this.handlingInstructions = new HashMap<String, String>();

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
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

    public boolean isHazardous() {
        return isHazardous;
    }

    public void setHazardous(boolean hazardous) {
        isHazardous = hazardous;
    }

    public String getHandlingInstructions(String key) {
        return handlingInstructions.get(key);
    }

    public void addHandlingInstruction(String key, String value) {
        this.handlingInstructions.put(key, value);
    }
}
