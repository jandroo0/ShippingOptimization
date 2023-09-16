package gui;

import listener.ResultsPanelListener;
import tools.MenuCustomList;
import tools.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsPanel extends JPanel {

    private JLabel titleLabel;

    private TextPanel resultsPane;

    private MenuCustomList procedureList;


    private JButton clearButton;

    private ResultsPanelListener resultsPanelListener;



    public ResultsPanel() {
        titleLabel = new JLabel("RESULTS");

        resultsPane = new TextPanel();

        clearButton = new JButton("CLEAR");


        procedureList = new MenuCustomList(16, new Dimension(300, 300));


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultsPane.displayResults("");

                resultsPanelListener.clearResults();
            }
        });


        layoutComponents();
        styling();
    }

    public void setResultsPanelListener(ResultsPanelListener listener) {
        this.resultsPanelListener = listener;
    }

    public void setResultsAreaText(String results) {
        resultsPane.displayResults(results);
    }


    private void layoutComponents() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0,0,0,50));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,10,0);
        add(titleLabel, gc);

        gc.gridy++;
        add(resultsPane ,gc);

        gc.gridy++;
        add(clearButton, gc);


    }

    private void styling() {

    }
}
