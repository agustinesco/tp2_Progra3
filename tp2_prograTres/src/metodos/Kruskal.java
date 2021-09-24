package metodos;

import java.util.Set;

import estructurasDeDatos.Arista;
import estructurasDeDatos.GrafoConPesos;

public class Kruskal {

	static GrafoConPesos arbolGenerado ;
	
	// este metodo usa el algoritmo de kruskal con BFS para devolver un arbol de un grafo dado
	public static GrafoConPesos kruskal(GrafoConPesos g) {
		
		if(g.vertices() == 0) {
			return g;
		}
		
		arbolGenerado = new GrafoConPesos(g.vertices());
		
		int i = 0;
		while (i<g.vertices()-1) {
			Arista arista = dameMinimaNoConexa(g , arbolGenerado); //arista a agregar en el arbol
			
			arbolGenerado.setArista(arista.getA(), arista.getB(),arista.getPeso());//agrego la arista minima que no hace circuito al arbol
			
			i+=1; //manejo de indice
		}
		
		return arbolGenerado;
	}

	
	//recorro el grafo y elijo la arista minima que no hace circuito en el arbol
	public static Arista dameMinimaNoConexa(GrafoConPesos grafo , GrafoConPesos arbol) {
		Arista temp = grafo.dameAristaMaxima(); //inicio con la primer arista para comparar
		for (Arista arista : grafo.getAristas()) { //recorro el grafo
			//si la arista es menor a la maxima del grafo y no hace circuito con otra arista del
			//arbol, la elijo
			if(arista.compareTo(temp)<0 && !haceCircuito(arbol, arista.getA(), arista.getB())) 
				temp = arista;
			
		}
		return temp;
	}
	
	//chequeo de si una arista hace circuito en un grafo
	public static boolean haceCircuito(GrafoConPesos g , int origen ,int destino) {
		Set<Integer> alcanzables =  BFS.alcanzables(g, origen);
		return alcanzables.contains(destino);
	}
}