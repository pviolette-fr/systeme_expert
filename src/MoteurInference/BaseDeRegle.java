package MoteurInference;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;


public class BaseDeRegle implements List<Paquet>, JSONAware {

    static final String JSON_PAQUETS_KEY = "paquets";

	private List<Paquet> m_content;
	
	public BaseDeRegle(){
		m_content = new LinkedList<Paquet>();
	}
	
	public BaseDeRegle(Collection<Paquet> c){
		m_content = new LinkedList<Paquet>(c);
	}
	
	public static BaseDeRegle parseJSON(JSONObject json_bdr){
		
		BaseDeRegle result = new BaseDeRegle();
		
		for(Object obj_paquet : (JSONArray) json_bdr.get("paquets")){
			JSONObject json_paquet = (JSONObject) obj_paquet;
			
			try{
			result.add(Paquet.parseJSON(json_paquet));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public JSONObject toJSONObject(){
	    JSONObject json = new JSONObject();

	    JSONArray paquets = new JSONArray();

	    for(Paquet p : m_content){
	        paquets.add(p.toJSONObject());
        }

        json.put(JSON_PAQUETS_KEY, paquets);

        return json;
    }

    @Override
    public String toJSONString() {

        return toJSONObject().toString();
    }


	public String toString(){
		String res="";
		res+= "####################################################" + System.lineSeparator();
		for(Paquet p : this){
			res += p + System.lineSeparator();
		}
		res+= "####################################################";
		return res;
	}
	
	public Stream<Paquet> parallelStream() {
		return m_content.parallelStream();
	}

	public boolean removeIf(Predicate<? super Paquet> arg0) {
		return m_content.removeIf(arg0);
	}

	public Stream<Paquet> stream() {
		return m_content.stream();
	}

	public void forEach(Consumer<? super Paquet> arg0) {
		m_content.forEach(arg0);
	}

	public boolean add(Paquet p) {
		return m_content.add(p);
	}

	public void add(int index, Paquet p) {
		m_content.add(index, p);
	}

	public boolean addAll(Collection<? extends Paquet> c) {
		return m_content.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Paquet> c) {
		return m_content.addAll(index, c);
	}

	public void clear() {
		m_content.clear();
	}

	public boolean contains(Object o) {
		return m_content.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return m_content.containsAll(c);
	}

	public Paquet get(int index) {
		return m_content.get(index);
	}

	public int indexOf(Object o) {
		return m_content.indexOf(o);
	}

	public boolean isEmpty() {
		return m_content.isEmpty();
	}

	public Iterator<Paquet> iterator() {
		return m_content.iterator();
	}

	public int lastIndexOf(Object o) {
		return m_content.lastIndexOf(o);
	}

	public ListIterator<Paquet> listIterator() {
		return m_content.listIterator();
	}

	public ListIterator<Paquet> listIterator(int index) {
		return m_content.listIterator(index);
	}

	public boolean remove(Object o) {
		return m_content.remove(o);
	}

	public Paquet remove(int o) {
		return m_content.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return m_content.removeAll(c);
	}

	public void replaceAll(UnaryOperator<Paquet> arg0) {
		m_content.replaceAll(arg0);
	}

	public boolean retainAll(Collection<?> c) {
		return m_content.retainAll(c);
	}

	public Paquet set(int index, Paquet p) {
		return m_content.set(index, p);
	}

	public int size() {
		return m_content.size();
	}

	public void sort(Comparator<? super Paquet> arg0) {
		m_content.sort(arg0);
	}

	public Spliterator<Paquet> spliterator() {
		return m_content.spliterator();
	}

	public List<Paquet> subList(int fromIndex, int toIndex) {
		return m_content.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return m_content.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return m_content.toArray(a);
	}


}
