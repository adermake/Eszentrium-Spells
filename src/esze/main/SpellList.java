package esze.main;

import java.util.ArrayList;

import esze.utils.MathUtils;
import spells.spellcore.Spell;
import spells.spells.Ansturm;
import spells.spells.AntlitzderGöttin;
import spells.spells.Aufwind;
import spells.spells.AugedesDrachen;
import spells.spells.Schallwelle;
import spells.spells.Scharfschuss;

public class SpellList {

	public static ArrayList<Spell> spells = new ArrayList<Spell>();
	
	
	
	
	public static ArrayList<Spell> getDiffrentRandom(int count) {
		ArrayList<Spell> randomList = (ArrayList<Spell>) spells.clone();
		while (randomList.size()>count) {
			
			randomList.remove(MathUtils.randInt(0, randomList.size()-1));
		}
		
		
		return randomList;
		
	}
	
	
	public static void registerSpells() {
		spells.add(new Ansturm());
		spells.add(new AntlitzderGöttin());
		spells.add(new Aufwind());
		spells.add(new AugedesDrachen());
		spells.add(new Schallwelle());
		spells.add(new Scharfschuss());
	}
	
}
