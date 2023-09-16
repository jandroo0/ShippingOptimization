package gui;

import config.Configuration;
import listener.FormListener;
import listener.ResultsPanelListener;
import model.Container;
import model.ContainerOptimization;
import model.Product;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

public class MainFrame extends JFrame {

    private TitlePanel titlePanel;
    private ContentPanel contentPanel;
    private ResultsPanel resultsPanel;

    private Controller controller;


    public MainFrame(){
        super("EZSHIPPING"); // set title

        setSize(Configuration.getFRAME_WIDTH(), Configuration.getFRAME_HEIGHT()); // set size
        setResizable(false); // set not resizable
        setLocationRelativeTo(null); // set location to center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set exit on close
        setVisible(true); // set visible

        // title panel
        titlePanel = new TitlePanel();

        // content panel
        contentPanel = new ContentPanel();

        // results panel
        resultsPanel = new ResultsPanel();

        controller = new Controller();

        Container BOX_TRUCK = new Container("Box Truck", 8, 24, 8);
        BOX_TRUCK.setMaxWeight(26000);

        Container SHIP_CONTAINER = new Container("Shipping Container 40ft", 8, 40, 8.5);
        SHIP_CONTAINER.setMaxWeight(59200);

        Container SHIP_CONTAINER_10 = new Container("Shipping Container 10ft", 8, 10, 8.5);
        SHIP_CONTAINER_10.setMaxWeight(13200);

        Container SHIP_CONTAINER_20 = new Container("Shipping Container 20ft", 8, 20, 8.5);
        SHIP_CONTAINER_20.setMaxWeight(23400);

        Container STORAGE_UNIT = new Container("Storage Unit", 10, 20, 8);
        STORAGE_UNIT.setMaxWeight(0);

        // set default containers
        contentPanel.setDefaultContainers(new Container[]{BOX_TRUCK, SHIP_CONTAINER_10, SHIP_CONTAINER_20, SHIP_CONTAINER, STORAGE_UNIT}); // storage unit



        // test products
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Brake Calipers",12, 6, 6, 100.0, 20, false, false));
        products.add(new Product("Batteries",9, 5, 8, 100.0, 40.0, false, false));
        products.add(new Product("Alternator",10, 6, 8, 100.0, 15.0, false, false));

        // test run
        containerOptimization(BOX_TRUCK.getWidth(), BOX_TRUCK.getDepth(), BOX_TRUCK.getHeight(), BOX_TRUCK.getMaxWeight(), products);


//        containerOptimization = new ContainerOptimization();

        setLayout(new BorderLayout()); // set layout
        add(titlePanel, BorderLayout.NORTH); // add title panel
        add(contentPanel, BorderLayout.WEST); // add content panel
        add(resultsPanel, BorderLayout.EAST); // add results panel


        contentPanel.setFormListener(new FormListener() {
            @Override
            public void addProductsEvent(Product product) {
                contentPanel.addProduct(product);
            }

            @Override
            public void runContainerOptimizationEvent(Container container, LinkedList<Product> products) {
                resultsPanel.setResultsAreaText(containerOptimization(container.getWidth(), container.getDepth(), container.getHeight(), container.getMaxWeight(), products));
            }
        });

        resultsPanel.setResultsPanelListener(new ResultsPanelListener() {
            @Override
            public void clearResults() {
                contentPanel.clearList();
            }
        });

    }

    private String containerOptimization(double containerWidthFt, double containerDepthFt, double containerHeightFt, double maxWeightLbs, List<Product> products) {
        double wastedSpace = 0;
        List<Product> placedBoxes = new ArrayList<>();
        double currentWeight = 0;

        // Convert container dimensions from feet to inches
        double containerWidth = containerWidthFt * 12;
        double containerDepth = containerDepthFt * 12;
        double containerHeight = containerHeightFt * 12;
        double maxWeight = maxWeightLbs;
        if(maxWeightLbs == 0) maxWeight = Double.MAX_VALUE;

        // Sort boxes by surface area (descending)
        Collections.sort(products, (box1, box2) -> (int) (box2.getWidth() * box2.getDepth() - box1.getWidth() * box1.getDepth()));

        for (Product box : products) {
            while (box.getCount() > 0) {

                boolean placed = false;
                for (int i = 0; i < placedBoxes.size(); i++) {
                    Product placedBox = placedBoxes.get(i);

                    if (placedBox.getWidth() >= box.getWidth() && placedBox.getDepth() >= box.getDepth() &&
                            currentWeight + box.getWeight() <= maxWeight) {

                        // Check if box is hazardous
                        if (box.isHazardous() || box.isFragile()) {
                            // Special handling for hazardous materials
                            if (placedBox.isHazardous() || box.isFragile()) {
                                // If the current placement is also hazardous, continue to the next placement
                                continue;
                            }
                            // Find the next non-hazardous placement
                            while (i < placedBoxes.size() && placedBoxes.get(i).isHazardous() && placedBoxes.get(i).isFragile()) {
                                i++;
                            }
                            if (i >= placedBoxes.size()) {
                                // No non-hazardous placements left, add a new layer
                                placedBox.setWidth(containerWidth);
                                placedBox.setDepth(containerDepth);
                                placedBox.setHeight(placedBox.getHeight() + box.getHeight() );
                            } else {
                                placedBox = placedBoxes.get(i);
                                placedBox.setWidth(placedBox.getWidth() - box.getWidth());
                                placedBox.setDepth(placedBox.getDepth() - box.getDepth());
                                placedBox.setHeight(placedBox.getHeight() + box.getHeight());
                            }
                        }else {
                            // Follow normal placement logic
                            placedBox.setWidth(placedBox.getWidth() - box.getWidth());
                            placedBox.setDepth(placedBox.getDepth() - box.getDepth());
                            placedBox.setHeight(placedBox.getHeight() + box.getHeight());
                        }

                        // Check handling instructions
                        for (String key : box.getHandlingInstructions().keySet()) {
                            String value = box.getHandlingInstructions().get(key);
                            // Implement checks based on handling instructions
                            if (key.equals("orientation") && value.equals("upright")) {
                                if (box.getDepth() > box.getWidth()) {
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
                    if (box.getWidth() <= containerWidth && box.getDepth() <= containerDepth &&
                            currentWeight + box.getWeight() <= maxWeight) {
                        placedBoxes.add(new Product(box.getName(), containerWidth, containerDepth, box.getHeight(), 1, box.getWeight(), box.isHazardous(), box.isFragile()));
                        currentWeight += box.getWeight();
                    } else {
                        wastedSpace += box.getVolume();
                    }
                    box.setCount(box.getCount() - 1);
                }
            }
        }

        double usedSpace = containerWidth * containerDepth * containerHeight - wastedSpace;

        // divide by 1728 ---- in^3 --> ft^3
//        System.out.println("Container Volume: " + String.format("%.2f", (containerWidth * containerDepth * containerHeight) / 1728));
//        System.out.println("Used Space: " + String.format("%.2f", usedSpace / 1728));
//        System.out.println("Wasted Space: " + String.format("%.2f", wastedSpace / 1728));

        StringBuilder results = new StringBuilder();

        results.append("\nContainer Volume: ").append(String.format("%.2f", (containerWidth * containerDepth * containerHeight) / 1728));
        results.append("\nUsed Space: ").append(String.format("%.2f", usedSpace / 1728));
        results.append("\nWasted Space: ").append(String.format("%.2f", wastedSpace / 1728));


        System.out.println(results);

        return results.toString();

    }


}