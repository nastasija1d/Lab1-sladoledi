package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AparatZaTocenje extends Panel {
	
	Button prodaj = new Button("Prodaj");
	private MestoZaTocenje tocilica;
	private Sladoledzinica owner;
	Label sladoled = new Label("");
	private ArrayList<Button> dugmici = new ArrayList<>();
	Panel center = new Panel();
	
	public AparatZaTocenje(Sladoledzinica o) {
		setLayout(new BorderLayout());
		owner = o;
		populate();
	}

	private void populate() {
//EAST
		Panel east = new Panel();
		east.setLayout(new GridLayout(2, 1));
		east.setPreferredSize(new Dimension(owner.getWidth() / 3,owner.getHeight() * 3 / 4));
		prodaj.setEnabled(false);
		
		east.setBackground(Color.decode("#DDDEDF"));
		
		tocilica = new MestoZaTocenje(this, owner.getWidth() / 3,owner.getHeight() * 3 / 8);
		east.add(prodaj);
		east.add(tocilica);

		
		prodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prodaj.setEnabled(false);
				System.out.println(tocilica.getSladoled().toString());	
				tocilica.novo();
				sladoled.setText("");
				repaint();
			}
		});

//SOUTH
		Panel south = new Panel();
		south.setLayout(new FlowLayout(FlowLayout.LEFT));
		Label l1 = new Label("Sladoled: ");
		l1.setFont(new Font("Arial", Font.BOLD, 14));
		south.add(l1);
		south.add(sladoled);
		south.setBackground(Color.decode("#A9AFAF"));
		south.setPreferredSize(new Dimension(owner.getWidth(),owner.getHeight()/12));
		
		center.setBackground(Color.decode("#DDDEDF"));
		
		add(east,BorderLayout.EAST);
		add(south,BorderLayout.SOUTH);
		add(center,BorderLayout.CENTER);
	}
	
	public void dodajUkus(Ukus u) throws GUkus  {
		for (Button b : dugmici) {
			if (b.getLabel().equals(u.getNaziv())) throw new GUkus();
		}
		Button dugme = new Button(u.getNaziv());
		dugme.setBackground(u.getBoja());
		
		dugmici.add(dugme);
		int i = dugmici.size();
		
		if(i <= 2 ) {
			center.setLayout(new GridLayout(0, 1));
		}else if (i <= 4) {
			center.setLayout(new GridLayout(2, 2));
		}else if (i <= 6) {
			center.setLayout(new GridLayout(3, 2));
		}else {
			center.setLayout(new GridLayout(0, 3));
		}
		dugme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				tocilica.pauza();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				tocilica.pocni(u);
			}
			
		});
		
		center.add(dugme);
		center.revalidate();
	}

	public MestoZaTocenje getTocilica() {
		return tocilica;
	}
	

}
