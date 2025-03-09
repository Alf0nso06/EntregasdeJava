package practica1;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Leerfichero {
	//Funcione sacadas de la clase File 2
	public static List<String> lineasDeFichero(String file) {
		Charset charSet = Charset.defaultCharset();
		return lineasDeFichero(file, charSet.toString());
	}
	
	//Funcion sacadad de File 2
	public static List<String> lineasDeFichero(String file, String charSet) {
		List<String> lineas = null;
		try {		
			lineas = Files.readAllLines(Paths.get(file), Charset.forName(charSet));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return lineas;
	}
	
	public static Integer LeerPalabras(String sep,String file , String cad) {
		List<String> lis = new ArrayList<>();
		for(String linea:lineasDeFichero(file)) {
			for(String p:linea.split(sep)) {
				if(p.equals(cad)) {
					lis.add(p);
				}
			}
		}
		return lis.size();
	}
	
	public static List<String> Contiene(String cad, String file) {
		List<String> ls = new ArrayList<>();
		for (String linea : lineasDeFichero(file)) {

			if (linea.contains(cad)) {
				ls.add(linea);

			}
		}
		return ls;
	}
	
	public static List<String> ContarPalabrasUnicas(String file)  {
		List<String> lis = new ArrayList<>();
		for(String linea:lineasDeFichero(file)) {
			for(String p:linea.split(" ")) {
				if(!lis.contains(p)) {
					lis.add(p);
				}
			}
		}	
		return lis;
	}
	public static Double MediaDeLasLineas(String file,String sep) {
		Double contador1 =0.;
		
		Integer tamaño = 0;
		for(String lineas:lineasDeFichero(file)) {
			contador1++;
			
			for(String p:lineas.split(sep)) {
				tamaño+=p.length();
				
			}
			
		}
		return tamaño/contador1;
	}
	
	
	
	public static void main(String[] args) {

		
		System.out.println(LeerPalabras(" ","src/practica1/lin_quijote.txt","Quijote"));
		System.out.println(Contiene("Quijote","src/practica1/lin_quijote.txt"));
		System.out.println(ContarPalabrasUnicas("src/practica1/lin_quijote.txt"));
		System.out.println(MediaDeLasLineas("src/practica1/palabras_random.csv",","));
	}
}
	
