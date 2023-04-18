import java.util.ArrayList;

public class WaterPokemon extends Pokemon {

	@Override
	public void speak() { 
		System.out.println("\nSplash!");
	}

	// default constructor for Pokemon subclass - only used in calculateDamage() in attackClass
	// Only the default constructor has the superEffective and notEffective lists, as its not used elsewhere
	public WaterPokemon() {
		super(0, 0, "Null", "Water", null);
		setSuperEffective(new String[]{"Fire", "Ground", "Rock"});
		setNotEffective(new String[]{"Grass", "Ice"});
	}	
	
	public WaterPokemon(int hitPoints, int powerPoints, String name, String type, ArrayList<Attack> attacks) {
		super(hitPoints, powerPoints, name, "Water", attacks);
	}	
}
