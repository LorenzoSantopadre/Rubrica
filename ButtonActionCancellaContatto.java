import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

public class ButtonActionCancellaContatto implements ActionListener {
	private JTable tabella;
	private GestoreRubrica gr;
	
	public ButtonActionCancellaContatto(JTable tabella, GestoreRubrica gr) {
		this.tabella = tabella;
		this.gr = gr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int contattoDaCancellare = tabella.getSelectedRow(); 
		if(contattoDaCancellare == -1) {
			String messaggio = "Selezionare il contatto da eliminare";
			JOptionPane.showMessageDialog(new JFrame(), messaggio, "Errore!", JOptionPane.ERROR_MESSAGE);
		}
		else {
			String messaggio = "Eliminare la persona " + ((String)tabella.getValueAt(contattoDaCancellare, 0)).toUpperCase() + " " +
								((String)tabella.getValueAt(contattoDaCancellare, 1)).toUpperCase();
			int conferma = JOptionPane.showConfirmDialog(null, messaggio, "Conferma cancellazione", JOptionPane.YES_NO_OPTION);
			if(conferma == 0) {
				String telefono = (String) tabella.getValueAt(contattoDaCancellare, 2);
				gr.eliminaContatto(telefono);
				ModelloTabella modello = (ModelloTabella)tabella.getModel();
				TableModelEvent del = new TableModelEvent(modello, contattoDaCancellare, contattoDaCancellare, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
				modello.fireTableChanged(del);
			}
		}
	}

}
