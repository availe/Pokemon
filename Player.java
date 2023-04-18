
public abstract class Player {
	private Pokemon pokemon;
	private Item item;
	private int turn;
	private Player opponent;
	private boolean hasUsedItem;
	
	public abstract void run();
	
	public Player() {
		this(null, null, null);
		setTurn(0);
		setHasUsedItem(false);
	}
	
	public Player(Pokemon pokemon, Item item, Player opponent) {
		this.pokemon = pokemon;
		this.item = item;
		this.opponent = opponent;
		setTurn(0);
		setHasUsedItem(false);
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public boolean getHasUsedItem() {
		return hasUsedItem;
	}

	public void setHasUsedItem(boolean hasUsedItem) {
		this.hasUsedItem = hasUsedItem;
	}
}
