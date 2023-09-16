package gui;

import config.Configuration;
import listener.FormListener;
import model.Container;
import model.Product;
import tools.MenuCustomList;
import tools.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ContentPanel extends JPanel {

    private JPanel formPanel; // form panel
    private JPanel currentItemsPanel; // current items panel

    private JLabel containerTypeLabel; // container type label

    private JComboBox<Container> containerTypeBox; // container type combo box
    private DefaultComboBoxModel<Container> containerTypeModel; // container type combo box model

    private PlaceholderTextField productNameField; // product name text field
    private PlaceholderTextField amountField; // amount text field

    // hazardous checkbox
    private JCheckBox isHazardousBox;


    // dimension placeholder text fields
    private PlaceholderTextField lengthField, widthField, heightField, weightField;

    private JButton addButton; // run button

    private MenuCustomList currentItemsList;

    private JButton runButton;


    private static Container[] defaultContainers;


    private FormListener formListener;

    public ContentPanel() {

        formPanel = new JPanel();
        currentItemsPanel = new JPanel();

        // default containers
        defaultContainers = new Container[] {}; // storage unit


        containerTypeLabel = new JLabel("Container Type"); // container type label
        containerTypeLabel.setFont(Configuration.getTextFont());

        containerTypeBox = new JComboBox<>(); // container type combo box
        containerTypeModel = new DefaultComboBoxModel<>(); // container type combo box model
        containerTypeBox.setModel(containerTypeModel); // set container type combo box model


        productNameField = new PlaceholderTextField("NAME", 90,28,18); // product name text field


        amountField = new PlaceholderTextField("AMOUNT", 90,28,18); // amount text field

        isHazardousBox = new JCheckBox("Hazard");

        // dimension placeholder text fields
        lengthField = new PlaceholderTextField("Length", 90, 28, 18);
        widthField = new PlaceholderTextField("Width", 90, 28, 18);
        heightField = new PlaceholderTextField("Height", 90, 28, 18);
        weightField = new PlaceholderTextField("Weight", 90, 28, 18);

        addButton = new JButton("ADD"); // add button
        runButton = new JButton("RUN"); // run button

        currentItemsList = new MenuCustomList(18, new Dimension(150,200));


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                double amount = Double.parseDouble(amountField.getText());

                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(lengthField.getText());
                double height = Double.parseDouble(lengthField.getText());
                double weight = Double.parseDouble(lengthField.getText());

                boolean isHazardous = isHazardousBox.isSelected();

                formListener.addProductsEvent(new Product(productName, width, length, height, amount, weight, isHazardous));

            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container container = (Container) containerTypeBox.getSelectedItem();
                formListener.runContainerOptimizationEvent(container, currentItemsList.getList());
            }
        });


        layoutComponents();
        styling();
    }

    public void addProduct(Product product) {
        currentItemsList.addItem(product);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    private void layoutComponents() {

        // form panel

        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;

        gc.insets = new Insets(0,0,10,0);
        formPanel.add(containerTypeLabel, gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,20,0);
        formPanel.add(containerTypeBox, gc);

        gc.gridy++;
        gc.insets = new Insets(0,0,5,0);
        formPanel.add(productNameField, gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,20,0);
        formPanel.add(amountField, gc);

        gc.insets = new Insets(0,0,5,0);
        gc.gridy++;
        formPanel.add(lengthField, gc);
        gc.gridy++;
        formPanel.add(widthField, gc);
        gc.gridy++;
        formPanel.add(heightField, gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,20,0);
        formPanel.add(weightField, gc);


        gc.gridy++;
        formPanel.add(addButton, gc);


        // current items panel
        currentItemsPanel.setLayout(new GridBagLayout());
        currentItemsPanel.setBorder(BorderFactory.createEmptyBorder(0,40,0,20));
        gc.gridx =  0;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,10,0);
        currentItemsPanel.add(currentItemsList, gc);
        gc.gridy++;
        currentItemsPanel.add(runButton, gc);


        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,0,10,0),
                BorderFactory.createMatteBorder(0,0,0,2, Color.BLACK)));
        add(formPanel, BorderLayout.WEST);
        add(currentItemsPanel, BorderLayout.EAST);

    }

    public void setDefaultContainers(Container[] containers) {
        defaultContainers = containers;
        containerTypeBox.setModel(new DefaultComboBoxModel<>(containers));
    }

    private void styling() {

    }
}
