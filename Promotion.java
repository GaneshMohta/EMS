package application;
import javax.swing.*;

public class Promotion extends JFrame {

    public Promotion() {
        setTitle("Event Promotion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton promoteButton = new JButton("Promote Event");
        panel.add(promoteButton);

        promoteButton.addActionListener(e -> {
            String[] options = {"Instagram", "Facebook", "Twitter"};
            String selectedOption = (String) JOptionPane.showInputDialog(null, "Choose a platform for promotion:",
                    "Promotion Platform", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Instagram":
                        openBrowser("https://www.instagram.com");
                        break;
                    case "Facebook":
                        openBrowser("https://www.facebook.com");
                        break;
                    case "Twitter":
                        openBrowser("https://www.twitter.com");
                        break;
                    default:
                        break;
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void openBrowser(String link) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(link));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Promotion();
        
    }
}