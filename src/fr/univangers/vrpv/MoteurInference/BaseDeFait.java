package fr.univangers.vrpv.MoteurInference;


import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

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

public class BaseDeFait implements List<Fait>, JSONAware {

    static final String JSON_BDF_LIST_FACTS_KEY = "facts";

    List<Fait> m_content;

    public BaseDeFait() {
        m_content = new LinkedList<Fait>();
    }

    public BaseDeFait(Collection<Fait> c) {
        m_content = new LinkedList<Fait>(c);
    }

    public String toString(){
        String bdf="";

        for(Fait fait : m_content){

            bdf+=fait.toString()+System.lineSeparator();

        }
        return bdf;
    }

    public static BaseDeFait parseJSON(JSONObject obj){
        JSONArray json_listFait = (JSONArray) obj.get(JSON_BDF_LIST_FACTS_KEY);
        BaseDeFait bdf = new BaseDeFait();
        for(Object o : json_listFait){
            JSONObject f = (JSONObject) o;
            bdf.add(Fait.parseJSON(f));
        }
        return bdf;
    }

    public JSONObject toJSONObject(){
        JSONObject res = new JSONObject();
        JSONArray faits = new JSONArray();

        for(Fait f : this){
            faits.add(f.toJSONObject());
        }

        res.put(JSON_BDF_LIST_FACTS_KEY, faits);

        return res;
    }

    @Override
    public String toJSONString() {
        return toJSONObject().toJSONString();
    }

    public Stream<Fait> parallelStream() {
        return m_content.parallelStream();
    }
     
    public boolean removeIf(Predicate<? super Fait> arg0) {
        return m_content.removeIf(arg0);
    }
     
    public Stream<Fait> stream() {
        return m_content.stream();
    }
     
    public void forEach(Consumer<? super Fait> arg0) {
        m_content.forEach(arg0);
    }
     
    public boolean add(Fait p) {
        return m_content.add(p);
    }
     
    public void add(int index, Fait p) {
        m_content.add(index, p);
    }
     
    public boolean addAll(Collection<? extends Fait> c) {
        return m_content.addAll(c);
    }
     
    public boolean addAll(int index, Collection<? extends Fait> c) {
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
     
    public Fait get(int index) {
        return m_content.get(index);
    }
     
    public int indexOf(Object o) {
        return m_content.indexOf(o);
    }
     
    public boolean isEmpty() {
        return m_content.isEmpty();
    }
     
    public Iterator<Fait> iterator() {
        return m_content.iterator();
    }
     
    public int lastIndexOf(Object o) {
        return m_content.lastIndexOf(o);
    }
     
    public ListIterator<Fait> listIterator() {
        return m_content.listIterator();
    }
     
    public ListIterator<Fait> listIterator(int index) {
        return m_content.listIterator(index);
    }
     
    public boolean remove(Object o) {
        return m_content.remove(o);
    }
     
    public Fait remove(int o) {
        return m_content.remove(o);
    }
     
    public boolean removeAll(Collection<?> c) {
        return m_content.removeAll(c);
    }
     
    public void replaceAll(UnaryOperator<Fait> arg0) {
        m_content.replaceAll(arg0);
    }
     
    public boolean retainAll(Collection<?> c) {
        return m_content.retainAll(c);
    }
     
    public Fait set(int index, Fait p) {
        return m_content.set(index, p);
    }
     
    public int size() {
        return m_content.size();
    }
     
    public void sort(Comparator<? super Fait> arg0) {
        m_content.sort(arg0);
    }
     
    public Spliterator<Fait> spliterator() {
        return m_content.spliterator();
    }
     
    public List<Fait> subList(int fromIndex, int toIndex) {
        return m_content.subList(fromIndex, toIndex);
    }
     
    public Object[] toArray() {
        return m_content.toArray();
    }
     
    public <T> T[] toArray(T[] a) {
        return m_content.toArray(a);
    }
}
