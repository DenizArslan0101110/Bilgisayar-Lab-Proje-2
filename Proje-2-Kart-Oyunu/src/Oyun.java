import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

public class Oyun {
    public static void main(String[] args) {

        ImageIcon fumo = new ImageIcon("Visual/djsjdjebfowodbwocbwoc.jpg"); // load image for label

        Border border = BorderFactory.createLineBorder(Color.blue, 3);  // create border

        JLabel label = new JLabel();                                // create label
        label.setText("Sanırsam bu DrawText fonksiyonu, galiba");   // add text to label
        label.setIcon(fumo);                                        // add image to label
        label.setHorizontalTextPosition(JLabel.CENTER);             // center the text to label
        label.setVerticalTextPosition(JLabel.TOP);                  // make text above label
        label.setForeground(new Color(223, 250, 20));               // makes label text colored
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));         // sets font
        label.setIconTextGap(100);                                  // makes text much above
        label.setBackground(Color.BLACK);                           // makes label bg colored
        label.setOpaque(true);                                      // shows label bg
        label.setBorder(border);                                    // draws border around label
        label.setVerticalAlignment(JLabel.CENTER);                  // center label in x inside window
        label.setHorizontalAlignment(JLabel.CENTER);                // center label in y inside window

        JFrame window = new JFrame();                               // create frame
        window.setTitle("Kart Oyunu: Savaş Araçları");              // set title
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // x button purpose
        window.setResizable(false);                                 // disable resize
        window.setSize(1366, 876);                                  // set size
        window.setVisible(true);                                    // make window visible
        window.add(label);                                          // draws our label

        ImageIcon logo = new ImageIcon("Visual/logo.png");           // create image for icon
        window.setIconImage(logo.getImage());                        // change icon
        window.getContentPane().setBackground(new Color(255, 0, 0)); // set bg color

    }
}
