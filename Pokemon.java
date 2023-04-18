import java.util.ArrayList;

public abstract class Pokemon {
	private int baseHitPoints, powerPoints, hitPoints;
	private String name, type;
	private ArrayList<Attack> attacks;
	private String[] superEffective = new String[0];
	private String[] notEffective = new String[0];
	
	public abstract void speak();
	
	public Pokemon () {
		this(0, 0, "Null", "Null", null);
		this.hitPoints = 0;
	}
	
	public Pokemon (int hitPoints, int powerPoints, String name, String type, ArrayList<Attack> attacks) {
		this.setBaseHitPoints(hitPoints);
		this.powerPoints = powerPoints;
		this.name = name;
		this.type = type;
		this.attacks = attacks;
		
		this.hitPoints = hitPoints;
	}
	
	/* note that hitPoints is how much Pokemon currently has, whereas baseHitPoints is hitPoints Pokemon holds at start of game
	 * the value of baseHitPoints is never updated throughout the game, whereas hitPoints is continually updated
	 */
	
	public int getHitPoints() {
		return hitPoints;
	}
	
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	public int getPowerPoints() {
		return powerPoints;
	}
	
	public void setPowerPoints(int powerPoints) {
		this.powerPoints = powerPoints;
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
	
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}
	
	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}
	
	public String[] getSuperEffective() {
		return superEffective;
	}
	
	public void setSuperEffective(String[] superEffective) {
		this.superEffective = superEffective;
	}
	
	public String[] getNotEffective() {
		return notEffective;
	}
	
	public void setNotEffective(String[] notEffective) {
		this.notEffective = notEffective;
	}
	
	public int getBaseHitPoints() {
		return baseHitPoints;
	}
	
	public void setBaseHitPoints(int baseHitPoints) {
		this.baseHitPoints = baseHitPoints;
	}
}
