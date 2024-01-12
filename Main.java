package easekolar;

import javax.swing.SwingUtilities;

import easekolar.application.Easekolar;
 
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Easekolar());
    }

}