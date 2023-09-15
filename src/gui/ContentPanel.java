package gui;

import config.Configuration;
import model.Container;
import tools.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;


public class ContentPanel extends JPanel {

    private JPanel formPanel; // form panel
    private JPanel currentItemsPanel; // current items panel

    private JLabel containerTypeLabel; // container type label

    private JComboBox<Container> containerTypeBox; // container type combo box
    private DefaultComboBoxModel<Container> containerTypeModel; // container type combo box model

    private PlaceholderTextField productNameField; // product name text field
    private PlaceholderTextField amountField; // amount text field

    // dimension placeholder text fields
    private PlaceholderTextField length, width, height, weight;

    private JButton addButton; // run button


    private static Container[] defaultContainers;

    public ContentPanel() {

        // default containers
        defaultContainers = new Container[] {}; // storage unit


        containerTypeLabel = new JLabel("Container Type"); // container type label
        containerTypeLabel.setFont(Configuration.getTextFont());

        containerTypeBox = new JComboBox<>(); // container type combo box
        containerTypeModel = new DefaultComboBoxModel<>(); // container type combo box model
        containerTypeBox.setModel(containerTypeModel); // set container type combo box model


        productNameField = new PlaceholderTextField("NAME", 90,28,18); // product name text field


        amountField = new PlaceholderTextField("AMOUNT", 90,28,18); // amount text field

        // dimension placeholder text fields
        length = new PlaceholderTextField("Length", 90, 28, 18);
        width = new PlaceholderTextField("Width", 90, 28, 18);
        height = new PlaceholderTextField("Height", 90, 28, 18);
        weight = new PlaceholderTextField("Weight", 90, 28, 18);

        addButton = new JButton("ADD"); // run button


        layoutComponents();
        styling();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;

        gc.insets = new Insets(0,0,10,0);
        add(containerTypeLabel, gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,20,0);
        add(containerTypeBox, gc);

        gc.gridy++;
        gc.insets = new Insets(0,0,5,0);
        add(productNameField, gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,20,0);
        add(amountField, gc);

        gc.insets = new Insets(0,0,5,0);
        gc.gridy++;
        add(length, gc);
        gc.gridy++;
        add(width, gc);
        gc.gridy++;
        add(height, gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,20,0);
        add(weight, gc);


        gc.gridy++;
        add(addButton, gc);

    }

    public void setDefaultContainers(Container[] containers) {
        defaultContainers = containers;
        containerTypeBox.setModel(new DefaultComboBoxModel<>(containers));
    }

    private void styling() {

    }
}
