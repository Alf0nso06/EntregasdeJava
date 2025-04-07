package entrega5;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public abstract class AgregadoLineal<E> {
	protected List<E> elementos;
	public AgregadoLineal() {
        this.elementos = new ArrayList<>();
    }
	
	public Integer size() {
		return this.elementos.size();
	}
	public boolean isEmpty() {
		return this.elementos.isEmpty();
		
	}
	public List<E> elementos(){
		return this.elementos;
	}
	public abstract void add(E e) ;
		
	
	public void addAll(List<E> lista) {
		for(E i : lista) {
			this.add(i);
		}
	}
	public E remove() {
        if (isEmpty()) throw new NoSuchElementException("No se puede eliminar de un agregado vacío.");
        return elementos.remove(0);
	}
	public void removeAll(List<E> lista) {
        this.elementos.clear();
  
    }
}
