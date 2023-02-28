package paket;

import java.awt.Color;
import java.awt.Panel;

public class Ukus {

	String naziv;
	Color boja;
	
	public Ukus(String n, Color b) {
		this.naziv = n;
		this.boja = b;
	}

	public String getNaziv() {
		return naziv;
	}

	public Color getBoja() {
		return boja;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Ukus)) return false;
		Ukus other = (Ukus) obj;
		if (!naziv.equals(other.naziv)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + naziv  + "]";
	}
	
	public static void main(String[] arg) {
		//Ukus u = new Ukus("Jagoda",new Color(ff22aa, true));
	}
	
}
