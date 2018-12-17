package esze.analytics.solo;

import java.util.HashMap;

public class SaveGame {
	
	private HashMap<SavePlayer, Integer> map = new HashMap<>();
	
	public SaveGame() {
		
	}
	
	public SaveGame(String s) {
		String[] args = SaveUtils.readString(s);
		for (int i = 0; i < args.length; i+=2) {
			map.put(new SavePlayer(args[i]), Integer.parseInt(args[i]));
		}
	}
	
	@Override
	public String toString() {
		String s = "[";
		
		for (SavePlayer sp : map.keySet()) {
			s += sp.toString() + ",";
			s += map.get(sp).intValue() + ",";
		}
		s = s.substring(0, s.length() - 1);
		return s + "]";
	}

	public HashMap<SavePlayer, Integer> getMap() {
		return map;
	}

	public void addPlayer(String s) {
		for (SavePlayer p : map.keySet()) {
			if (p.getName().equals(s)) {
				return;
			}
		}
		SavePlayer sav = new SavePlayer();
		sav.setName(s);
		map.put(sav, 0);
	}

	public void addDeath(String name, String cause) {
		boolean fin = false;
		SavePlayer pl = null;
		for (SavePlayer p : map.keySet()) {
			if (p.getName().equals(name)) {
				fin = p.addDeath(cause);
				pl = p;
			}
		}
		
		if (fin) {
			int max = 0;
			for (Integer i : map.values()) {
				if (max <= i) {
					max = i;
				}
			}
			if (pl != null) {
				map.remove(pl);
				map.put(pl, max+1);
			}
		}
	}

	public void addSelect(String name, SaveSelection sele) {
		for (SavePlayer p : map.keySet()) {
			if (p.getName().equals(name)) {
				p.addSelect(sele);
			}
		}
	}
	
	

}
