import java.util.ArrayList;

public class ElectricPokemon extends Pokemon {

	@Override
	public void speak() {
		System.out.println("\nPika pika!");
	}
	
	// default constructor for Pokemon subclass - only used in calculateDamage() in attackClass
	// Only the default constructor has the superEffective and notEffective lists, as its not used elsewhere
	public ElectricPokemon() {
		super(0, 0, "Null", "Electric", null);
		setSuperEffective(new String[]{"Flying", "Water"});
		setNotEffective(new String[]{"Electric", "Grass", "Ground"});
	}
	
	public ElectricPokemon(int hitPoints, int powerPoints, String name, String type, ArrayList<Attack> attacks) {
		super(hitPoints, powerPoints, name, "Electric", attacks);
	}
}
