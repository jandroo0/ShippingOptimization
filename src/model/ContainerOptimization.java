package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContainerOptimization {

    private double wastedSpace;
    private List<Product> placedBoxes;
    private double currentWeight;

    public ContainerOptimization(Container container, List<Product> boxes) {


//        results()


    }


    public String results(Container container, List<Product> boxes) {
        wastedSpace = 0;
        placedBoxes = new ArrayList<>();
        currentWeight = 0;

        // Convert container dimensions from feet to inches
        double containerWidth = container.getWidth();
        double containerDepth = container.getLength();
        double containerHeight = container.getHeight();
        double maxWeight = container.getMaxWeight();

        // some containers do not have a max weight
        if (maxWeight == 0) maxWeight = Double.MAX_VALUE; // set max weight to infinite
        // Sort boxes by surface area (descending)
        Collections.sort(boxes, (box1, box2) -> (int) (box2.getWidth() * box2.getLength() - box1.getWidth() * box1.getLength()));

        for (Product box : boxes) {
            while (box.getCount() > 0) {

                boolean placed = false;
                for (int i = 0; i < placedBoxes.size(); i++) {
                    Product placedBox = placedBoxes.get(i);

                    if (placedBox.getWidth() >= box.getWidth() && placedBox.getLength() >= box.getLength() &&
                            currentWeight + box.getWeight() <= maxWeight) {

                        // Check if box is hazardous
                        if (box.isHazardous()) {
                            // Special handling for hazardous materials
                            if (placedBox.isHazardous()) {
                                // If the current placement is also hazardous, continue to the next placement
                                continue;
                            }
                            // Find the next non-hazardous placement
                            while (i < placedBoxes.size() && placedBoxes.get(i).isHazardous()) {
                                i++;
                            }
                            if (i >= placedBoxes.size()) {
                                // No non-hazardous placements left, add a new layer
                                placedBox.setWidth(containerWidth);
                                placedBox.setLength(containerDepth);
                                placedBox.setHeight(placedBox.getHeight() + box.getHeight());
                            } else {
                                placedBox = placedBoxes.get(i);
                                placedBox.setWidth(placedBox.getWidth() - box.getWidth());
                                placedBox.setLength(placedBox.getLength() - box.getLength());
                                placedBox.setHeight(placedBox.getHeight() + box.getHeight());
                            }
                        } else {
                            // Follow normal placement logic
                            placedBox.setWidth(placedBox.getWidth() - box.getWidth());
                            placedBox.setLength(placedBox.getLength() - box.getLength());
                            placedBox.setHeight(placedBox.getHeight() + box.getHeight());
                        }

                        // Check handling instructions
                        for (String key : box.getHandlingInstructions().keySet()) {
                            String value = box.getHandlingInstructions().get(key);
                            // Implement checks based on handling instructions
                            if (key.equals("orientation") && value.equals("upright")) {
                                if (box.getLength() > box.getWidth()) {
                                    box.rotate();
                                }
                            }
                            // Add more handling instructions checks as needed
                        }

                        currentWeight += box.getWeight();
                        placed = true;
                        break;
                    }
                }

                if (!placed) {
                    if (box.getWidth() <= containerWidth && box.getLength() <= containerDepth &&
                            currentWeight + box.getWeight() <= maxWeight) {
                        placedBoxes.add(new Product(box.getName(), containerWidth, box.getLength(), box.getHeight(), 1, box.getWeight(), box.isHazardous()));
                        currentWeight += box.getWeight();
                    } else {
                        wastedSpace += box.getVolume();
                    }
                    box.setCount(box.getCount() + 1);
                }
            }
        }


        double usedSpace = containerWidth * containerDepth * containerHeight - wastedSpace;

        StringBuilder results = new StringBuilder();

        // divide by 1728 ---- in^3 --> ft^3

        results.append("Container Volume: " + String.format("%.2f", (containerWidth * containerDepth * containerHeight) / 1728));
        results.append("\nUsed Space: " + String.format("%.2f", usedSpace / 1728));
        results.append("\nWasted Space: " + String.format("%.2f", wastedSpace / 1728));

        return results.toString();
    }
}
