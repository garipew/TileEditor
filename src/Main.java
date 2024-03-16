import javax.swing.*;

public class Main {

    static JFrame window;

    public static void main(String[] args) {

     window = new JFrame("Tile Editor");
     EditorPanel panel = new EditorPanel();

     window.add(panel);
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setResizable(false);
     window.pack();
     
     window.setVisible(true);
     panel.startPanelThread();


    }
}