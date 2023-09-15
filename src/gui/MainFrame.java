package gui;

import config.Configuration;
import model.Container;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TitlePanel titlePanel;
    private ContentPanel contentPanel;

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

        // set default containers
        contentPanel.setDefaultContainers(new Container[]{
                new Container("Box Truck", 24, 8, 8), // box truck
                new Container("Shipping Container",40, 8, 8.5), // shipping container
                new Container("Storage Unit",20, 10, 8)}); // storage unit



        setLayout(new BorderLayout()); // set layout
        add(titlePanel, BorderLayout.NORTH); // add title panel
        add(contentPanel, BorderLayout.CENTER); // add content panel






    }


}
