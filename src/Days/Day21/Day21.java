package Days.Day21;

import java.util.ArrayList;
import java.util.Collections;

//Leaderboard at 94th position, Yay
public class Day21 {
	static final int BOSS_HEALTH = 104;
	static final int BOSS_DAMAGE = 8;
	static final int BOSS_ARMOR = 1;
	public static void main(String... args){
		ArrayList<Integer> totalCostWin = new ArrayList<Integer>();
		ArrayList<Integer> totalCostLose = new ArrayList<Integer>();
		for(Weapon weapon: Weapon.values()) 
			for(Armor armor: Armor.values()) 
				for(Ring ring1: Ring.values()) 
					for(Ring ring2: Ring.values())
						if(ring1.equals(Ring.NONE) || !ring1.equals(ring2)){
							int cost = weapon.cost + armor.cost + ring1.cost + ring2.cost;							
							if(fightBoss(weapon, armor, ring1, ring2)){
								totalCostWin.add(cost);
							}else{
								totalCostLose.add(cost);
							}
						}
		System.out.println("Part 1: " + Collections.min(totalCostWin));
		System.out.println("Part 2: " + Collections.max(totalCostLose));
	}
	private static boolean fightBoss(Weapon weapon, Armor armor, Ring ring1, Ring ring2){
		final int heroDamage = weapon.damage + ring1.damage + ring2.damage;
		final int damageToBoss = Math.max(1, heroDamage - BOSS_ARMOR);
		final int heroArmor = armor.armor + ring1.armor + ring2.armor;
		final int damageToHero = Math.max(1, BOSS_DAMAGE - heroArmor);
		
		int bossHP = BOSS_HEALTH;
		int heroHP = 100;

		while(true){
			if((bossHP -= damageToBoss) <= 0) return true;
			if((heroHP -= damageToHero) <= 0) return false;
		}
	}

	static enum Weapon{
		Dagger		(8, 	4),
		Shortsword	(10, 	5),
		Warhammer	(25, 	6),
		Longsword	(40, 	7),
		Greataxe	(74, 	8);

		public final int cost, damage;

		private Weapon(int cost, int damage){
			this.cost = cost;
			this.damage = damage;
		}
	}

	static enum Armor{
		Leather		(13, 	1),
		Chainmail	(31, 	2),
		Splintermail(53, 	3),
		Bandedmail	(75, 	4),
		Platemail	(102, 	5),
		NONE		(0, 	0);

		public final int cost, armor;

		private Armor(int cost, int armor){
			this.cost = cost;
			this.armor = armor;
		}
	}

	static enum Ring{
		Damage1		(25, 	1, 		0),
		Damage2		(50, 	2, 		0),
		Damage3		(100, 	3, 		0),
		Defense1	(20, 	0, 		1),
		Defense2	(40, 	0, 		2),
		Defense3	(80, 	0, 		3),
		NONE		(0, 	0, 		0);

		public final int cost, damage, armor;

		private Ring(int cost, int damage, int armor){
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}
	}
}
