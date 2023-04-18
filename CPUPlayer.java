import java.util.Random;

public final class CPUPlayer extends Player {

	@Override 
	public void run() {
		selectMove();
		this.setTurn(getTurn() + 1);
	}
	
    public CPUPlayer(Pokemon pokemon, Item item, Player opponent) {
        super(pokemon, item, opponent);
    }
    
    // selects whether CPU players attacks or uses item
    public void selectMove() { 
    	int num = 0;
    	// if Pokemon health's goes below 50% of its original value, use item
    	if (getHasUsedItem() == false && this.getPokemon().getHitPoints() < (this.getPokemon().getBaseHitPoints() / 2)) {
    		num = 2;
    		setHasUsedItem(true);
    	}
    	else {
    		num = 1;
    	}
				
		switch(num) {
	    case 1:
	    	selectAttack();
	        break;
	    case 2:
	    	this.getItem().use(this.getPokemon());
	       
	    	break;
	    default: // should never run under normal conditions
	        System.out.println("Invalid option.");
	        break;
	}
    }
    
    // selects which attack to use via a random integer, deploys the attack via attackLoop(
    public void selectAttack() {
        Random random = new Random();
		int num = random.nextInt(this.getPokemon().getAttacks().size());
		
		Attack attack = new Attack();
		attack.attackLoop(this, getOpponent(), num);
		System.out.println("\n_____");
    }
}
