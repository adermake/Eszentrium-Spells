package spells.spellcore;

import java.util.ArrayList;

import esze.utils.MathUtils;
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
import spells.spells.Explosion;
import spells.spells.Feuerball;
import spells.spells.Flucht;
import spells.spells.Heilen;
import spells.spells.HimmlischesUrteil;
import spells.spells.Lamaturm;
import spells.spells.Lichtstrudel;
import spells.spells.Luftsprung;
import spells.spells.Magmafalle;
import spells.spells.Magnetball;
import spells.spells.Meteoritenhagel;
import spells.spells.Notenzauber;
import spells.spells.Opfersuche;
import spells.spells.Orbitar;
import spells.spells.Raumwechsel;
import spells.spells.RufderOzeane;
import spells.spells.Schallbrecher;
import spells.spells.Schallwelle;
import spells.spells.Scharfschuss;
import spells.spells.Schicksalschnitt;
import spells.spells.SchnittdersiebenWinde;
import spells.spells.Schock;
import spells.spells.Schwerkraftsmanipulation;
import spells.spells.SchwerterausLicht;
import spells.spells.SiegelderFurcht;

public class SpellList {

	public static ArrayList<Spell> spells = new ArrayList<Spell>();
	public static ArrayList<Spell> traitorSpells = new ArrayList<Spell>();
	
	
	
	public static ArrayList<Spell> getDiffrentRandom(int count) {
		ArrayList<Spell> randomList = (ArrayList<Spell>) spells.clone();
		while (randomList.size()>count) {
			randomList.remove(MathUtils.randInt(0, randomList.size()-1));
		}
		return randomList;
		
	}
	
	public static Spell getRandomSpell() {
		return spells.get(MathUtils.randInt(0, spells.size()-1));
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
		spells.add(new Explosion());
		spells.add(new Blasensturm());
		spells.add(new Feuerball());
		spells.add(new Flucht());
		//spells.add(new Heilen());
		//spells.add(new HimmlischesUrteil());
		spells.add(new Lamaturm());
		spells.add(new Lichtstrudel());
		spells.add(new Lichtstrudel());
		spells.add(new Luftsprung());
		spells.add(new Magmafalle());
		spells.add(new Magnetball());
		spells.add(new Meteoritenhagel());
		spells.add(new Notenzauber());
		spells.add(new Opfersuche());
		spells.add(new Orbitar());
		spells.add(new Raumwechsel());
		spells.add(new RufderOzeane());
		spells.add(new Erdsurfer());
		spells.add(new Schallbrecher());
		spells.add(new SchwerterausLicht());
		spells.add(new Schock());
		spells.add(new Schwerkraftsmanipulation());
		spells.add(new Schicksalschnitt());
		spells.add(new SchnittdersiebenWinde());
		spells.add(new SiegelderFurcht());
	}
	public static void registerTraitorSpells() {
		
	}
	
}
