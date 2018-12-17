package esze.analytics.solo;

import java.util.ArrayList;

public class SaveUtils {
	
	private static SaveGame gme =null;
	private static SaveEsze svgms = null;
	private static final String FILE = "analytics_solo.sav";
	
	public static String[] readString(String s) {
		ArrayList<String> list = new ArrayList<String>();
		s = s.substring(1, s.length()-1);
		int depth = 0;
		int lastloc = 0;
		for (int i = 0; i < s.length();i++) {
			char c = s.charAt(i);
			switch (c) {
			case '[':
				depth++;
				break;
			case ']':
				depth--;
				break;
			case ',':
				if (depth == 0) {
					list.add(s.substring(lastloc,(i-1)));
					lastloc = (i+1);
				}
				break;
			default:
				break;
			}
		}
		String[] l = new String[list.size()];
		int i = 0;
		for (String bla : list) {
			l[i] = bla;
			i++;
		}
		return l;
	}
	
	public static void startGame() {
		if (gme != null) {
			return;
		}
		gme = new SaveGame();	
	}
	
	public static void endGame() {
		if (svgms == null) {
			load();
		}
		svgms.add(gme);
		gme = null;
	}
	
	public static void addPlayer(String s) {
		if (gme == null) {return;}
		gme.addPlayer(s);
	}
	
	public static void addPlayerDeath(String name,String cause) {
		if (gme == null) {return;}
		gme.addDeath(name,cause);
	}
	
	public static void addPlayerSelection(String name,SaveSelection sele) {
		if (gme == null) {return;}
		gme.addSelect(name,sele);
	}
	
	public static void load() {
		String s = FILE;
	}
	
	public static void save() {
		String s = FILE;
	}
}