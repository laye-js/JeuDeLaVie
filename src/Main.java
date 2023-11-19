import javax.swing.*;
import views.GameUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameUI();
        });
    }
}
