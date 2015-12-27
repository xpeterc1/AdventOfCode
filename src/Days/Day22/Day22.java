package Days.Day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Day22 {
	static final int BOSS_HEALTH = 55;
	static final int BOSS_DAMAGE = 8;
	static final int WIZARD_HEALTH = 50;
	static final int WIZARD_ARMOR = 0;
	static final int WIZARD_MANA = 500;
	static int minMana = Integer.MAX_VALUE;
	static List<Spell> spells = new ArrayList<Spell>();

	public static void main(String... args) throws InstantiationException, IllegalAccessException{
		spells.add(new Recharge());
		spells.add(new Shield());
		spells.add(new Drain());
		spells.add(new MagicMissile());
		spells.add(new Poison());

		Wizard wizard = new Wizard();
		Boss boss = new Boss();
		System.out.println("Part 1: " + Collections.min(getManaCost(wizard, boss, new ArrayList<Spell>(), 0, true, 0, false)));
		minMana = Integer.MAX_VALUE;
		System.out.println("Part 2: " + Collections.min(getManaCost(wizard, boss, new ArrayList<Spell>(), 0, true, 0, true)));

	}

	public static ArrayList<Integer> getManaCost(Wizard wizard, Boss boss, List<Spell> effects, int manaCost, boolean WizardTurn, int turn, boolean hardMode) throws InstantiationException, IllegalAccessException{
		ArrayList<Integer> totalManaCost = new ArrayList<Integer>();

		if(manaCost > minMana){
			return totalManaCost;
		}

		if(boss.getHealth() <= 0){
			if(manaCost < minMana){
				minMana = manaCost;
			}
			totalManaCost.add(manaCost);
			return totalManaCost;
		}
		//Hardmode and wizard's turn
		if(hardMode && WizardTurn){
			wizard.receiveDamage(1);
		}
		if(wizard.getHealth() <= 0){
			return totalManaCost;
		}
		//Apply effects still in play
		Iterator<Spell> iter = effects.iterator();
		while (iter.hasNext()) {
			Spell spell = iter.next();
			if (spell.applySpell(wizard, boss)){
				iter.remove();
			}
		}

		if(boss.getHealth() <= 0){
			if(manaCost < minMana){
				minMana = manaCost;
			}
			totalManaCost.add(manaCost);
			return totalManaCost;
		}

		if(WizardTurn && wizard.getHealth() > 0){
			//Player Turn
			for(Spell spell: spells){
				boolean containsEffect = false;
				for(Spell effect: effects){
					if(spell.getClass().equals(effect.getClass())){
						containsEffect = true;
					}
				}

				if(!containsEffect && wizard.getMana() >= spell.getManaCost()){
					if(spell.isAnEffectType()){
						List<Spell> nextEffects = new ArrayList<Spell>(effects.size());
						for(Spell copy: effects){
							nextEffects.add(copy.getClone());
						}
						nextEffects.add(spell.getClass().newInstance());
						Wizard nextWizard = new Wizard(wizard, spell.getManaCost());
						Boss nextBoss = new Boss(boss);
						totalManaCost.addAll(getManaCost(nextWizard, nextBoss, nextEffects, manaCost + spell.getManaCost(), !WizardTurn, turn, hardMode));
					}else{				
						List<Spell> nextEffects = new ArrayList<Spell>(effects.size());
						for(Spell copy: effects){
							nextEffects.add(copy.getClone());
						}
						Wizard nextWizard = new Wizard(wizard, spell.getManaCost());
						Boss nextBoss = new Boss(boss);
						spell.applySpell(nextWizard, nextBoss);
						totalManaCost.addAll(getManaCost(nextWizard, nextBoss, nextEffects, manaCost + spell.getManaCost(), !WizardTurn, turn, hardMode));
					}
				}
			}
		}else{
			//BOSS TURN
			wizard.receiveDamage(BOSS_DAMAGE);
			if(wizard.getHealth() > 0){
				totalManaCost.addAll(getManaCost(wizard, boss, effects, manaCost, !WizardTurn, turn + 1, hardMode));
			}			
		}
		return totalManaCost;			
	}

	private static class Wizard{
		int health;
		int mana;
		int armor;
		public Wizard(){
			this.health = WIZARD_HEALTH;
			this.mana = WIZARD_MANA;
			this.armor = WIZARD_ARMOR;
		}
		public Wizard(Wizard wizard, int manaCost){
			this.health = wizard.getHealth();
			this.mana = wizard.getMana() - manaCost;
			this.armor = wizard.getArmor();
		}
		public void receiveDamage(int damage){
			health -= Math.max(1, damage - armor);
		}
		public void receiveHeal(int heal){
			health += heal;
		}
		public void setArmor(int magicArmor){
			this.armor = magicArmor;
		}
		public int getHealth(){
			return this.health;
		}
		public int getMana(){
			return this.mana;
		}
		public int getArmor(){
			return this.armor;
		}
		public void addMana(int additionalMana) {
			this.mana += additionalMana;
		}

	}
	private static class Boss{
		int health;
		public Boss(){
			this.health = BOSS_HEALTH;
		}
		public Boss(Boss boss){
			this.health = boss.getHealth();
		}
		public int getHealth(){
			return this.health;
		}
		public void reciveDamage(int damage){
			this.health -= damage;
		}
	}

	private interface Spell{
		public Spell getClone();
		public int getManaCost();
		public boolean isAnEffectType();
		public boolean applySpell(Wizard wizard, Boss boss);
	}
	private static class MagicMissile implements Spell{
		final int mana = 53;
		final boolean effect = false;
		@Override
		public boolean applySpell(Wizard wizard, Boss boss) {
			boss.reciveDamage(4);
			return true;
		}
		@Override
		public int getManaCost() {
			return mana;
		}
		@Override
		public boolean isAnEffectType() {
			return effect;
		}
		@Override
		public String toString(){
			return "Magic Missile";
		}

		@Override
		public Spell getClone() {
			return null;
		}

	}
	public static class Drain implements Spell{
		final int mana = 73;
		final boolean effect = false;
		@Override
		public boolean applySpell(Wizard wizard, Boss boss) {
			wizard.receiveHeal(2);
			boss.reciveDamage(2);
			return true;
		}
		@Override
		public int getManaCost() {
			return mana;
		}
		@Override
		public boolean isAnEffectType() {
			return effect;
		}

		@Override
		public String toString(){
			return "Drain";
		}

		@Override
		public Spell getClone() {
			return null;
		}

	}
	public static class Shield implements Spell{
		final int mana = 113;
		final boolean effect = true;
		private int duration;
		public Shield(){
			this.duration = 6;
		}
		public Shield(Shield shield){
			this.duration = shield.getDuration();
		}
		private int getDuration() {
			return duration;
		}
		@Override
		public boolean applySpell(Wizard wizard, Boss boss) {
			if(duration > 0){
				wizard.setArmor(7);
				duration--;	
				if(duration == 0){
					wizard.setArmor(0);
					return true;
				}
				return false;
			}
			wizard.setArmor(0);
			return true;
		}
		@Override
		public int getManaCost() {
			return mana;
		}

		@Override
		public String toString(){
			return "Shield";
		}
		@Override
		public Spell getClone() {
			return new Shield(this);
		}
		@Override
		public boolean isAnEffectType() {
			return true;
		}

	}
	public static class Poison implements Spell{
		final int mana = 173;
		final boolean effect = true;
		private int duration;
		public Poison(){
			this.duration = 6;
		}
		public Poison(Poison poison){
			this.duration = poison.getDuration();
		}
		@Override
		public boolean applySpell(Wizard wizard, Boss boss) {
			if(duration > 0){
				boss.reciveDamage(3);
				duration--;
				if(duration == 0){
					return true;
				}
				return false;
			}
			return true;
		}
		@Override
		public int getManaCost() {
			return mana;
		}
		@Override
		public boolean isAnEffectType() {
			return effect;
		}

		@Override
		public String toString(){
			return "Poison";
		}
		public int getDuration() {
			return duration;
		}
		@Override
		public Spell getClone() {
			return new Poison(this);
		}

	}
	public static class Recharge implements Spell{
		final int mana = 229;
		final boolean effect = true;
		private int duration;
		public Recharge(){
			this.duration = 5;
		}
		public Recharge(Recharge recharge){
			this.duration = recharge.getDuration();
		}
		@Override
		public boolean applySpell(Wizard wizard, Boss boss) {
			if(duration > 0){
				wizard.addMana(101);
				duration--;
				if(duration == 0){
					return true;
				}
				return false;
			}
			return true;

		}
		@Override
		public int getManaCost() {
			return mana;
		}
		@Override
		public boolean isAnEffectType() {
			return effect;
		}

		@Override
		public String toString(){
			return "Recharge";
		}
		public int getDuration() {
			return duration;
		}
		@Override
		public Spell getClone() {
			return new Recharge(this);
		}

	}

}
//173