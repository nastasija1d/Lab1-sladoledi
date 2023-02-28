package paket;

import java.awt.Color;
import java.util.ArrayList;

public class Sladoled {

	class Sastojak{
		Ukus ukus;
		int kolicina;
		
		public Sastojak(Ukus u, int k) {
			ukus = u;
			kolicina = k;
		}
		public void dodaj(int d) {
			kolicina+=d;
		}
		public Ukus getUkus() {return ukus;}
		public int getKolicina() {return kolicina;}
	}

	private int velicina, trenutno;
	ArrayList<Sastojak> ukusi;
	
	public Sladoled(int i) {
		velicina = i;
		trenutno = 0;
		ukusi = new ArrayList<>();
	}
	
	public boolean pun() {return velicina == trenutno;}
	
	public void dodaj(Ukus u,int kol) {
		if ((kol + trenutno) > velicina) kol = velicina - trenutno;
		if (kol == 0) return;
		trenutno += kol;
		boolean nema = true;
		for (Sastojak s : ukusi) {
			if (u.equals(s.getUkus())) {
				s.dodaj(kol);
				nema = false;
				break;
			}
		}
		if (nema) {
			ukusi.add(new Sastojak(u, kol));
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Sastojak s : ukusi) {
			sb.append(" ").append(s.getKolicina()).append("ml").append(s.getUkus());
		}
		return sb.toString();
	}
	
	
	public static void main (String[] arg) {
		Ukus u1 = new Ukus("Malina",Color.RED);
		Ukus u2 = new Ukus("Cokolada",Color.ORANGE);
		Ukus u3 = new Ukus("Banana",Color.YELLOW);
		
		Sladoled s = new Sladoled(100);
		s.dodaj(u1, 30);
		s.dodaj(u3, 40);
		s.dodaj(u1, 50);
		s.dodaj(u2, 10);
		
		System.out.println(s);
	}
	
}
