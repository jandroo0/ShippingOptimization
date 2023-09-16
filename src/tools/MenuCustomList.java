package tools;

import config.Configuration;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MenuCustomList extends JPanel {

    private JList<Product> list;
    private DefaultListModel<Product> model;
    private JScrollPane scrollPane;
    private boolean displayItemCount, displayInSpecialFormat, alignCenter;
    private Map<Product, Integer> itemCounter;

    public MenuCustomList(int fontSize, Dimension preferredSize) {
        setLayout(new BorderLayout());
        setPreferredSize(preferredSize);

        list = new JList<>();
        scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(preferredSize);

        model = new DefaultListModel<>();
        list.setModel(model);
        list.setCellRenderer(new CustomListCellRenderer());

        list.setFont(Configuration
                .getTextFont(fontSize));
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        list.setBackground(Color.WHITE);
        list.setForeground(Configuration.getTextColor());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        add(scrollPane, BorderLayout.CENTER);

        this.displayItemCount = false; // Set the default value
        this.itemCounter = new HashMap<>();
        this.displayInSpecialFormat = false; // Set the default value
    }

    public void setDisplayItemCount(boolean displayItemCount) {
        this.displayItemCount = displayItemCount;
    }

    public void setDisplayInSpecialFormat(boolean displayInSpecialFormat) {
        this.displayInSpecialFormat = displayInSpecialFormat;
    }

    public void setAlignCenter(boolean alignCenter) {
        this.alignCenter = alignCenter;
    }

    public void addItem(Product item) {
        if (itemCounter.containsKey(item)) {
            int count = itemCounter.get(item) + 1;
            itemCounter.put(item, count);
        } else {
            itemCounter.put(item, 1);
            model.addElement(item);
        }
        list.repaint(); // Force the list to repaint

    }

    public Product getSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<Product> model = (DefaultListModel<Product>) list.getModel();
            return model.getElementAt(selectedIndex);
        }
        return null;
    }

    public LinkedList<Product> getList() {
        DefaultListModel<Product> model = (DefaultListModel<Product>) list.getModel();
        LinkedList<Product> items = new LinkedList<>();
        for (int i = 0; i < model.getSize(); i++) {
            items.add(model.getElementAt(i));
        }
        return items;
    }

    public void setList(LinkedList<Product> items) {
        DefaultListModel<Product> model = (DefaultListModel<Product>) list.getModel();
        model.removeAllElements();
        for (Product item : items) {
            model.addElement(item);
        }
    }

    public void removeSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<Product> model = (DefaultListModel<Product>) list.getModel();
            model.remove(selectedIndex);
        }
    }

    public void clearList() {
        DefaultListModel<Product> model = (DefaultListModel<Product>) list.getModel();
        model.removeAllElements();
        itemCounter.clear();
    }

    private class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Product) {
                Product Product = (Product) value;
                setText(Product.getName() + " x" + Product.getCount());
                setHorizontalAlignment(SwingConstants.CENTER);

            }

            return c;
        }
    }
}
