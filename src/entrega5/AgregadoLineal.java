package entrega5;
import java.util.List;
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
	public void remove(E e) {
        this.elementos.remove(e);
    }
	public void removeAll(List<E> lista) {
        this.elementos.clear();
  
    }
}
