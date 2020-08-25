import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;

public class ButtonActionNuovoContatto implements ActionListener {
	private JFrame frame;
	private GestoreRubrica gr;
	private JTable tabella;
	
	public ButtonActionNuovoContatto (JFrame frame, GestoreRubrica gr, JTable tabella) {
		this.frame = frame;
		this.gr = gr;
		this.tabella = tabella;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FinestraEditor f = new FinestraEditor(frame, gr, tabella);
		f.setLocationRelativeTo(frame);
		f.pack();
		f.setVisible(true);		
	}
	
}
