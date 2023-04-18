import java.lang.reflect.InvocationTargetException;

public class Attack {
	private String name, type;
	private int cost, baseDamage, damage, damageFlag;
	
	/* CalculateDamage() notes:
	 * we cannot just compare the type of attacking Pokemon vs defending Pokemon, as Pokemon can learn attack types that are not their own type
	 * As such, instead of checking the type of the attacking Pokemon, the plan is to:
	 * 1) check the type of the attack
	 * 2) see if any Pokemon subclass with the same type of the attack exists
	 * 3) get lists of SuperEffective and NotEffective matchups from Pokemon subclass
	 * 4) use said information to calculate damage, which is baseDamage * a multiplier
	 * This approach makes attacking completely modular, as a developer can add new types of Pokemon (via subclasses) without changing any other code at all
	 */
	public void calculateDamage (Pokemon other, Attack attack) {
		// stores the attack-type and then concatenates "Pokemon" into String attackType
		// this is because Pokemon classes are named in such a format, such as "GrassPokemon"
		String attackType = attack.getType() + "Pokemon";
		
		try {
			// String attackType is translated into a parameterized subclass of Pokemon via Class.forName
			// The class which is created is called attackClass
			Class<? extends Pokemon> attackClass = Class.forName(attackType).asSubclass(Pokemon.class);
			
			/* tempInfo is an initialization of attackClass - this allows us to initialize Pokemon subclasses during runtime (such as GrassPokemon)
			 * we are not creating new Pokemon, rather we're trying to access the SuperEffective and NotEffective lists that are stored within the Pokemon subclasses
			 * note that this creates a new instance of a default constructor, a null Pokemon with no values that's automatically discarded when calculateDamage() ends
			 */
			Pokemon tempInfo = attackClass.getDeclaredConstructor().newInstance();
			
			/* other.getType refers to the type of the opponent Pokemon (such as water type)
			 * tempInfo refers to the null Pokemon we just created - we later use the getter functions to see the SuperEffective and NotEffective lists
			 * the findString() part checks if other.getType is found in the list of tempInfo + the list it's searching through
			 * we also set DamageFlags which are used later on to determine what text output to print out in printAttack()
			 */
			if (findString(other.getType(), tempInfo.getSuperEffective())) {
				attack.setDamage(attack.getBaseDamage() * 2);
				setDamageFlag(0);
			}
			else if (findString(other.getType(), tempInfo.getNotEffective())) {
				attack.setDamage(attack.getBaseDamage()/2);
				setDamageFlag(1);
			}
			else {
				attack.setDamage(attack.getBaseDamage());
				setDamageFlag(2);
			}
		
		/* catch() occurs if findString() cannot find any info on whether the attack is effective or not against the opponent Pokemon
		 * aka this occurs if the Pokemon/tempInfo subclass which houses the SuperEffective and NotEffective matchups doesn't exist
		 * since there's no file for such a type, we will try to find if such a Pokemon type exists in the games, otherwise declare the type invalid
		 */
		} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// this is a list of all Pokemon present in the games, stored as a string array str
			String[] str = {"NormalPokemon", "FirePokemon", "WaterPokemon", "GrassPokemon", "ElectricPokemon", "IcePokemon", "FightingPokemon", "PoisonPokemon", "GroundPokemon", "FlyingPokemon", "PsychicPokemon", "BugPokemon", "RockPokemon", "GhostPokemon", "DarkPokemon", "DragonPokemon", "SteelPokemon", "FairyPokemon"};
			
			// we try to find if the String attackType exists in the game
			// aka if there's a poison-type attack, we find if there's a poison-type in the games 
			if (findString(attackType, str)) {
				attack.setDamage(attack.getBaseDamage());
				this.setDamageFlag(2);	
			}
			// since we haven't found any program files of the Pokemon type, and the Pokemon type doesn't exist in the games, we declare the type invalid and end the game
			else {
				System.out.println("The attack type " + attackType + " does not exist in the Pokemon base game or in the program files"
						+ "\nThis error is caused when initializing the Pokemon attack list in Runner.java"
						+ "\nGame Over");
				System.exit(1);
			}
			
		}
	}
	
	public void printAttack (Pokemon pokemon, Pokemon other, Attack attack) {
		String str;
		
		switch (damageFlag) {
			case 0: str = "It was Super Effective!"; 
				break;
			case 1: str = "It was Not Effective!"; 
				break;
			case 2: str = "It had Average Effectivness!"; 
				break;
			default: str = "Attack was invalid"; 
				break;
		}
		
		System.out.println("\n" + pokemon.getName() + " used " + attack.getName() + "! " + str);
			
		if (other.getHitPoints() > 0) {
			System.out.println(other.getName() + " took " + attack.getDamage() + " damage! It now has " + other.getHitPoints() + " hitPoints left!");
		}
		else {
			other.speak();
			System.out.println(other.getName() + " took " + attack.getDamage() + " damage! It has fainted!");
			System.exit(0);
		}
	}
	
	public void inflictAttack (Pokemon other, Attack attack) {
		other.setHitPoints(other.getHitPoints() - attack.getDamage());
	}

	public void subtractCost (Pokemon pokemon, Attack attack) {
		pokemon.setPowerPoints(pokemon.getPowerPoints( ) - attack.getCost());
	}
	
	public void attackLoop(Player player, Player opponent, int indexOffset) {
		// checks if Pokemon has 0 Power Points (which would make it unable to attack)
		// if Pokemon hasn't used item yet, use it hope that it will boosts Power Points
		if (player.getPokemon().getPowerPoints() == 0 && player.getHasUsedItem() == false) {
			player.getItem().use(player.getPokemon());
			
			// If Power Points still 0 after using item, end the game 
			if(player.getPokemon().getPowerPoints() == 0) {
				System.out.println("You have ran out of Power Points\n Game Over");
				System.exit(0);
			}
			System.out.println(player.getPokemon().getName() + " has automatically used " + player.getItem().getClass().getSimpleName() + " to restore Power Points!");

		}
		// if Pokemon has more than 0 Power Points but not enough to attack, then it cannot attack 
		else if (player.getPokemon().getPowerPoints( ) - player.getPokemon().getAttacks().get(indexOffset).getCost() < 0) {
			System.out.println("Move costs more than PP you have available");
		}
		else {
			// If Pokemon has enough Power Points to conduct attack, carry on the following methods:
			subtractCost(player.getPokemon(), player.getPokemon().getAttacks().get(indexOffset));
			calculateDamage(opponent.getPokemon(), player.getPokemon().getAttacks().get(indexOffset));
			inflictAttack(opponent.getPokemon(), player.getPokemon().getAttacks().get(indexOffset));
			printAttack(player.getPokemon(), opponent.getPokemon(), player.getPokemon().getAttacks().get(indexOffset));
		}
	}
	
	public boolean findString(String string, String[] stringArray) {
		for (int i = 0; i < stringArray.length; i++) {
			if (string.equals(stringArray[i])) {
				return true;
			}
		}
		return false;
	}
	
	public Attack() {
		this("Null", "Null", 0, 0);
		damage = 0;
		damageFlag = -1;
	}
	
	public Attack (String name, String type, int cost, int baseDamage) {
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.baseDamage = baseDamage;
		this.damage = 0;
		damageFlag = -1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamageFlag() {
		return damageFlag;
	}

	public void setDamageFlag(int damageFlag) {
		this.damageFlag = damageFlag;
	}
}
