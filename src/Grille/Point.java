package Grille;

public class Point implements Comparable<Point> {
	int x;
	int y;
	int L;
	int xan;
	int yan;
	Point(int x, int y,int xan, int yan,int L)
		{
		this.x=x;
		this.y=y;
		this.L=L;
     	this.xan=xan;
     	this.yan=yan;
     	}
	public String toString(){
		return("("+x+","+y+")");	
						}

	
	
	@Override	public int compareTo(Point ob){
		if(ob!=null&&(this.x==ob.x)&&(this.y==ob.y))
			return 0;
		else if(this.L<ob.L)
			return -1;
		else return 1;
									}

	public boolean equals(Object ob){
		Point o=(Point)ob;
		if(o!=null&&this.x==o.x&&this.y==o.y)
					return true;
		else return false;
		
		
	}
	

}
