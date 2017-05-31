package msolver;

/**
 * Created by thoma on 31-May-17.
 */
class Coordinate{
	private final int row, col;

	public Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}

	public int row(){
		return row;
	}

	public int col(){
		return col;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Coordinate){
			Coordinate coordinate = (Coordinate) obj;
			return row == coordinate.row && col == coordinate.col;
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public String toString() {
		return "(" +
				"col=" + col +
				" row=" + row +
				')';
	}
}
