package model;

import java.util.HashMap;
import java.util.Map;


public class Product {
    private String name;
    private double depth, width, height, weight;

    private double count;
    private double volume;
    private boolean isHazardous;
    private boolean isFragile;



    // handling instructions as a map
    private Map<String, String> handlingInstructions;

    public Product(String name, double width, double depth, double height, double count, double weight, boolean isHazardous, boolean isFragile) {
        this.name = name;
        this.depth = depth;
        this.height = height;
        this.width = width;
        this.volume = depth * height * width;

        this.weight = weight;

        this.count = count;

        this.isHazardous = isHazardous;
        this.isFragile = isFragile;

        // create a map to store the instructions
        this.handlingInstructions = new HashMap<String, String>();

    }


    public void rotate() {
        double temp = width;
        width = depth;
        depth = temp;
    }



    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double length) {
        this.depth = length;
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

    public void setCount(double count) {
        this.count = count;
    }

    public double getCount(){
        return count;
    }

    public boolean isHazardous() {
        return isHazardous;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setHazardous(boolean hazardous) {
        isHazardous = hazardous;
    }

    public Map<String, String> getHandlingInstructions() {
        return handlingInstructions;
    }

    public void setHandlingInstructions(Map<String, String> handlingInstructions) {
        this.handlingInstructions = handlingInstructions;
    }

    public void addHandlingInstruction(String key, String value) {
        this.handlingInstructions.put(key, value);
    }

    public String getHandlingInstruction(String key) {
        return handlingInstructions.get(key);
    }
}
