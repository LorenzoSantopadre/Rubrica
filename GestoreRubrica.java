import java.io.File;
import java.util.Vector;

public class GestoreRubrica {
	private Vector<Persona> listaContatti;
	private File file;
	private GestoreFile gf;
	
	public GestoreRubrica(File file) {
		this.listaContatti = new Vector<>();
		this.file = file;
		this.gf = new GestoreFile();
	}
	
	
	public void aggiungiContatto(Persona p) {
		Persona nuovoContatto = new Persona(p);
		this.listaContatti.add(nuovoContatto);
		gf.scriviSuFile(file, listaContatti);
	}
	
	
	public void modificaContatto(int posizione, String nome, String cognome, String indirizzo, String telefonoNew, int eta) {
		Persona personaDaModificare = this.listaContatti.get(posizione);
		personaDaModificare.setNome(nome);
		personaDaModificare.setCognome(cognome);
		personaDaModificare.setIndirizzo(indirizzo);
		personaDaModificare.setTelefono(telefonoNew);
		personaDaModificare.setEta(eta);
		gf.scriviSuFile(file, listaContatti);
	}
	
	
	public void eliminaContatto(String telefono) {
		Persona personaDaEliminare = this.listaContatti.stream().filter(p -> p.getTelefono().equals(telefono)).findFirst().get();
		this.listaContatti.remove(personaDaEliminare);
		gf.scriviSuFile(file, listaContatti);
	}
	

	public Vector<Persona> getListaContatti(){
		return this.listaContatti;
	}
	
	public void aggiungiContattiDaFile() {
		GestoreFile gf = new GestoreFile();
		Vector<Persona> v = gf.leggiFile(file);
		for(Persona p : v)
			aggiungiContatto(p);
	}
	
	
	public void stampaListaContatti() {
		for(Persona p : this.listaContatti){
			System.out.println(p.personaToString());
		}
	}
	
	
	
}
