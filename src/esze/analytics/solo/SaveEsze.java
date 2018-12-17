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
	
	//Anaysis

}
