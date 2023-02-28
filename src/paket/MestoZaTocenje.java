package paket;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class MestoZaTocenje extends Canvas implements Runnable {
	
	private AparatZaTocenje owner;
	Sladoled sladoled;
	private Thread nit = new Thread(this);
	private boolean radi = false;
	private Ukus ukus;
	private ArrayList<Color> boje;

	public MestoZaTocenje(AparatZaTocenje a, int x, int y) {
		owner = a;
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(x,y));
		novo();
		nit.start();
	}
	
	public void novo() {
		sladoled = new Sladoled(200);
		boje = new ArrayList<>();
		repaint();
	}
	
	public synchronized void pocni(Ukus u) {
		ukus = u;
		radi = true;
		notify();
	}
	public synchronized void pauza() {
		radi = false;
	}
	public void stani() {
		nit.interrupt();
	}
	@Override
	public void paint(Graphics g) {
		int a = this.getHeight() / 10;
		int b = this.getHeight() - a;
		for (Color c : boje) {
			g.setColor(c);
			g.fillRect(0, b, this.getWidth(), a);
			//System.out.println(b + ", " + (b - a) + ", " + c);
			b = b - a;
		}
		//System.out.println("-----------");
	}
	
	
	@Override
	public void run() {
		try {	
			while (!Thread.interrupted()){
				synchronized (this) {
					while (radi == false) wait();
				}
				sladoled.dodaj(ukus, 20);
				
				if (sladoled.pun()) owner.prodaj.setEnabled(true);
				
				if(boje.size() < 10) boje.add(ukus.getBoja());
				
				Thread.sleep(500);
				owner.sladoled.setText(sladoled.toString());
				owner.sladoled.revalidate();
				repaint();
				
			}
		}catch (InterruptedException e) {}		
	}

	
	public Sladoled getSladoled() {
		return sladoled;
	}

	public ArrayList<Color> getBoje() {
		return boje;
	}
	
	
	

}










