package model;

import java.util.ArrayList;
import java.util.List;

public class ContainerOptimization {

    public ContainerOptimization(Container container, ArrayList<Product> products) {


        // Create a slice with dimensions (8, 6, 5)
        for (Slice slice : container.getSlices()) {

            // Apply peak filling
            slice.peakFill(products);

            // Reduce from 3D to 2D
            slice.reduceFrom3DTo2D();

            // Divide the slice into sub-slices
            List<Slice> subSlices = slice.divide();

            // Fill sub-slices with boxes
            for (Slice subSlice : subSlices) {
                subSlice.fillWithBoxes(products);
            }

            // Push slices together
            for (int i = 1; i < subSlices.size(); i++) {
                subSlices.get(0).push(subSlices.get(i));
            }

            // Output the final filled boxes in the slice
            System.out.println("Final Filled Boxes in the Slice:");
            for (Product filledBox : subSlices.get(0).filledBoxes) {
                System.out.println("Width: " + filledBox.getWidth() + ", Length: " + filledBox.getLength() + ", Height: " + filledBox.getHeight());
            }
        }
    }
}
