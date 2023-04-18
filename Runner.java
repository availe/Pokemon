import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		
		// create list of attacks for Snivy
		ArrayList<Attack> snivyAttacks = new ArrayList<>();
		snivyAttacks.add(new Attack("Vine Whip", "Grass", 50, 45));
		snivyAttacks.add(new Attack("Leaf Blade", "Grass", 10, 65));
		snivyAttacks.add(new Attack("Thunder Fang", "Electric", 15, 90));
		snivyAttacks.add(new Attack("Tackle", "Normal", 15, 20));
		
		// create list of attacks for Oshawott
		ArrayList<Attack> OshawottAttacks = new ArrayList<>();
		OshawottAttacks.add(new Attack("Splash", "Water", 10, 45));
		OshawottAttacks.add(new Attack("Aqua Jet", "Water", 10, 45));
		OshawottAttacks.add(new Attack("Rock Throw", "Rock", 10, 45));
		OshawottAttacks.add(new Attack("Snorlax", "Normal", 10, 45));

		// initialize Snivy and Oshawott (Pokemons)
		GrassPokemon Snivy = new GrassPokemon(200, 100, "Snivy", "Grass", snivyAttacks);
		WaterPokemon Oshawott = new WaterPokemon(200, 100, "Oshawott", "Water", OshawottAttacks);
		
		// initialize the Items used in game
		PPUp PPUp = new PPUp();
		OranBerry OranBerry = new OranBerry();
		
		// make parameterized constructor for Snivy and Oshawott (Pokemons)
		HumanPlayer HumanPlayer = new HumanPlayer(Snivy, PPUp, null);
        CPUPlayer CPUPlayer = new CPUPlayer(Oshawott, OranBerry, HumanPlayer);
        HumanPlayer.setOpponent(CPUPlayer);
        
        // continue game while both Pokemon have more than 0 hitPoints
        // if one Pokemon has 0 or less hitPoints, game ends
        while (HumanPlayer.getPokemon().getHitPoints() > 0 && CPUPlayer.getPokemon().getHitPoints() > 0) {
        	HumanPlayer.run();
        	CPUPlayer.run();
        }
        
        // close scanner opened inside HumanPlayer class
        HumanPlayer.closeScanner();
	}

}