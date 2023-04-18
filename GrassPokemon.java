import java.util.ArrayList;

public class GrassPokemon extends Pokemon {

	@Override
	public void speak() { 
		System.out.println("\nSnivy!");
	}
	
	// default constructor for Pokemon subclass - only used in calculateDamage() in attackClass
	// Only the default constructor has the superEffective and notEffective lists, as its not used elsewhere
	public GrassPokemon() {
		super(0, 0, "Null", "Grass", null);
		setSuperEffective(new String[]{"Water", "Rock", "Ground"});
		setNotEffective(new String[]{"Bug", "Fire", "Flying", "Grass"});
	}
	
	public GrassPokemon(int hitPoints, int powerPoints, String name, String type, ArrayList<Attack> attacks) {
		super(hitPoints, powerPoints, name, "Grass", attacks);
	}
}
