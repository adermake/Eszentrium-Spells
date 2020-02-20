package spells.spellcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;

import spells.spells.Ansturm;
import spells.spells.Mondkugel;
import spells.spells.AntlitzderG�ttin;
import spells.spells.Archon;
import spells.spells.Astralsprung;
import spells.spells.Aufwind;
import spells.spells.AugedesDrachen;
import spells.spells.Avatar;
import spells.spells.Beben;
import spells.spells.Binden;
import spells.spells.Blasensturm;
import spells.spells.Chaoswelle;
import spells.spells.Enterhaken;
import spells.spells.Erdsurfer;
import spells.spells.Erl�sung;
import spells.spells.Explosion;
import spells.spells.Feuerball;
import spells.spells.Feuerwerkshelix;
import spells.spells.Flucht;
import spells.spells.Delfintorpedo;
import spells.spells.Heilen;
import spells.spells.HimmlischesUrteil;
import spells.spells.H�hnchenluftschlag;
import spells.spells.Kaminchen;
import spells.spells.Knochenparty;
import spells.spells.K�tzchenkanone;
import spells.spells.Lamaturm;
import spells.spells.Lichtstrudel;
import spells.spells.Luftsprung;
import spells.spells.Magmafalle;
import spells.spells.Magnetball;
import spells.spells.Meteoritenhagel;
import spells.spells.Notenzauber;
import spells.spells.Opfersuche;
import spells.spells.Orbitar;
import spells.spells.Pyroschlag;
import spells.spells.Ranke;
import spells.spells.Raumwechsel;
import spells.spells.RufderOzeane;
import spells.spells.Schallbrecher;
import spells.spells.Schallwelle;
import spells.spells.Scharfschuss;
import spells.spells.Schicksalsschnitt;
import spells.spells.Schleimschleuder;
import spells.spells.Requiemspfeil;
import spells.spells.SchnittdersiebenWinde;
import spells.spells.Schock;
import spells.spells.SchreidesPh�nix;
import spells.spells.Schwerkraftsmanipulation;
import spells.spells.SchwerterausLicht;
import spells.spells.SiegelderFurcht;
import spells.spells.SpeerderZwietracht;
import spells.spells.Stich;
import spells.spells.Teleport;
import spells.spells.Thermolanze;
import spells.spells.Vampirpilz;
import spells.spells.Verstummen;
import spells.spells.Wunsch;
import spells.spells.Wurmloch;
import spells.spells.Zaubersprung;
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
	
public static ArrayList<Spell> getDiffrentRandomGreen(int count) {
		
		ArrayList<Spell> spellsForThisType = new ArrayList<Spell>();
		
		for(Spell spell : spells.keySet()){
			if (spell.name.contains("�c") || spell.name.contains("�8")) {
				continue;
			}
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
	
	public static void registerSpells() {
		spells.clear();
		//registerSpell(new Ansturm());
		//registerSpell(new Astralsprung());
		registerSpell(new AntlitzderG�ttin());
		registerSpell(new Aufwind());
		registerSpell(new AugedesDrachen());
		registerSpell(new Schallwelle());
		registerSpell(new Scharfschuss());
		registerSpell(new SchreidesPh�nix(), TypeTTT.class);
		registerSpell(new Avatar());
		registerSpell(new Beben());
		registerSpell(new Chaoswelle());
		registerSpell(new Enterhaken());
		registerSpell(new Explosion());
		registerSpell(new Blasensturm());
		registerSpell(new Feuerball());
		registerSpell(new Flucht());
		registerSpell(new Archon(), TypeTTT.class);
		registerSpell(new Erl�sung(), TypeTTT.class);
		registerSpell(new Heilen(), TypeTTT.class);
		registerSpell(new H�hnchenluftschlag());
		registerSpell(new K�tzchenkanone());
		registerSpell(new Kaminchen());
		//registerSpell(new HimmlischesUrteil());
		registerSpell(new Lamaturm());
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
		
		registerSpell(new Requiemspfeil());
		registerSpell(new Ranke());
		registerSpell(new Schicksalsschnitt());
		registerSpell(new SchnittdersiebenWinde());
		registerSpell(new SpeerderZwietracht());
		registerSpell(new SiegelderFurcht());
		registerSpell(new Teleport());
		registerSpell(new Thermolanze());
		registerSpell(new Verstummen());
		registerSpell(new Vampirpilz());
		
		registerSpell(new Wunsch());
		registerSpell(new Wurmloch());
		registerSpell(new Zaubersprung());
		registerSpell(new Mondkugel());
		registerSpell(new Feuerwerkshelix());
		registerSpell(new Delfintorpedo());
		registerSpell(new Knochenparty());
		//registerSpell(new Binden());
		registerSpell(new Stich());
		registerSpell(new Schleimschleuder());
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
