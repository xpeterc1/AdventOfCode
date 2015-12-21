package Days.Day21;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Leaderboard at 94th position, Yay
public class Day21 {
	public static void main(String... args) throws IOException{
		ArrayList<Integer> amountSpend = new ArrayList<Integer>();
		ArrayList<Integer> amountSpendLOSSING = new ArrayList<Integer>();

		for(Weapon weapon: Weapon.values()){
			for(Armor armor: Armor.values()){
				for(Ring ring1: Ring.values()){
					for(Ring ring2: Ring.values()){
						if(ring1 != ring2 || ring1.ID == 6){
							int cost = weapon.cost + armor.cost + ring1.cost + ring2.cost;							if(fightBoss(weapon,armor,ring1,ring2)){
								amountSpend.add(cost);
							}else{
								amountSpendLOSSING.add(cost);
							}
						}
					}
				}
			}
		}
		System.out.println("Part 1: " + Collections.min(amountSpend));
		System.out.println("Part 2: " + Collections.max(amountSpendLOSSING));



	}
	public static boolean fightBoss(Weapon weapon, Armor armor, Ring ring1, Ring ring2){
		int bossHP = 104;
		int bossDMG = 8;
		int bossARMOR = 1;
		int heroHP = 100;
		int heroDMG = weapon.damage + ring1.damage + ring2.damage;
		int heroARMOR = armor.armor + ring1.armor + ring2.armor;
		while(true){
			int damageToBoss = heroDMG - bossARMOR;
			bossHP -= Math.max(1, damageToBoss);
			if(bossHP <= 0){
				return true;
			}
			int damageToHero = bossDMG - heroARMOR;
			heroHP -= Math.max(1, damageToHero);
			if(heroHP <= 0){
				return false;
			}
		}
	}

	static enum Weapon{
		Dagger(0, 8, 4, 0),
		Shortsword(1, 10, 5, 0),
		Warhammer(2, 25, 6, 0),
		Longsword(3, 40, 7, 0),
		Greataxe(4, 74, 8, 0);

		private final int ID;
		private final int cost;
		private final int damage;
		private final int armor;

		private Weapon(int ID, int cost, int damage, int armor){
			this.ID = ID;
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}	
	}

	static enum Armor{
		Leather(0, 13, 0, 1),
		Chainmail(1, 31, 0, 2),
		Splintermail(2, 53, 0, 3),
		Bandedmail(3, 75, 0, 4),
		Platemail(4, 102, 0, 5),
		NONE(5, 0, 0, 0);

		private final int ID;
		private final int cost;
		private final int damage;
		private final int armor;

		private Armor(int ID, int cost, int damage, int armor){
			this.ID = ID;
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}
	}

	static enum Ring{
		Damage1(0, 25, 1, 0),
		Damage2(1, 50, 2, 0),
		Damage3(2, 100, 3, 0),
		Defense1(3, 20, 0, 1),
		Defense2(4, 40, 0, 2),
		Defense3(5, 80, 0, 3),
		NONE(6, 0, 0, 0);

		private final int ID;
		private final int cost;
		private final int damage;
		private final int armor;

		private Ring(int ID, int cost, int damage, int armor){
			this.ID = ID;
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}
	}
}
