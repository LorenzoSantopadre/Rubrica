import java.util.StringJoiner;

public class Persona {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private int eta;
	
	/*costruttori*/
	public Persona(String nome, String cognome, String indirizzo, String telefono, int eta) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.eta = eta;
	}
	
	//costruttore per copiare una Persona in un nuovo oggetto Persona
	public Persona(Persona p) {
		this.nome = p.nome;
		this.cognome = p.cognome;
		this.indirizzo = p.indirizzo;
		this.telefono = p.telefono;
		this.eta = p.eta;
	}
	
	
	/*getters*/
	public String getNome() {return this.nome;}
	
	public String getCognome() {return this.cognome;}
	
	public String getIndirizzo() {return this.indirizzo;}
	
	public String getTelefono() {return this.telefono;}
	
	public int getEta() {return this.eta;}
	
	
	/*setters*/
	public void setNome(String nome) {this.nome = nome;}
	
	public void setCognome(String cognome) {this.cognome = cognome;}
	
	public void setIndirizzo(String indirizzo) {this.indirizzo = indirizzo;}
	
	public void setTelefono(String telefono) {this.telefono = telefono;}
	
	public void setEta(int eta) {this.eta = eta;}
	
	
	
	/*metodi*/
	public String personaToString() {
		StringJoiner sj = new StringJoiner(";");
		sj.add(this.nome).add(this.cognome).add(this.indirizzo).add(this.telefono).add(Integer.toString(this.eta));
		return sj.toString();
	}
}
