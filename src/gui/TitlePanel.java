package gui;

import config.Configuration;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {

    private JLabel titleLabel; // title label

    public TitlePanel() {
        titleLabel = new JLabel("EZSHIPPING"); // create title label
        titleLabel.setFont(Configuration.getFONT_TITLE()); // set font
        titleLabel.setForeground(Color.BLACK); // set color
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // set alignment

        setBorder(BorderFactory.createEmptyBorder(10,0,0,0)); // set border

        setLayout(new BorderLayout()); // set layout
        add(titleLabel, BorderLayout.CENTER); // add title label

    }
}
