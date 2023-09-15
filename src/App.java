import gui.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        // program start
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

}
