import java.util.ArrayList;

public class RockPokemon extends Pokemon {

	@Override
	public void speak() {
		System.out.println("\nThud!");
	}
	
	// default constructor for Pokemon subclass - only used in calculateDamage() in attackClass
	// Only the default constructor has the superEffective and notEffective lists, as its not used elsewhere
	public RockPokemon() {
		super(0, 0, "Null", "Rock", null);
		setSuperEffective(new String[]{"Bug", "Fire", "Flying", "Ice"});
		setNotEffective(new String[]{"Fighting", "Rock"});
	}
	
	public RockPokemon(int hitPoints, int powerPoints, String name, String type, ArrayList<Attack> attacks) {
		super(hitPoints, powerPoints, name, "Rock", attacks);
	}
}
