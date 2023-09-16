package gui;

import tools.MenuCustomList;

import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {

    private JLabel titleLabel;

    private MenuCustomList resultsList;

    private JButton clearButton;


    public ResultsPanel() {
        titleLabel = new JLabel("RESULTS");

        resultsList = new MenuCustomList(16, new Dimension(250, 300));

        clearButton = new JButton("CLEAR");



        layoutComponents();
        styling();
    }


    private void layoutComponents() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0,20,0,40));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,10,0);
        add(resultsList, gc);

        gc.gridy++;
        add(clearButton ,gc);


    }

    private void styling() {

    }
}
