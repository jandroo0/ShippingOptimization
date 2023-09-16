package listener;

import model.Container;
import model.Product;

import java.util.EventListener;
import java.util.LinkedList;

public interface FormListener extends EventListener {

    void addProductsEvent(Product product);

    void runContainerOptimizationEvent(Container container, LinkedList<Product> products);
}
