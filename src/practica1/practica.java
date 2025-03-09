package practica1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class practica {

	public static Integer Nose(Integer n, Integer k) {
		Integer solucion = 1;
		for (int i = 0; i <= k - 1; i++) {
			solucion = solucion * (n - i + 1);

		}
		return solucion;

	}

	public static Double Funcion2(Double a1, Double r, Double n) {
		Double solucion = 1.;
		for (Double i = 1.; i <= n; i++) {
			solucion = solucion * (a1 * Math.pow(r, i - 1));

		}
		return solucion;
	}

	public static Integer Factorial(Integer k) {
		Integer solucion = 1;
		for (int i = 1; i <= k; i++) {
			solucion = solucion * i;
		}

		return solucion;
	}

	public static Integer Funcion3(Integer n, Integer k) {
		if (k <= n) {
			return Factorial(n) / (Factorial(k) * Factorial(n - k));
		} else {
			return 0;
		}

	}

	public static Double S(Integer n, Integer k) {
		Double solucion = 0.;
		for (Integer i = 0; i <= k - 1; i++) {
			solucion = solucion + (Math.pow(-1, i) * Funcion3(k + 1, i + 1) * Math.pow(k - i, n));
		}
		return solucion / Factorial(k);
	}
	
	
	public static Double Funcion5(Function<Double,Double> F,Function<Double,Double> FI , Double a,Double e) {
		Double x=a;
		while(Math.abs(F.apply(x)) > e){
			x = x - ((F.apply(x))/(FI.apply(x)));
		}
		return x;
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		System.out.println(Nose(4,2));
		System.out.println(Funcion2(3.,5.,2.));
		System.out.println(Factorial(5));
		System.out.println(Funcion3(4,2));
		System.out.println(S(4,2));
		System.out.println(Funcion5(x->2*Math.pow(x, 2),i->4*i,3.,0.001));
		
	}
}
	






























