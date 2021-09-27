package metodos;

import java.util.Collections;
import java.util.Set;

import com.sun.tools.classfile.Annotation.element_value;

import estructurasDeDatos.Arista;
import estructurasDeDatos.GrafoConPesos;

public class KruskalBFS {

	static GrafoConPesos arbolGenerado ;
	
	// este metodo usa el algoritmo de kruskal con BFS para devolver un arbol de un grafo dado
	public static GrafoConPesos kruskal(GrafoConPesos g) {
		
		if(BFS.esConexo(g)) {
//			throw new RuntimeException("El grafo tiene que ser conexo");
			System.out.println("El grafo no es conexo!");
		}
		
		if(g.vertices() == 0) {
			return g;
		}
		
		arbolGenerado = new GrafoConPesos(g.vertices());
		
		int i = 0;
		int verticesDelGrafo=g.vertices();
		
		Set<Arista> aristasGrafo = (Set<Arista>) g.getAristas().clone();
		
		while (i<verticesDelGrafo-1) {
			Arista arista = dameMinimaNoConexa(aristasGrafo , arbolGenerado); //arista a agregar en el arbol
			if(arista!=null)
			arbolGenerado.agregarArista(arista.getA(), arista.getB(),arista.getPeso());//agrego la arista minima que no hace circuito al arbol
			
			i+=1; //manejo de indice
		}
		
		return arbolGenerado;
	}

	
	//recorro el grafo y elijo la arista minima que no hace circuito en el arbol
	public static Arista dameMinimaNoConexa(Set<Arista> aristasGrafo , GrafoConPesos arbol) {
		Arista temp = null; //inicio con la primer arista para comparar
		
		for (Arista arista : aristasGrafo) { //recorro el grafo
			if(temp == null) {
				temp = arista;
			}
			//si la arista es menor a la primera de las aristas del grafo y no hace circuito con otra arista del arbol, la elijo
			else if(arista.compareTo(temp)<0 && 
					!haceCircuito(arbol, arista.getA(), arista.getB())) {
				
				temp = arista;
			}
		}
		aristasGrafo.remove(temp); //remuevo la arista del set de aristas del grafo
		return temp;
	}
	
	//chequeo de si una arista hace circuito en un grafo
	public static boolean haceCircuito(GrafoConPesos g , int origen ,int destino) {
		Set<Integer> alcanzables =  BFS.alcanzables(g, origen);
		return alcanzables.contains(destino);
	}
}
