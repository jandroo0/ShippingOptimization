package gui;

import config.Configuration;
import listener.FormListener;
import model.Container;
import model.ContainerOptimization;
import model.Product;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

        // set default containers
        contentPanel.setDefaultContainers(new Container[]{
                new Container("Box Truck", 24, 8, 8), // box truck
                new Container("Shipping Container",40, 8, 8.5), // shipping container
                new Container("Storage Unit",20, 10, 8)}); // storage unit

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Brake Calipers",12, 6, 6, 100.0, 20, false));
        products.add(new Product("Batteries",9, 5, 8, 100.0, 40.0, false));
        products.add(new Product("Alternator",10, 6, 8, 100.0, 15.0, false));
        products.add(new Product("Starter Motor",8, 6, 8, 100.0, 10.0, false));


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
                ContainerOptimization containerOptimization = new ContainerOptimization(container, products);
            }


        });





    }


}
