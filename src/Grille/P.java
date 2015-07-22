package Grille;

public class P implements Comparable<P> {
	int x;
	int y;

	P(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public String toString() {
		return ("(" + x + "," + y + ")");
	}

	public int compareTo(P ob2) {
		if (this.x == ob2.x && this.y == ob2.y)
			return 0;
		return 1;
	}

	boolean equals(P ob1) {
		if (this.x == ob1.x && this.y == ob1.y)
			return true;
		return false;

	}
}
