package application;

public class Position {
	public final int x;
	public final int y;
	
	// in gird
	public final int row;
	public final int col;
	
	public Position(int row, int col, int x, int y) {
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
	}
}
