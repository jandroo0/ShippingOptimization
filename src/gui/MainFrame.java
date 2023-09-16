package gui;

import config.Configuration;
import listener.FormListener;
import listener.ResultsPanelListener;
import model.Container;
import model.Product;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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
        ArrayList<Product> products_1 = new ArrayList<>();
        products_1.add(new Product("Brake Calipers",12, 6, 6, 100.0, 20, false, false));
        products_1.add(new Product("Batteries",9, 5, 8, 100.0, 40.0, false, false));
        products_1.add(new Product("Alternator",10, 6, 8, 100.0, 15.0, false, false));

        ArrayList<Product> products_2 = new ArrayList<>();
        products_2.add(new Product("Brake Calipers",12, 6, 6, 100.0, 20, false, false));
        products_2.add(new Product("Batteries",9, 5, 8, 100.0, 40.0, false, false));
        products_2.add(new Product("Alternator",10, 6, 8, 100.0, 15.0, false, false));

        products_2.add(new Product("Brake Calipers",43.6, 24.3, 43.4, 10.0, 100.0, false, false));
        products_2.add(new Product("Brake Calipers",50, 30, 20, 5.0, 30.0, false, false));
        products_2.add(new Product("Brake Calipers",70, 10, 5, 15.0, 20.0, false, false));
        products_2.add(new Product("Brake Calipers",10, 10, 10, 5.0, 5.0, false, false));
        products_2.add(new Product("Brake Calipers",5, 2.5, 3, 20.0, 10.0, false, false));
        products_2.add(new Product("Brake Calipers",30, 10, 40, 10.0, 200.0, false, false));
        products_2.add(new Product("Brake Calipers",5, 5, 20, 5.0, 3.0, false, false));
        products_2.add(new Product("Brake Calipers",80, 80, 10, 15.0, 102.0, false, false));
        products_2.add(new Product("Brake Calipers",6, 10, 7, 4.0, 7.0, false, false));
        products_2.add(new Product("Brake Calipers",60, 80, 80, 20.0, 50.0, false, false));



        // test run

        // BOX TRUCK
        System.out.println("BOX TRUCK");
        containerOptimization(BOX_TRUCK.getWidth(), BOX_TRUCK.getDepth(), BOX_TRUCK.getHeight(), BOX_TRUCK.getMaxWeight(), products_1);
        System.out.println("-------------------------------------------");

//         SHIPPING CONTAINER 40 ft
        System.out.println("SHIPPING CONTAINER");
        containerOptimization(SHIP_CONTAINER.getWidth(), SHIP_CONTAINER.getDepth(), SHIP_CONTAINER.getHeight(), SHIP_CONTAINER.getMaxWeight(), products_2);
        System.out.println("-------------------------------------------");


//        // test products
//        Random rand = new Random();
//
//        ArrayList<Product> products_1 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) { // 10 different products
//            int width = rand.nextInt(15) + 5; // random width between 5 and 20 inches
//            int depth = rand.nextInt(15) + 5; // random depth between 5 and 20 inches
//            int height = rand.nextInt(15) + 5; // random height between 5 and 20 inches
//            double weight = rand.nextDouble(80) + 2; // random weight between 2 and 80 lbs
//            double count = rand.nextDouble(150) + 25; // random count between 25 and 150
//
//            products_1.add(new Product("Product " + i, width, depth, height, count, weight, false, false));
//        }
//
//        ArrayList<Product> products_2 = new ArrayList<>();
//        for (int i = 0; i < 5; i++) { // 5 different products
//            int width = rand.nextInt(15) + 5; // random width between 5 and 20 inches
//            int depth = rand.nextInt(15) + 5; // random depth between 5 and 20 inches
//            int height = rand.nextInt(15) + 5; // random height between 5 and 20 inches
//            double weight = rand.nextDouble(110) + 2; // random weight between 5 and 100 lbs
//            double count = rand.nextDouble(250) + 25; // random count between 25 and 250
//
//
//            products_2.add(new Product("Product " + i, width, depth, height, count, weight, false, false));
//        }
//
//        ArrayList<Product> products_3 = new ArrayList<>();
//        for (int i = 0; i < 100; i++) { // 5 different products
//            int width = rand.nextInt(30) + 5; // random width between 5 and 20 inches
//            int depth = rand.nextInt(24) + 5; // random depth between 5 and 20 inches
//            int height = rand.nextInt(20) + 5; // random height between 5 and 20 inches
//            double weight = rand.nextDouble(250) + 2; // random weight between 5 and 100 lbs
//            double count = rand.nextDouble(500) + 25; // random count between 25 and 500
//
//
//            products_3.add(new Product("Product " + i, width, depth, height, count, weight, false, false));
//        }
//
//
//        // test runs
//
//        // BOX TRUCK
//        System.out.println("BOX TRUCK");
//        containerOptimization(BOX_TRUCK.getWidth(), BOX_TRUCK.getDepth(), BOX_TRUCK.getHeight(), BOX_TRUCK.getMaxWeight(), products_1);
//        containerOptimization(BOX_TRUCK.getWidth(), BOX_TRUCK.getDepth(), BOX_TRUCK.getHeight(), BOX_TRUCK.getMaxWeight(), products_2);
//        System.out.println("-------------------------------------------");
//
//        // SHIPPING CONTAINER 40 ft
//        System.out.println("SHIPPING CONTAINER");
//        containerOptimization(SHIP_CONTAINER.getWidth(), SHIP_CONTAINER.getDepth(), SHIP_CONTAINER.getHeight(), SHIP_CONTAINER.getMaxWeight(), products_1);
//        containerOptimization(SHIP_CONTAINER.getWidth(), SHIP_CONTAINER.getDepth(), SHIP_CONTAINER.getHeight(), SHIP_CONTAINER.getMaxWeight(), products_2);
//        System.out.println("-------------------------------------------");





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