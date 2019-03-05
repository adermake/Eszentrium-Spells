package esze.main;

import java.util.ArrayList;

import esze.utils.MathUtils;
import net.minecraft.server.v1_13_R2.Explosion;
import spells.spellcore.Spell;
import spells.spells.Ansturm;
import spells.spells.AntlitzderGöttin;
import spells.spells.Aufwind;
import spells.spells.AugedesDrachen;
import spells.spells.Avatar;
import spells.spells.Beben;
import spells.spells.Blasensturm;
import spells.spells.Chaoswelle;
import spells.spells.Enterhaken;
import spells.spells.Erdsurfer;
import spells.spells.Feuerball;
import spells.spells.Flucht;
import spells.spells.Heilen;
import spells.spells.Schallbrecher;
import spells.spells.Schallwelle;
import spells.spells.Scharfschuss;
import spells.spells.Spinnenkäfig;

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
		spells.add(new Avatar());
		spells.add(new Beben());
		spells.add(new Chaoswelle());
		spells.add(new Enterhaken());
		//spells.add(new Explosion());
		spells.add(new Blasensturm());
		spells.add(new Feuerball());
		spells.add(new Flucht());
		spells.add(new Heilen());
		spells.add(new Erdsurfer());
		spells.add(new Schallbrecher());
		spells.add(new Spinnenkäfig());
	}
	
}
