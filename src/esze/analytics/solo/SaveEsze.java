package esze.analytics.solo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import esze.main.main;

public class SaveEsze {
	
	private ArrayList<SaveGame> sv;
	
	public SaveEsze() {
		sv = new ArrayList<>();
	}
	
	public SaveEsze(String s) {
		sv = new ArrayList<>();
		String[] args = SaveUtils.readString(s);
		for (int i = 0; i < args.length; i++) {
			sv.add(new SaveGame(args[i]));
		}
	}
	
	public void add(SaveGame g) {
		sv.add(g);
	}
	
	@Override
	public String toString() {
		String s = "[";
		
		for (SaveGame g : sv) {
			s += g.toString() + ",";
		}
		if (s.length() > 1) {
			s = s.substring(0, s.length()-1);
		}
		return s + "]";
	}
	
	//Analytics ----------------------------------------------------------------------------------------------
	
	public double getWorth(String s) {
		int choice = 0;
		int chosen = 0;
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				for (SaveSelection sele : p.getSelections()) {
					if (sele.getChsp().equals(s)) {
						chosen++;
					}
					if (sele.getChoices().contains(s)) {
						choice++;
					}
				}
			}
		}
		
		return (100 * ((double) chosen / (double) choice));
	}
	
	public int getVictories(String s) {
		int victories = 0;
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				if (p.getName().equals(s)) {
					if (g.getMap().get(p) == 1) {
						victories++;
					}
				}
			}
		}
		return victories;
	}
	
	public int getLosses(String s) {
		int losses = 0;
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				if (p.getName().equals(s)) {
					if (g.getMap().get(p) != 1) {
						losses++;
					}
				}
			}
		}
		return losses;
	}
	
	public void changeSpellName(String old_s, String new_s) {
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				for (SaveSelection sele : p.getSelections()) {
					if (sele.getChsp().equals(old_s)) {
						sele.setChsp(new_s);
					}
					if (sele.getSp1().equals(old_s)) {
						sele.setSp1(new_s);
					}
					if (sele.getSp2().equals(old_s)) {
						sele.setSp2(new_s);
					}
					if (sele.getSp3().equals(old_s)) {
						sele.setSp3(new_s);
					}
					if (sele.getSp4().equals(old_s)) {
						sele.setSp4(new_s);
					}
				}
			}
		}
	}

	public int getKills(String name) {
		int kills = 0; 
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				for (String s : p.getDeaths()) {
					String[] edit = s.split("-");
					if (edit.length >= 2) {
						if (edit[1].equals(name)) {
							kills++;
						}
					}
				}
			}
		}
		return kills;
	}
	
	public int getSpellKills(String name, String spell) {
		int kills = 0; 
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				for (String s : p.getDeaths()) {
					String[] edit = s.split("-");
					if (edit.length >= 2) {
						if (edit[1].equals(name) && edit[0].equals(spell)) {
							kills++;
						}
					}
				}
			}
		}
		return kills;
	}
	
	public int getDeaths(String name) {
		int deaths = 0;
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				if (p.getName().equals(name)) {
					deaths += p.getDeathCount();
				}
			}
		}
		return deaths;
	}
	
	public int getSpellDeaths(String name, String spell) {
		int deaths = 0; 
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				if (p.getName().equals(name)) {
					for (String s : p.getDeaths()) {
						String[] edit = s.split(Pattern.quote("-"));
						if (edit.length >= 1) {
							if (edit[0].equals(spell)) {
								deaths++;
							}
						}
					}
				}
			}
		}
		return deaths;
	}

	public double getSpellWorth(String name, String s) {
		
		int choice = 0;
		int chosen = 0;
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				if (p.getName().equals(name)) {
					for (SaveSelection sele : p.getSelections()) {
						for (String tag : main.colorTags) {
							if (sele.getChsp().equals(tag + s)) {
								chosen++;
							}
							if (sele.getChoices().contains(tag + s)) {
								choice++;
							}
						}
					}
				}
			}
		}
		if (choice == 0) {
			return 0;
		}
		return (100 * ((double) chosen / (double) choice));
	}
	
	public String getFavSpell(String name) {
		
		HashMap<String, Integer> map = new HashMap<>();
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				if (p.getName().equals(name)) {
					for (SaveSelection sele : p.getSelections()) {
						if (map.containsKey(sele.getChsp())) {
							Integer i = map.get(sele.getChsp()) + 1;
							map.remove(sele.getChsp());
							map.put(sele.getChsp(), i);
						} else {
							map.put(sele.getChsp(), 0);
						}
					}
				}
			}
		}
		if (map.size() == 0) {
			return "";
		}
		String max = (String) map.keySet().toArray()[0];
		for (String s : map.keySet()) {
			if (map.get(s) > map.get(max)) {
				max = s;
			}
		}
		return max;
	}
	
	public String getFavSpell() {
		
		HashMap<String, Integer> map = new HashMap<>();
		for (SaveGame g : sv) {
			for (SavePlayer p : g.getMap().keySet()) {
				
				for (SaveSelection sele : p.getSelections()) {
					if (map.containsKey(sele.getChsp())) {
						Integer i = map.get(sele.getChsp()) + 1;
						map.remove(sele.getChsp());
						map.put(sele.getChsp(), i);
					} else {
						map.put(sele.getChsp(), 0);
					}
				}
				
			}
		}
		if (map.size() == 0) {
			return "";
		}
		String max = (String) map.keySet().toArray()[0];
		for (String s : map.keySet()) {
			if (map.get(s) > map.get(max)) {
				max = s;
			}
		}
		return max;
	}
	
}
