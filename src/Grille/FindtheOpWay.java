package Grille;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
	public class FindtheOpWay extends JFrame implements MouseListener,ActionListener
	{int lC=20;
	int c1=3120;
	int largeur,hauteur;
	JButton b=new JButton("TROUVER LE CHEMIN OPTIMAL");
	JButton ok=new JButton(" nouveau essai");
	int xd,yd,xa,ya,tx=40,ty=40;
	Window win;
	int niveau;
	MyJLabel  indication=new MyJLabel(Color.BLUE,"<HTML>maintenant vous pouvez choisir le obstacles<br><br> que vous voulez <br><br> et c'est à nous de retrouvez le chemin minimal</html>");
	ArrayList<P> cheminOptimal=new ArrayList<P>();
	ArrayList<P> all=new ArrayList<P>();
	ArrayList<Point> ouvert=new ArrayList<Point>();
	ArrayList<Point> ferme=new ArrayList<Point>();
	FindtheOpWay(int xd,int yd,int xa,int ya,int largeur,int hauteur,int niveau){
		super("LabyrinthusOpSolution");
		this.xd=xd;
		this.yd=yd;
		this.xa=xa;
		this.ya=ya;
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.niveau=niveau;
		indication.setSize(100,400);
	win=new Window(largeur,hauteur,xd,yd,xa,ya,lC,niveau);
	win.setLayout(new FlowLayout(FlowLayout.LEFT,860,60));	
	win.add(indication);
	win.setLayout(new FlowLayout(FlowLayout.LEFT,860,60));
	win.add(b);
	win.add(ok);
 	b.addActionListener(this);
 	ok.addActionListener(this);
	addMouseListener(this);
	getContentPane().add(win);
	int i=xd+2;
	if(niveau==1){
		while( i<=xa)
		{	
			all.add(new P(i,yd));
			i=i+1;
		}
		i=yd+1;
		while(i<ya)
		{	
			all.add(new P(xd+Math.abs(xd-xa)/2,i));
			i=i+1;
		}
		i=xd+Math.abs(xd-xa)/2+1;
		while( i<=ya)
		{	
			all.add(new P(i,ya-1));
			i=i+1;
		}
	}
	}
	public void paintComponent(Graphics g){
	}
	public void mouseClicked(MouseEvent e){
		int varx=9,vary=30;
		Graphics g=getGraphics();
		/*initialiser les obstacles*/
		
		
		int x=e.getX(),y=e.getY();
		if(x<=tx+lC*largeur&&y<=ty+lC*hauteur&&x>=tx&&y>=ty&&((x%lC==0)||(x-1)%lC==0||(x+1)%lC==0||(x+2)%lC==0||(x-2)%lC==0)&&((y%lC==0)||(y-1)%lC==0||(y+1)%lC==0||(y+2)%lC==0||(y-2)%lC==0))
		{
		if((x+1)%lC==0||(x+2)%lC==0)
			x=x+lC;
		if((y+1)%lC==0||(y+2)%lC==0)
			y=y+lC;
		all.add(new P(((x/lC*lC)-tx)/lC,((y/lC*lC)-tx)/lC));
		try {
			Image img = ImageIO.read(new File("mur3.jpg"));
			g.drawImage(img,x/lC*lC-lC/2 , y/lC*lC-lC/2,lC,lC, this);
			} catch (IOException s) {
			}
			}
		}
	public Point trouveMin(ArrayList<Point> c)   /* pour trouver le min d'une ArrayList  */
	{	int min,test;
		Point pointMin,pointTest;
		Iterator it=c.iterator();
		pointMin=((Point)it.next());
		min=pointMin.L;
		while(it.hasNext())
			{
			pointTest=((Point)it.next());
			test=pointTest.L;
			if(test<min){
						pointMin=pointTest;
						min=test;
						}
			}
		return pointMin;
	}
	public boolean verif(Point p )  /*pour verifier si notre point n'est pas ub obstacle et s'il appartient a l'univer du labyrinth*/
	{
		Iterator it=all.iterator();
		boolean b=true;
		P point;
		if(p.x<0||p.y<0||p.x>largeur||p.y>hauteur)
			b=false;
		while(it.hasNext())
		{
			point=(P)it.next();
			if((p.x==point.x&&p.y==point.y))
				b=false;
		}
		return b;
	}
	public void ajoutSiPossible(Point successeur)
	{	int index=-1;
		boolean test=true;
		Iterator it1=ferme.iterator();
		Iterator it=ouvert.iterator();
		Point p,p1;
		while(it.hasNext())
		{
			p=(Point)it.next();
			if(p.x==successeur.x&&p.y==successeur.y)
			{
				index=ouvert.indexOf(p);
				break;
			}
		}
		while(it1.hasNext())
		{
			p1=(Point)it1.next();
			if(p1.x==successeur.x&&p1.y==successeur.y)
			{
				test=false;
			}
		}
		if(index>0&&test)
			{
			if(ouvert.get(index).L>successeur.L) /*le cout du successeur est inferier a celui qui existe deja*/
				{
				ouvert.remove(ouvert.get(index));/*on le remplace par le successeur*/
				ouvert.add(successeur);
				}
			}
		if(index<0&&test) /*s'il n'existe pas donc on ajoute le */ 
			ouvert.add(successeur);
	}
	Point trouvePoint(int x,int y){
		Iterator it=ferme.iterator();
		Point p=null;
		while(it.hasNext())
		{
			p=(Point)it.next();
			if(p.x==x&&p.y==y)
				break;
		}
		return p;
	}
	public void dessineChemin(Graphics g){
		Iterator it=cheminOptimal.iterator();
		P w,z;
		w=(P)it.next();
		while(it.hasNext())
		{
			z=w;
			w=(P)it.next();
			g.setColor(Color.red);
			/*tx+z.x*lC<tx+w.x*lC&&g.drawLine(tx+z.x*lC,ty+z.y*lC,tx+w.x*lC,ty+w.y*lC);*/
			try {
				Image img = ImageIO.read(new File("HELLO.jpg"));
				if(ty+z.y*lC<ty+w.y*lC||tx+z.x*lC<tx+w.x*lC)
				g.drawImage(img,tx+z.x*lC,ty+z.y*lC,Math.abs(ty+w.y*lC-ty-z.y*lC)/4+Math.abs(tx+w.x*lC-tx-z.x*lC),Math.abs(tx+w.x*lC-tx-z.x*lC)/4+Math.abs(ty+w.y*lC-ty-z.y*lC), this);
				if(ty+z.y*lC>ty+w.y*lC||tx+z.x*lC>tx+w.x*lC)
					g.drawImage(img,tx+w.x*lC,ty+w.y*lC,Math.abs(ty+w.y*lC-ty-z.y*lC)/4+Math.abs(tx+w.x*lC-tx-z.x*lC),Math.abs(tx+w.x*lC-tx-z.x*lC)/4+Math.abs(ty+w.y*lC-ty-z.y*lC), this);
					
			} catch (IOException s) {
				}
		}
	}
	public void actionPerformed(ActionEvent e)
	{	Graphics g=getGraphics();
		int i=xd;
		Point p;
		Point droit,haut,gauche,bas;
		ouvert.add(new Point(xd,yd,xd,yd,0));
		p=trouveMin(ouvert);
		if(e.getSource()==ok){
			setVisible(false);
		 dispose();
			TrouveChemin chemin=new TrouveChemin("chemin optimal");
			chemin.setDefaultCloseOperation(TrouveChemin.EXIT_ON_CLOSE);
			chemin.setSize(1400,730);
			chemin.setVisible(true);
			chemin.setLocationRelativeTo(null) ;
			
		}
		if(e.getSource()==b){
			removeMouseListener(this);
			b.removeActionListener(this);
			while((p.x!=xa||p.y!=ya))
			{i++;
			if(ouvert.isEmpty())
				break;
				p=trouveMin(ouvert);
				
				droit=new Point(p.x+1,p.y,p.x,p.y,p.L+1);
				gauche=new Point(p.x-1,p.y,p.x,p.y,p.L+1);
				haut=new Point(p.x,p.y+1,p.x,p.y,p.L+1);
				bas=new Point(p.x,p.y-1,p.x,p.y,p.L+1);
				ferme.add(p);
				if(p.x==xa&&p.y==ya)
					break;
				ouvert.remove(ouvert.indexOf(p));
				/*pour chaque point******************************************************************************************************/
				if(verif(droit))
					ajoutSiPossible(droit);
				if(verif(gauche))
					ajoutSiPossible(gauche);
				if(verif(haut))
					ajoutSiPossible(haut);
				if(verif(bas))
					ajoutSiPossible(bas);	
			}
		}
		P dot=new P(xa,ya);
		cheminOptimal.add(dot);
		if(!ouvert.isEmpty())
		{
		while(dot.x!=xd||dot.y!=yd)
		{	
			dot=new P(trouvePoint(dot.x,dot.y).xan,trouvePoint(dot.x,dot.y).yan);
			cheminOptimal.add(dot);
		}
		dessineChemin(getGraphics());}		
		else
		{	g.setFont(new Font("serif",Font.BOLD,40));
			g.setColor(Color.red);
			g.drawString("chemin INTROUVABLE", 300, 300);
			}
	}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
}