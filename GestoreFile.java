import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class GestoreFile {
	public File getFile() {
		File file = new File("informazioni.txt");
		try {
			file.createNewFile();
		}
		catch(Exception e) {
			System.err.println(e);
		}
		return file;
	}
	
	public Vector<Persona> leggiFile(File file){
		Vector<Persona> v = new Vector<Persona>();
		
		try (Scanner fileScanner = new Scanner(file)){
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				Scanner lineScanner = new Scanner(line).useDelimiter("\\s*;\\s*");
				while(lineScanner.hasNext()) {
					String nome = new String(lineScanner.next());
					String cognome = new String(lineScanner.next());
					String indirizzo = new String(lineScanner.next());
					String telefono = new String(lineScanner.next());
					int eta = Integer.parseInt(lineScanner.next());
					Persona p = new Persona(nome, cognome, indirizzo, telefono, eta);
					v.add(p);
				}
				lineScanner.close();
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return v;
	}
	
	public void scriviSuFile(File file, Vector<Persona> v) {
		FileOutputStream  outputStream = null;
		PrintStream printStream = null;
		try {
			outputStream = new FileOutputStream(file);
			printStream = new PrintStream(outputStream);
			
			for(Persona p : v) {
				printStream.println(p.personaToString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(outputStream!=null)
					outputStream.close();
				if(printStream!=null)
					printStream.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
