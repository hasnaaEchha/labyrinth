package Grille;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.CardLayout;

public class TrouveChemin extends JFrame implements ActionListener {
	Font police = new Font("Tahoma", Font.BOLD, 16);
	MyJLabel presentation = new MyJLabel(
			Color.WHITE,
			"<html> <blockquote><blockquote><blockquote>Bienvenue dans LabyrinthusOpSolution</blockquote></blockquote></blockquote><br>"
					+ "avec notre application tous ce que vous devez faire c'est saisir les obstacles</blockquote><br><br>"
					+ "et LabyrinthusOpSolution s'occupera de vous trouver un chemin optimal<br><br><br><br><br><br><br> "
					+ "<blockquote><blockquote><blockquote>si vous voulez continuer veuillez cliquer sur suivant</blockquote></blockquote></blockquote></html>");
	MyJLabel nD = new MyJLabel(Color.WHITE, "Choisir le niveau de difficulté");
	MyJLabel indication = new MyJLabel(Color.red, "");
	ImagePanel pan = new ImagePanel("labyrinth3.JPG");
	Button b1 = new Button("OK");
	FindtheOpWay co;
	CardLayout cl = new CardLayout();
	JPanel content = new JPanel();
	String[] listContent = { "card1", "card2", "card3" };
	ImagePanel card1 = new ImagePanel("labyrinth8.JPG");
	ImagePanel card2 = new ImagePanel("labyrinth8.JPG");
	ImagePanel card3 = new ImagePanel("labyrinth8.JPG");
	JPanel boutonPan = new JPanel();
	JButton prece = new JButton("precedent");
	JButton suiv = new JButton("suivant");
	MyJLabel l1x = new MyJLabel(
			Color.white,
			"<html>l'abscisse du point de depart<br><br>(il ne faut pas que ca depasse 40)</html>");
	MyJLabel l1y = new MyJLabel(
			Color.white,
			"<html>l'ordonné du point de depart<br><br>(il ne faut pas que ca depasse 30)</html>");
	MyJLabel l2x = new MyJLabel(
			Color.white,
			"<html>l'abscisse du point d'arrivé<br><br>(il ne faut pas que ca depasse 40)</html>");
	MyJLabel l2y = new MyJLabel(
			Color.white,
			"<html>l'ordonné du point d'arrivé<br><br>(il ne faut pas que ca depasse 30)</html>");
	Choice li = new Choice();
	JComboBox niveauDifficulte = new JComboBox();
	TextField t1x = new TextField(3);
	TextField t1y = new TextField(3);
	TextField t2x = new TextField(3);
	TextField t2y = new TextField(3);
	FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 160, 160);
	public int largeur = 40;
	int hauteur = 30;
	int xd = -1;
	int yd = -1;
	int xa = -1;
	int ya = -1;
	int a = 0;

	TrouveChemin(String titre) {
		super(titre);
		niveauDifficulte.addItem("niveau0");
		niveauDifficulte.addItem("niveau1");
		indication.setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));
		suiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (a != 2) {
					a++;
					cl.next(content);
				}
			}
		});
		prece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (a != 0) {
					a = a - 1;
					cl.show(content, listContent[a]);
				}
			}
		});
		presentation
				.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));
		card1.add(presentation);
		card2.setLayout(fl);
		card2.add(l1x);
		card2.add(t1x);
		card2.add(l1y);
		card2.add(t1y);
		card2.add(l2x);
		card2.add(t2x);
		card2.add(l2y);
		card2.add(t2y);
		card3.setLayout(fl);
		card3.add(nD);
		card3.add(niveauDifficulte);
		boutonPan.add(prece);
		boutonPan.add(suiv);
		content.setLayout(cl);
		content.add(card1, listContent[0]);
		content.add(card2, listContent[1]);
		content.add(card3, listContent[2]);
		this.getContentPane().add(boutonPan, BorderLayout.NORTH);
		this.getContentPane().add(content, BorderLayout.CENTER);
		suiv.addActionListener(this);
	}

	public void paintComponent(Graphics g) {

		g.setColor(Color.WHITE);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString("ENTER DES COORDONNEES COMPRIS ENTRE 0 ET " + largeur,
				100, 180);
	}

	public void actionPerformed(ActionEvent e) {
		Graphics g = getGraphics();
		g.setColor(Color.white);
		if (e.getSource() == suiv && a == 2) {
			try {
				xd = Integer.parseInt(t1x.getText());
				yd = Integer.parseInt(t1y.getText());
				xa = Integer.parseInt(t2x.getText());
				ya = Integer.parseInt(t2y.getText());
				co = new FindtheOpWay(xd, yd, xa, ya, largeur, hauteur,
						niveauDifficulte.getSelectedIndex());
				if (xa < 0 || xa > largeur || ya < 0 || ya > hauteur || xd < 0
						|| xd > largeur || yd < 0 || yd > hauteur) {
					g.setColor(Color.red);
					g.setFont(new Font("Serif", Font.BOLD, 20));
					g.drawString("ENTREZ DES CORDONNEES VALIDES", 100, 500);
				}

				else {

					setVisible(false);
					co.setSize(1500, 730);/* (1500,700) */
					co.setVisible(true);
				}
			} catch (NumberFormatException s) {

				g.setColor(Color.WHITE);
				g.setFont(new Font("Serif", Font.BOLD, 20));
				g.drawString(
						"veuillez d'abord entrez les coordonnees correcte",
						100, 600);
			}
		}

	}

	public static void main(String[] arg) {
		TrouveChemin chemin = new TrouveChemin("LabyrinthusOpSolution");
		chemin.setDefaultCloseOperation(TrouveChemin.EXIT_ON_CLOSE);
		chemin.setSize(1400, 730);
		chemin.setVisible(true);
		chemin.setLocationRelativeTo(null);
	}

}
