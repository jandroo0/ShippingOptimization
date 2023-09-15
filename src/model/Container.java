package model;

import java.util.LinkedList;

public class Container {
    private String type;
    private double length, width, height, maxWeight;
    private double volume;

    private LinkedList<Product> products;
    private LinkedList<Slice> slices;

    public Container(String type, double length, double width, double height) {
        this.type = type;
        this.length = length;
        this.width = width;
        this.height = height;

        this.volume = length * width * height;

        this.products = new LinkedList<>();
        this.slices = new LinkedList<>();

        createSlices();

    }

    // add slices to the container
    public void createSlices() {
        double remainingHeight = height;
        while (remainingHeight > 0) {
            // Create a new slice with the same width and length as the container
            double sliceHeight = Math.min(remainingHeight, height);
            Slice slice = new Slice(width, length, sliceHeight);

            // Add the slice to the list of slices
            slices.add(slice);

            // Update remaining height
            remainingHeight -= sliceHeight;
        }
    }

    // GETTERS


    public LinkedList<Slice> getSlices() {
        return slices;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }
    public String getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return volume;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    // SETTERS
    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
    @Override
    public String toString() {
        return type;
    }


}
