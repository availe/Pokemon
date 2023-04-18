
public final class PPUp implements Item {
	
	// Item increases Pokemon's Power Points by 100 points
	 
	@Override
	public void use (Pokemon pokemon) {
		pokemon.setPowerPoints(pokemon.getPowerPoints() + 100);
		printItem(pokemon);
	}
	
	public void printItem (Pokemon pokemon) {
		System.out.println("\n" + pokemon.getName() + " used PPUp! Power Points raised to " + pokemon.getPowerPoints() + "!\n");
	}
}
