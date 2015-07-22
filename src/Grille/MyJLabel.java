package Grille;

import java.awt.Font;
import java.awt.*;
import javax.swing.*;

public class MyJLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color color;
	String texte;
	Font police = new Font("Tahoma", Font.BOLD, 16);

	MyJLabel(Color color, String texte) {
		this.color = color;
		this.texte = texte;
		setFont(police);
		setForeground(color);
		setText(texte);

	}

}
