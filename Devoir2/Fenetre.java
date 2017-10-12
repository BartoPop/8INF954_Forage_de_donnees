package devoir2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//Les imports habituels

public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	JFormattedTextField jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel label = new JLabel("Indiquez la valeur de minsup en %");
	private JRadioButton jr1 = new JRadioButton("Standard Overlap");
	private JRadioButton jr2 = new JRadioButton("Entropie Overlap");
	private JRadioButton ftc = new JRadioButton("FTC");
	

			private JRadioButton hftc = new JRadioButton("HFTC");
			private ButtonGroup bg = new ButtonGroup();
			private ButtonGroup bg1 = new ButtonGroup();

			Boolean overlap=false,ftcbool=false;
			JButton b = new JButton ("OK");
			JFileChooser chooser=new JFileChooser();
			String choosertitle;
			JPanel top = new JPanel();
			public Fenetre(){
				this.setTitle("Forage de données: Devoir 2 ");
				this.setSize(300, 300);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setLocationRelativeTo(null);
				container.setBackground(Color.white);
				container.setLayout(new BorderLayout());

				Font police = new Font("Arial", Font.BOLD, 14);
				jtf.setFont(police);
				jtf.setPreferredSize(new Dimension(150, 30));
				jtf.setForeground(Color.BLUE);
				b.addActionListener(new BoutonListener());
				jr1.addActionListener(new StateListener());
				jr2.addActionListener(new StateListener());
				bg.add(jr1);
				bg.add(jr2);
				bg1.add(ftc);
				bg1.add(hftc);
				chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("c:\\"));
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				//    
				if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
					System.out.println("getSelectedFile() : " 
							+  chooser.getSelectedFile());
				}
				else {
					System.out.println("No Selection ");
				}

				top.add(label);
				top.add(jtf);
				top.add(b);

				top.add(jr1);
				top.add(jr2);
				top.add(ftc);
				top.add(hftc);
				
				this.setContentPane(top);
				this.setVisible(true);            
			}    
			public void add(ArrayList<JLabel> label){
				for(JLabel element:label){
					top.add(element);}
			}

			class BoutonListener implements ActionListener{
				Boolean ready=false;
				public void actionPerformed(ActionEvent e) {
					ready=true;

				}
			}
			class StateListener implements ActionListener {
				public void actionPerformed(ActionEvent arg0) {

					if(jr2.isSelected()){
						overlap=true;
					}
					if(hftc.isSelected()){
						ftcbool=true;
					}

				}
			}
}
