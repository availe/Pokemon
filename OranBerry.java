
public final class OranBerry implements Item {

	// Item increases Pokemon hitPoints by 50 points, or back to its original health, whichever is less
	@Override
	public void use(Pokemon pokemon) {
		if(pokemon.getHitPoints() + 50 <= pokemon.getBaseHitPoints()) {
			pokemon.setHitPoints(pokemon.getHitPoints() + 50);
		}
		else {
			pokemon.setHitPoints(pokemon.getBaseHitPoints());
		}
		printItem(pokemon);
	}
	
	public void printItem (Pokemon pokemon) {
		System.out.println("\n" + pokemon.getName() + " ate berry! Health restored to " + pokemon.getHitPoints() + "!\n");
	}
}
