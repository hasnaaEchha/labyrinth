package Grille;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;
public class MyJLabel extends JLabel {
	Color color;
	String texte;
	Font police = new Font("Tahoma", Font.BOLD, 16);
	MyJLabel(Color color,String texte){
		this.color=color;
		this.texte=texte;
		setFont(police);
		setForeground(color);
		setText(texte);
		
		
	}
	
	
	
}
