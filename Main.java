import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Main {
	public static void creaGUI(GestoreRubrica gr) {
		/*Crea il frame di base*/
		JFrame frame = new JFrame("Rubrica");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel tablePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		/*Crea la tabella*/
		ModelloTabella modello = new ModelloTabella(gr);
		JTable tabella = new JTable(modello);
		tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabella.setPreferredScrollableViewportSize(new Dimension(500,150));
		JScrollPane scrollPane = new JScrollPane(tabella);
		tablePanel.add(scrollPane);
		
		/*Crea i pulsanti*/
		JButton aggiungi = new JButton("Nuovo contatto");
		aggiungi.setPreferredSize(new Dimension(150,50));
		JButton modifica = new JButton("Modifica contatto");
		modifica.setPreferredSize(new Dimension(150,50));
		JButton elimina = new JButton("Elimina contatto");
		elimina.setPreferredSize(new Dimension(150,50));
		buttonPanel.add(aggiungi);
		buttonPanel.add(modifica);
		buttonPanel.add(elimina);
		
		ButtonActionNuovoContatto nuovoAction = new ButtonActionNuovoContatto(frame, gr, tabella);
		aggiungi.addActionListener(nuovoAction);
		ButtonActionCancellaContatto cancellaAction = new ButtonActionCancellaContatto(tabella, gr);
		elimina.addActionListener(cancellaAction);
		ButtonActionModificaContatto modificaAction = new ButtonActionModificaContatto(frame, tabella, gr);
		modifica.addActionListener(modificaAction);
		
		/*posiziona gli elementi e dimensiona il frame*/
		frame.add(tablePanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GestoreFile gf = new GestoreFile();
		File file = gf.getFile();
		GestoreRubrica gr = new GestoreRubrica(file);
		gr.aggiungiContattiDaFile();
		creaGUI(gr);
	}
}
