package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Slice {
    private double width, length, height;
    List<Product> filledBoxes;

    public Slice(double width, double length, double height) {
        this.width = width;
        this.length = length;
        this.height = height;

        filledBoxes = new ArrayList<>();
    }

    public void peakFill(List<Product> boxes) {
        if (boxes.isEmpty()) return;

        Product box = boxes.get(0);

        // attempt to place the box
        if (box.getWidth() <= width && box.getLength() <= length && box.getHeight() <= height) {
            // place the box in the slice
            filledBoxes.add(box);

            // create a new sub-slice on top of the placed box
            double newHeight = height - box.getHeight();
            Slice subSlice = new Slice(width, length, newHeight);

            // recursively fill the sub-slice
            subSlice.peakFill(boxes.subList(1, boxes.size()));

            // ff no more sub-slices can be created, start backtracking
            if (newHeight == height) {
                double remainingWidth = width - box.getWidth();
                double remainingLength = length - box.getLength();

                // backtrack and fill left sub-slices
                while (remainingWidth >= box.getWidth() && remainingLength >= box.getLength()) {
                    Slice leftSubSlice = new Slice(remainingWidth, remainingLength, height);
                    leftSubSlice.peakFill(boxes.subList(1, boxes.size()));
                    filledBoxes.addAll(leftSubSlice.filledBoxes);
                    remainingWidth -= box.getWidth();
                    remainingLength -= box.getLength();
                }
            }
        }

        // continue with the remaining boxes
        peakFill(boxes.subList(1, boxes.size()));
    }

    // reduce to 2D
    public void reduceFrom3DTo2D() {
        this.height = 0;
    }

    // divide into 4 sub-slices
    public List<Slice> divide() {
        List<Slice> subSlices = new ArrayList<>();

        double newWidth = width / 2;
        double newLength = length / 2;

        // Create four sub-slices
        subSlices.add(new Slice(newWidth, newLength, height));
        subSlices.add(new Slice(newWidth, newLength, height));
        subSlices.add(new Slice(newWidth, newLength, height));
        subSlices.add(new Slice(newWidth, newLength, height));

        return subSlices;
    }

    public void fillWithBoxes(List<Product> boxes) {
        while (!boxes.isEmpty()) {
            boolean boxPlaced = false;

            for (int i = 0; i < boxes.size(); i++) {
                Product box = boxes.get(i);

                if (box.getWidth() <= width && box.getLength() <= length && box.getHeight() <= height) {
                    filledBoxes.add(box);
                    boxes.remove(i);

                    boxPlaced = true;
                    break;
                }
            }

            if (!boxPlaced) break;
        }
    }

    public void push(Slice otherSlice) {
        // Calculate the new height after pushing
        double newHeight = Math.max(height, otherSlice.height);
        double combinedWidth = width + otherSlice.width;
        double combinedLength = length + otherSlice.length;

        // Create a new slice with the combined dimensions
        Slice combinedSlice = new Slice(combinedWidth, combinedLength, newHeight);

        // Copy the filled boxes from both slices to the combined slice
        combinedSlice.filledBoxes.addAll(filledBoxes);
        combinedSlice.filledBoxes.addAll(otherSlice.filledBoxes);

        // Update this slice with the combined slice's data
        this.width = combinedWidth;
        this.length = combinedLength;
        this.height = newHeight;
        this.filledBoxes = combinedSlice.filledBoxes;
    }




}