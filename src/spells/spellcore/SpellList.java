package spells.spellcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;

import spells.spells.Ansturm;
import spells.spells.AntlitzderG�ttin;
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
import esze.enums.GameType;
import esze.types.TypeSOLO;
import esze.types.TypeTTT;
import esze.utils.MathUtils;

public class SpellList {

	public static HashMap<Spell, List<Class>> spells = new HashMap<Spell, List<Class>>();
	public static ArrayList<Spell> traitorSpells = new ArrayList<Spell>();
	
	
	
	public static ArrayList<Spell> getDiffrentRandom(int count) {
		
		ArrayList<Spell> spellsForThisType = new ArrayList<Spell>();
		
		for(Spell spell : spells.keySet()){
			List<Class> classes = spells.get(spell);
			
			if(classes.contains(GameType.getType().getClass())){
				spellsForThisType.add(spell);
			}
		}
		
		
		ArrayList<Spell> randomList = (ArrayList<Spell>) spellsForThisType.clone();
		while (randomList.size()>count) {
			randomList.remove(MathUtils.randInt(0, randomList.size()-1));
		}
		return randomList;
		
	}
	
	public static Spell getRandomSpell() {
		HashMap<Spell, List<Class>> spellsForThisType = new HashMap<Spell, List<Class>>();
		
		for(Spell spell : spells.keySet()){
			List<Class> classes = spells.get(spell);
			
			if(classes.contains(GameType.getType().getClass())){
				spellsForThisType.put(spell, classes);
			}
		}
		
		
		return new ArrayList<>(spellsForThisType.keySet()).get(MathUtils.randInt(0, spellsForThisType.size()-1));
	}
	
	public static void registerSpells() {
		registerSpell(new Ansturm());
		registerSpell(new AntlitzderG�ttin());
		registerSpell(new Aufwind());
		registerSpell(new AugedesDrachen());
		registerSpell(new Schallwelle());
		registerSpell(new Scharfschuss());
		registerSpell(new Avatar());
		registerSpell(new Beben());
		registerSpell(new Chaoswelle());
		registerSpell(new Enterhaken());
		registerSpell(new Explosion());
		registerSpell(new Blasensturm());
		registerSpell(new Feuerball());
		registerSpell(new Flucht());
		registerSpell(new Heilen(), TypeTTT.class);
		//registerSpell(new HimmlischesUrteil(), TypeTTT.class);
		registerSpell(new Lamaturm());
		registerSpell(new Lichtstrudel());
		registerSpell(new Lichtstrudel());
		registerSpell(new Luftsprung());
		registerSpell(new Magmafalle());
		registerSpell(new Magnetball());
		registerSpell(new Meteoritenhagel());
		registerSpell(new Notenzauber());
		registerSpell(new Opfersuche());
		registerSpell(new Orbitar());
		registerSpell(new Raumwechsel());
		registerSpell(new RufderOzeane());
		registerSpell(new Erdsurfer());
		registerSpell(new Schallbrecher());
		registerSpell(new SchwerterausLicht());
		registerSpell(new Schock());
		registerSpell(new Schwerkraftsmanipulation());
		registerSpell(new Schicksalschnitt());
		registerSpell(new SchnittdersiebenWinde());
		registerSpell(new SiegelderFurcht());
	}
	
	public static void registerSpell(Spell spell, Class...gameTypes){
		ArrayList<Class> classes = new ArrayList<Class>();
		for(Class glass : gameTypes){
			classes.add(glass);
		}
		
		
		if(classes.isEmpty()){
			classes.add(TypeTTT.class);
			classes.add(TypeSOLO.class);
		}
		
		spells.put(spell, classes);
	}
	
	public static void registerTraitorSpells() {
		
	}
	
}
