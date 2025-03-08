import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            SwingUtilities.invokeLater(() -> {
                UI app = new UI();
                app.setVisible(true);
            });
            //MenusGestionBaseDatos.menuPrincipal();
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
    }
}