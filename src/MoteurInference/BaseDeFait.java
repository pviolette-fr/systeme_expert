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

public class BaseDeFait implements List<Fait> {

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

    @Override
    public Stream<Fait> parallelStream() {
        return m_content.parallelStream();
    }
    @Override
    public boolean removeIf(Predicate<? super Fait> arg0) {
        return m_content.removeIf(arg0);
    }
    @Override
    public Stream<Fait> stream() {
        return m_content.stream();
    }
    @Override
    public void forEach(Consumer<? super Fait> arg0) {
        m_content.forEach(arg0);
    }
    @Override
    public boolean add(Fait p) {
        return m_content.add(p);
    }
    @Override
    public void add(int index, Fait p) {
        m_content.add(index, p);
    }
    @Override
    public boolean addAll(Collection<? extends Fait> c) {
        return m_content.addAll(c);
    }
    @Override
    public boolean addAll(int index, Collection<? extends Fait> c) {
        return m_content.addAll(index, c);
    }
    @Override
    public void clear() {
        m_content.clear();
    }
    @Override
    public boolean contains(Object o) {
        return m_content.contains(o);
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return m_content.containsAll(c);
    }
    @Override
    public Fait get(int index) {
        return m_content.get(index);
    }
    @Override
    public int indexOf(Object o) {
        return m_content.indexOf(o);
    }
    @Override
    public boolean isEmpty() {
        return m_content.isEmpty();
    }
    @Override
    public Iterator<Fait> iterator() {
        return m_content.iterator();
    }
    @Override
    public int lastIndexOf(Object o) {
        return m_content.lastIndexOf(o);
    }
    @Override
    public ListIterator<Fait> listIterator() {
        return m_content.listIterator();
    }
    @Override
    public ListIterator<Fait> listIterator(int index) {
        return m_content.listIterator(index);
    }
    @Override
    public boolean remove(Object o) {
        return m_content.remove(o);
    }
    @Override
    public Fait remove(int o) {
        return m_content.remove(o);
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        return m_content.removeAll(c);
    }
    @Override
    public void replaceAll(UnaryOperator<Fait> arg0) {
        m_content.replaceAll(arg0);
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        return m_content.retainAll(c);
    }
    @Override
    public Fait set(int index, Fait p) {
        return m_content.set(index, p);
    }
    @Override
    public int size() {
        return m_content.size();
    }
    @Override
    public void sort(Comparator<? super Fait> arg0) {
        m_content.sort(arg0);
    }
    @Override
    public Spliterator<Fait> spliterator() {
        return m_content.spliterator();
    }
    @Override
    public List<Fait> subList(int fromIndex, int toIndex) {
        return m_content.subList(fromIndex, toIndex);
    }
    @Override
    public Object[] toArray() {
        return m_content.toArray();
    }
    @Override
    public <T> T[] toArray(T[] a) {
        return m_content.toArray(a);
    }

}
