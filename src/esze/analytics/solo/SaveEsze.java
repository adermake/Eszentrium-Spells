package esze.analytics.solo;

import java.util.ArrayList;

public class SaveEsze {
	
	private ArrayList<SaveGame> sv = new ArrayList<>();
	
	public SaveEsze() {
		
	}
	
	public SaveEsze(String s) {
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
		s = s.substring(0, s.length()-1);
		return s + "]";
	}
	
	
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

}
