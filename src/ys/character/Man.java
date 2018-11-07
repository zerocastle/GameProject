package ys.character;

public class Man {
	
	public static final int BLOOD_MAX = 3;
	
	private String name;
	private int blood;
	
	public Man() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public static int getBloodMax() {
		return BLOOD_MAX;
	}
	

}
