package com.axiante.tm1.json.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class Row<T extends Cell> implements List<T>{
	@Getter(value = AccessLevel.PROTECTED)
	private List<T> _list ;
	@Setter
	@Getter
	private Integer rowIndex = -1;
	@Getter
	private String rowKey;

	@Getter
	private static final Row<Cell> emptyRow = new Row<Cell>(true);
	
	public Row() {
		this._list = Collections.synchronizedList(new ArrayList<>());
	}
	
	public Row(int cardinality) {
		this._list = Collections.synchronizedList(new ArrayList<>(cardinality));
	}
	public boolean isHeader() {
		boolean ret = true;
		if (get_list() != null) {
			ret = get_list().parallelStream().anyMatch(Cell::getIsHeader);
		}
		return ret && this.rowIndex == -1;
	}
	
	@Override
	public int size() {
		return get_list().size();
	}

	@Override
	public boolean isEmpty() {
		return get_list().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return get_list().contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return get_list().iterator();
	}

	@Override
	public Object[] toArray() {
		return get_list().toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return get_list().toArray(a);
	}

	@Override
	public boolean add(T e) {
		return get_list().add(e);
	}

	@Override
	public boolean remove(Object o) {
		return get_list().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return get_list().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return get_list().addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if ( c != null ) {
			boolean result = get_list().addAll(index, c);
			// reset the cell indexes
			final AtomicInteger i = new AtomicInteger(-1);
			get_list().forEach(cell->{
				cell.setColumPosition(i.incrementAndGet());
			});
			return result;
		} else { 
			log.debug("no data in variable");
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return get_list().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return get_list().retainAll(c);
	}

	@Override
	public void clear() {
		get_list().clear();
	}

	@Override
	public T get(int index) {
		return get_list().get(index);
	}

	@Override
	public T set(int index, T element) {
		return get_list().set(index, element);
	}

	@Override
	public void add(int index, T element) {
		get_list().add(index,element);
	}

	@Override
	public T remove(int index) {
		return get_list().remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return get_list().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return get_list().lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return get_list().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return get_list().listIterator();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return get_list().subList(fromIndex, toIndex);
	}

	public void generateRowKey(final int numberOfColumnsToUse, final ObjectMapper mapper) {
		final StringBuilder builder = new StringBuilder("{")   ;
		if ( numberOfColumnsToUse > size() ) {
			this.rowKey="error generating key";
		} else { 
			stream().limit(numberOfColumnsToUse).forEach(c->{
				try {
					builder.append(mapper.writeValueAsString(c.getName())).append(":").append(mapper.writeValueAsString(c.getValue())).append(",");
				} catch (JsonProcessingException e) {
					log.error("error generating rowKey", e);
				}		
			});
			if (builder.length()>1)
				builder.deleteCharAt(builder.length()-1);
			builder.append("}");
			this.rowKey = builder.toString();
		}
	}
	@SuppressWarnings("unchecked")
	public void ensureCapacity(@NonNull Integer capacity) {
		if ( this.size() < capacity) {
			for(int index = this.size(); index < capacity ; ++ index) {
				add((T) Table.PAD);
			}
		}
	}
	
	private Row(Boolean unmodifiable) {
		this._list = Collections.unmodifiableList(new ArrayList<T>());
	}
}
