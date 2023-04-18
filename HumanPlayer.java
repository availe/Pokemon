import java.util.InputMismatchException;
import java.util.Scanner;

public final class HumanPlayer extends Player {
	
    private Scanner scanner;

	@Override
	public void run() { 
		scanner = new Scanner(System.in);
		selectMove();
	}
	
    public HumanPlayer(Pokemon pokemon, Item item, Player opponent) {
        super(pokemon, item, opponent);
    }
    
    // selects whether player should attack, use item, or run
    public void selectMove() {
		int num = 0;
		
		do {
			System.out.println("\nTurn " + getOpponent().getTurn() + ": Pick whether you wish to\n1. Attack\n2. Use item\n3. Run\n");
			try {
				num = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid option.");
				scanner.next();
			}
		} while (num < 1 || num > 3);
		
		switch(num) {
	    case 1:
	    	selectAttack();
	        break;
	    case 2:
	        if(!this.getHasUsedItem()) {
	        	this.getItem().use(getPokemon());
	        	this.setHasUsedItem(false);
	        }
	        else {
	        	System.out.println("You already have used your item!");
	        }
	        break;
	    case 3:
	        System.out.println("\nYou can't run from a Trainer battle! If you want to get away, you'll have to beat the opposing Pokemon!"); 
	        break;
	    default: // should never run under normal conditions
	        System.out.println("Invalid option.");
	        break;
	}
    }
    
    // selects which attack player should use and deploys it in attackLoop()
    public void selectAttack() {
		int num = 0;

    	do {
    		System.out.println("\nPick an attack:");
	    	for(int i = 0; i < this.getPokemon().getAttacks().size(); i++) {
	    	    Attack currentAttack = this.getPokemon().getAttacks().get(i);
	    	    System.out.println((i + 1) + ". " + currentAttack.getName() + " | Type: " + currentAttack.getType() 
	    	    + " | Cost: " + currentAttack.getCost() + " | Base Damage: " + currentAttack.getBaseDamage());
	    	}
	    	System.out.println();
			try {
				num = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid option.");
				scanner.next();
			}
		} while (num < 1 || num > this.getPokemon().getAttacks().size());
		
		Attack attack = new Attack();
		attack.attackLoop(this, getOpponent(), num-1);
    }
    
    // closes the scanner - method called in runner class
    public void closeScanner() {
    	scanner.close();
    }
}
