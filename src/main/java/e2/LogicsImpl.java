package e2;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;

	public LogicsImpl(int size, Pair<Integer,Integer> knight, Pair<Integer,Integer> pawn) {
		checkValidSize(size);
		this.size = size;
		checkValidPosition(pawn);
		this.pawn = pawn;
		checkValidPosition(knight);
		this.knight = knight;
	}

	private void checkValidPosition(Pair<Integer,Integer> position) {
		if (position.getX() < 0 || position.getY() < 0 || position.getX() >= this.size || position.getY() >= this.size)
			throw new IndexOutOfBoundsException();
	}

    public LogicsImpl(int size){
		checkValidSize(size);
		this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();
    }

	private void checkValidSize(int size) {
		if (size <= 1)
			throw new IllegalArgumentException();
	}

	private Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knight.getX();
		int y = col-this.knight.getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}
