package esze.analytics.solo;

import java.util.ArrayList;

public class SaveSelection {
	
	private String sp1;
	private String sp2;
	private String sp3;
	private String sp4;
	private String chsp;
	
	public SaveSelection() {}
	
	public SaveSelection(String chsp ,String sp1, String sp2, String sp3, String sp4) {
		this.sp1 = sp1;
		this.sp2 = sp2;
		this.sp3 = sp3;
		this.sp4 = sp4;
		this.chsp = chsp;
	}



	public SaveSelection(String s) {
		s = s.replaceAll("[", "");
		s = s.replaceAll("]", "");
		String[] g = s.split(",");
		chsp = g[0];
		sp1 = g[1];
		sp2 = g[2];
		sp3 = g[3];
		sp4 = g[4];
	}
	
	@Override
	public String toString() {
		String s = "[";
		
		s += chsp + ",";
		s += sp1 + ",";
		s += sp2 + ",";
		s += sp3 + ",";
		s += sp4;
		
		return s + "]";
	}
	
	public String getSp1() {
		return sp1;
	}
	public void setSp1(String sp1) {
		this.sp1 = sp1;
	}
	public String getSp2() {
		return sp2;
	}
	public void setSp2(String sp2) {
		this.sp2 = sp2;
	}
	public String getSp3() {
		return sp3;
	}
	public void setSp3(String sp3) {
		this.sp3 = sp3;
	}
	public String getSp4() {
		return sp4;
	}
	public void setSp4(String sp4) {
		this.sp4 = sp4;
	}
	public String getChsp() {
		return chsp;
	}
	public void setChsp(String chsp) {
		this.chsp = chsp;
	}
	
	public ArrayList<String> getChoices() {
		ArrayList<String> choices = new ArrayList<>();
		choices.add(sp1);
		choices.add(sp2);
		choices.add(sp3);
		choices.add(sp4);
		return choices;
	}

}
