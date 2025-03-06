package e2;

import java.util.*;

public class LogicsImpl implements Logics {

	private ChessPiece knight;
	private ChessPiece pawn;
	private final Random random = new Random();
	private final int size;

	public LogicsImpl(int size, Pair<Integer,Integer> knightPosition, Pair<Integer,Integer> pawnPosition) {
		this(size);
		checkValidPosition(pawnPosition);
		checkValidPosition(knightPosition);
		this.pawn.setNewPosition(pawnPosition);
		this.knight.setNewPosition(knightPosition);
	}

	public LogicsImpl(int size){
		checkValidSize(size);
		this.size = size;
		createPawnAndKnight();
		this.pawn.setNewPosition(this.randomEmptyPosition());
		this.knight.setNewPosition(this.randomEmptyPosition());
	}
    
	@Override
	public boolean hit(int row, int col) {
		checkValidPosition(new Pair<>(row, col));
		if (this.knight.getPossibleMovements(this.size).contains(new Pair<>(row, col)))
			this.knight.setNewPosition(new Pair<>(row, col));
		return this.knight.getPosition().equals(this.pawn.getPosition());
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.getPosition().equals(new Pair<>(row,col));
	}

	private void createPawnAndKnight() {
		ChessPieceFactory factory = new ChessPieceFactory();
		this.pawn = factory.createPawn();
		this.knight = factory.createKnight();
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

	private void checkValidPosition(Pair<Integer,Integer> position) {
		if (position.getX() < 0 || position.getY() < 0 || position.getX() >= this.size || position.getY() >= this.size)
			throw new IndexOutOfBoundsException();
	}
}
