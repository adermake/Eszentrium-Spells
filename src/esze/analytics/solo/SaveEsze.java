package esze.analytics.solo;

import java.util.ArrayList;

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

}
