import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ButtonActionModificaContatto implements ActionListener {
	private JTable tabella;
	private GestoreRubrica gr;
	private JFrame frame;
	
	public ButtonActionModificaContatto(JFrame frame, JTable tabella, GestoreRubrica gr) {
		this.tabella = tabella;
		this.gr = gr;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int contattoDaModificare = tabella.getSelectedRow();
		if(contattoDaModificare == -1) {
			String messaggio = "Selezionare il contatto da modificare";
			JOptionPane.showMessageDialog(new JFrame(),  messaggio, "Errore!", JOptionPane.ERROR_MESSAGE);
		}
		else {
			FinestraEditor f = new FinestraEditor(frame, gr, tabella, contattoDaModificare);
			f.setLocationRelativeTo(frame);
			f.pack();
			f.setVisible(true);	
		}
	}

}
