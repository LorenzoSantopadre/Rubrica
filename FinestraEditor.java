import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;

public class FinestraEditor extends JDialog implements ActionListener {
	private GestoreRubrica gr;
	private JTable tabella;
	private int contattoDaModificare = -1;
	JTextField nome;
	JTextField cognome;
	JTextField indirizzo;
	JTextField telefono;
	JTextField eta;
	
	public FinestraEditor(JFrame parent, GestoreRubrica gr, JTable tabella) {
		super(parent, true);
		this.gr = gr;
		this.tabella = tabella;
		creaFinestra(parent, contattoDaModificare); 				
	}
	
	public FinestraEditor(JFrame parent, GestoreRubrica gr, JTable tabella, int contattoDaModificare) {
		super(parent, true);
		this.gr = gr;
		this.tabella = tabella;
		this.contattoDaModificare = contattoDaModificare;
		creaFinestra(parent, this.contattoDaModificare); 				
	}
	
	
	
	//uso la stringa per vedere se è un nuovo contatto o uno da modificare ??????
	private void creaFinestra(JFrame parent, int contatto) {
		setTitle("Editor dei contatti");
		
		/*crea le etichette e i campi modificabili*/
		nome = new JTextField(25);
		cognome = new JTextField(25);
		indirizzo = new JTextField(25);
		telefono = new JTextField(25);
		eta = new JTextField(25);
		JLabel lNome = new JLabel("Nome");
		lNome.setHorizontalAlignment(JLabel.CENTER);
		JLabel lCognome = new JLabel("Cognome");
		lCognome.setHorizontalAlignment(JLabel.CENTER);
		JLabel lIndirizzo = new JLabel("Indirizzo");
		lIndirizzo.setHorizontalAlignment(JLabel.CENTER);
		JLabel lTelefono = new JLabel("Telefono");
		lTelefono.setHorizontalAlignment(JLabel.CENTER);
		JLabel lEta = new JLabel("Età");
		lEta.setHorizontalAlignment(JLabel.CENTER);
		
		
		/*crea i bottoni e assegna la loro funzione*/
		JButton conferma = new JButton("Salva");
		JButton annulla = new JButton("Annulla");
		conferma.addActionListener(this);
		annulla.addActionListener(this);
		
		/*disponi le componenti nella finestra*/
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(5,2));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		textPanel.add(lNome);
		textPanel.add(nome);
		textPanel.add(lCognome);
		textPanel.add(cognome);
		textPanel.add(lIndirizzo);
		textPanel.add(indirizzo);
		textPanel.add(lTelefono);
		textPanel.add(telefono);
		textPanel.add(lEta);
		textPanel.add(eta);
		
		
		
		if(contatto != -1) {
			Persona p = gr.getListaContatti().get(contatto);
			nome.setText(p.getNome());
			cognome.setText(p.getCognome());
			indirizzo.setText(p.getIndirizzo());
			telefono.setText(p.getTelefono());
			eta.setText(String.valueOf(p.getEta()));
		}
		
		buttonPanel.add(conferma);
		buttonPanel.add(annulla);
		add(textPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/*funzionalità dei pulsanti*/			//da spostare in buttonactnuovo????
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Annulla"))
			dispose();
		else {
			Persona p = new Persona(nome.getText(), cognome.getText(),
									indirizzo.getText(), telefono.getText(),
									Integer.parseInt(eta.getText()));
			
			if(contattoDaModificare == -1) {
			gr.aggiungiContatto(p);
			ModelloTabella modello = (ModelloTabella)tabella.getModel();
			
			TableModelEvent ins = new TableModelEvent(modello, tabella.getRowCount(), 
													  tabella.getRowCount(),
													  TableModelEvent.ALL_COLUMNS, 
													  TableModelEvent.INSERT);
			modello.fireTableChanged(ins);
			}
			else {
				gr.modificaContatto(contattoDaModificare, p.getNome(),
									p.getCognome(), p.getIndirizzo(), 
									p.getTelefono(), p.getEta());
				
				ModelloTabella modello = (ModelloTabella)tabella.getModel();
				TableModelEvent ins = new TableModelEvent(modello, contattoDaModificare, 
														  contattoDaModificare,
						  								  TableModelEvent.ALL_COLUMNS, 
						  								  TableModelEvent.UPDATE);
				modello.fireTableChanged(ins);
			}
			dispose();
		}
	}

}
