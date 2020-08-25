import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ModelloTabella extends AbstractTableModel implements TableModelListener{
	private String[] nomiColonne = {"Nome", "Cognome", "Telefono"};
	private GestoreRubrica gr;
	private List<String[]> data = new ArrayList<String[]>();
	
	/*crea la tabella con i dati già in memoria*/
	public ModelloTabella(GestoreRubrica gr) {
		this.gr = gr;
		this.addTableModelListener(this);
		
		Vector<Persona> v = gr.getListaContatti();
		
		for(Persona p : v) {
			String[] s = {p.getNome(), p.getCognome(), p.getTelefono()};
			data.add(s);
		}
	}
	
	/*metodi per aggiornare la tabella quando ci sono modifiche*/
	public void aggiungiRiga(GestoreRubrica gr) {
		Persona p = gr.getListaContatti().lastElement();
		String[] s = {p.getNome(), p.getCognome(), p.getTelefono()};
		data.add(s);
	}
	
	public void cancellaRiga(int contattoDaRimuovere) {
		data.remove(contattoDaRimuovere);
	}
	
	public void modificaRiga(int contattoDaModificare) {
		Persona p = gr.getListaContatti().elementAt(contattoDaModificare);
		String[] s = {p.getNome(), p.getCognome(), p.getTelefono()};
		data.remove(contattoDaModificare);
		data.add(contattoDaModificare, s);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getType() == TableModelEvent.INSERT)
			aggiungiRiga(this.gr);
		else if(e.getType() == e.DELETE) {
			cancellaRiga(e.getFirstRow());
		}
		else if(e.getType() == TableModelEvent.UPDATE) {
			modificaRiga(e.getFirstRow());
		}
	}

	@Override
	public int getColumnCount() {
		return nomiColonne.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int riga, int colonna) {
		return data.get(riga)[colonna];
	}
	
	@Override
	public String getColumnName(int colonna) {
		return nomiColonne[colonna];
	}

	
	
}
