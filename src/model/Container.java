package model;

import java.util.LinkedList;

public class Container {
    private String type;
    private double depth, width, height, maxWeight;
    private double volume;

    private LinkedList<Product> products;

    public Container(String type, double width, double depth, double height) {
        this.type = type;
        this.depth = depth;
        this.width = width;
        this.height = height;


        this.products = new LinkedList<>();
    }


    // GETTERS

    public LinkedList<Product> getProducts() {
        return products;
    }
    public String getType() {
        return type;
    }

    public double getDepth() {
        return depth;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return depth*width*height;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    // SETTERS

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }


    @Override
    public String toString() {
        return type;
    }


}
