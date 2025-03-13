package examenes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Defensadeentrega1 {
	
	
	public static Double ProductoDeImpares(Double n) {
		Double solucion = 1.;
		if(n<=0) {
			throw new  IllegalArgumentException("n tiene que ser mayor que 0");
		}
		
		for (Double i = 1.; i <= n; i++) {
			if(i%2!=0) {
			solucion = solucion *i;
			}

		}
		return solucion;
	}
	
	
	
	
	public static Double sumaGeometricaAlternada(Double a1, Double r, Double k) {
		Double solucion = 0.;
		if(k<=0) {
			throw new  IllegalArgumentException("𝑘 debe ser mayor que 0");
			
		}
		if(a1>0&&r>0) {
		for (Double i = 1.; i <= k; i++) {
			solucion = solucion + Math.pow(-1, i)*a1*Math.pow(r, i-1) ;

		}
		}
		return solucion;
	    }
	//he creado este metodo para calcular el factorial de un numero que usare mas adelante
		public static Double Factorial(Integer k) {
			Double solucion = 1.;
			for (Double i = 1.; i <= k; i++) {
				solucion = solucion * i;
			}

			return solucion;
		}
	
		public static boolean esMultiploDeTres(Integer num) {
	        return num % 3 == 0;
		}
	        
	    public static Double combinatorioSinMultiplosDeTres(Integer n,Integer k) {
	    	if (n < k || n < 0 || k < 0) {
	            throw new IllegalArgumentException("n debe ser mayor o igual a k, y ambos deben ser positivos.");
	        }
	        if (esMultiploDeTres(n) || esMultiploDeTres(k) || esMultiploDeTres(n - k)) {
	            return 0.0;
	        }
	        Double numerador = Factorial(n);
	        Double denominador = Factorial(k) * Factorial(n - k);
	        return numerador / denominador;
	    }
	    
	        
	    
	    
	    public static List<String> filtrarLineasConsecutivas(String archivo, List<String> palabras) {
	        List<String> resultado = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] palabrasLinea = linea.split(" ");
	                for (int i = 0; i < palabrasLinea.length - 1; i++) {
	                    if (palabras.contains(palabrasLinea[i]) && palabras.contains(palabrasLinea[i + 1])) {
	                        resultado.add(linea);
	                        break;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	        return resultado;
	    }
	    
	    
	    
	    
	        
	public static void main(String[] args) {
		System.out.println(ProductoDeImpares(5.));
		//System.out.println(ProductoDeImpares(-5.));  //Esto es para comprobar el Argument error
		System.out.println("-----------------");
		System.out.println(sumaGeometricaAlternada(1.,2.,3.));
		//System.out.println(sumaGeometricaAlternada(1.,2.,-3.));  //Esto es para comprobar el Argument error
		System.out.println("-----------------");
		//System.out.println(combinatorioSinMultiplosDeTres(-3,4));  //Esto es para comprobar el Argument error
		System.out.println(combinatorioSinMultiplosDeTres(5,4));
		System.out.println(combinatorioSinMultiplosDeTres(5,2));
		System.out.println(combinatorioSinMultiplosDeTres(10,5));
		System.out.println("-----------------");
		
		
		
		
	}
	
	
}
