package trainingapp.food;

public class Essen {
	
	private String essen;
	private double kh;
	private double ew;
	private double fe;
	private double kcal;
	private int menge;
	
	public Essen(String essen, double kh, double ew, double fe, double kcal) {
		this.essen = essen;
		this.kh = kh;
		this.ew = ew;
		this.fe = fe;
		this.kcal = kcal;
	}
	
	public Essen(String essen, double kh, double ew, double fe, double kcal, int menge) {
		this.essen = essen;
		this.kh = kh * 100/menge;
		this.ew = ew * 100/menge;
		this.fe = fe * 100/menge;
		this.kcal = kcal * 100/menge;
		this.menge = menge;
	}

	public String getEssen() {
		return essen;
	}

	public void setEssen(String essen) {
		this.essen = essen;
	}

	public double getKh() {
		return kh/100 * this.menge;
	}

	public void setKh(double kh) {
		this.kh = kh * 100/menge;
	}

	public double getEw() {
		return ew/100 * this.menge;
	}

	public void setEw(double ew) {
		this.ew = ew * 100/menge;
	}

	public double getFe() {
		return fe/100 * this.menge;
	}

	public void setFe(double fe) {
		this.fe = fe * 100/menge;
	}

	public double getKcal() {
		return kcal/100 * this.menge;
	}

	public void setKcal(double kcal) {
		this.kcal = kcal * 100/menge;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

}
