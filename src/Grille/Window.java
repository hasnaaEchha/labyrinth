package Grille;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class Window extends JPanel {
int largeur,hauteur ,xd,yd,xa,ya,lC;
int varx=9,vary=30;
int tx =40-varx,ty=40-vary;
int niveau;
public Window(int largeur,int hauteur,int xd,int yd,int xa,int ya,int lC,int niveau){
	super();
	setSize(getWidth(),getHeight());
	this.largeur=largeur;
	this.xa=xa;
	this.ya=ya;
	this.xd=xd;
	this.yd=yd;
	this.lC=lC;
	this.hauteur=hauteur;
	this.niveau=niveau;
}
public void paintComponent(Graphics g){	
	int j=xd+2;
	super.paintComponent(g);

	try {
	Image img = ImageIO.read(new File("image3.jpg"));
	g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	} catch (IOException e) {
	e.printStackTrace();
	}

	g.setColor(new Color(58,4,60));
	for(int i=0;i<=largeur;i++){
		g.drawLine(tx+lC*i,ty, tx+lC*i,ty+hauteur*lC);
		if(i<=hauteur)
		g.drawLine(tx,ty+lC*i,tx+largeur*lC,ty+lC*i);
	}

	/*initialiser les obstacles*/
	if(niveau==1)
	{
		while( j<=xa)
		{	
			try {
				Image img = ImageIO.read(new File("mur3.jpg"));
				g.drawImage(img,(j*lC+tx)-lC/2 ,(yd*lC+ty)-lC/2,lC,lC, this);
				} catch (IOException s) {
				}
			j=j+1;
		}
		j=yd+1;
		while( j<ya)
		{	
			try {
				Image img = ImageIO.read(new File("mur3.jpg"));
				g.drawImage(img,((xd+Math.abs(xd-xa)/2)*lC+tx)-lC/2 ,(j*lC+ty)-lC/2,lC,lC, this);
				} catch (IOException s) {
				}
			j=j+1;
		}
		j=xd+Math.abs(xd-xa)/2+1;
		while( j<=xa)
		{	
			try {
				Image img = ImageIO.read(new File("mur3.jpg"));
				g.drawImage(img,(j*lC+tx)-lC/2 ,((ya-1)*lC+ty)-lC/2,lC,lC, this);
				} catch (IOException s) {
				}
	
			j=j+1;
		}
		}
	g.setColor(Color.red);
	g.fillOval(xd*lC-lC/2+tx,yd*lC-lC/2+ty,lC,lC);
	g.fillOval(xa*lC-lC/2+tx,ya*lC-lC/2+ty,lC,lC);
	}
}