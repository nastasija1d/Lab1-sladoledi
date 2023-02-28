package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sladoledzinica extends Frame {

	AparatZaTocenje aparat;
	Button dodajUkus = new Button("Dodaj ukus");
	Button prodaj = new Button("Prodaj");
	
	public Sladoledzinica() {
		setBounds(700, 200, 450, 480);
		setTitle("Sladoledzinica");
		setResizable(false);
		
		populate1();
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			dispose();
			aparat.getTocilica().stani();
		}
		});
	}
	

	
	private void populate1() {
		
//JUZNI DEO ZA DODAVANJE NOVOG UKUSA SLADOLEDA
		
		TextField nazivUkusa = new TextField(6);
		TextField nazivBoje = new TextField(6);
		
		Panel noviUkus = new Panel();
		noviUkus.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		Label l1 = new Label("Naziv:");
		l1.setFont(new Font("Arial", Font.BOLD, 14));
		noviUkus.add(l1);
		noviUkus.add(nazivUkusa);
		
		Label l2 = new Label("Boja:");
		l2.setFont(new Font("Arial", Font.BOLD, 14));
		noviUkus.add(l2);
		noviUkus.add(nazivBoje);
		noviUkus.add(dodajUkus);
		noviUkus.setBackground(Color.decode("#29EFEF"));
		noviUkus.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/12));
		
		dodajUkus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = nazivUkusa.getText();
				String boja = nazivBoje.getText();
				boja = "#" + boja;
				nazivUkusa.setText("");
				nazivBoje.setText("");
				Ukus u = new Ukus(naziv, Color.decode(boja));
				try {
					aparat.dodajUkus(u);
				} catch (GUkus e1) {
					System.out.println(e1);					
				}
			}
		});
		add(noviUkus, BorderLayout.SOUTH);	
		aparat = new AparatZaTocenje(this);
		add(aparat, BorderLayout.CENTER);

	}
	

	
//MAIN	
	public static void main (String[] arg) {
		new Sladoledzinica();
	}
}
