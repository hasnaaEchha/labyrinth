package Grille;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class ImagePanel extends JPanel {
String nomImage="mur1.JPG";
ImagePanel(String nomImage){
	this.nomImage=nomImage;
}
public void paintComponent(Graphics g){
try {
Image img = ImageIO.read(new File(nomImage));
g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
} catch (IOException e) {
e.printStackTrace();
}
}
}