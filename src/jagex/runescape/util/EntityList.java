package jagex.runescape.util;

import jagex.runescape.model.Entity;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EntityList.java
 * 
 * @author I don't know who owns this Aug 21, 2012
 */
public final class EntityList<T extends Entity> implements Iterable<T> {

    private final Entity[] entitys;

    private int size = 0;

    private int pointer = 0;

    public EntityList(int capacity) {
	this.entitys = new Entity[capacity];
    }

    public int size() {
	return size;
    }

    public int capacity() {
	return entitys.length;
    }

    public boolean add(T entity) {
	if (size == entitys.length) {
	    return false;
	}
	int index = -1;
	for (int i = pointer; i < entitys.length; ++i) {
	    if (entitys[i] == null) {
		index = i;
		break;
	    }
	}
	if (index == -1) {
	    for (int i = 0; i < pointer; ++i) {
		if (entitys[i] == null) {
		    index = i;
		    break;
		}
	    }
	}
	if (index == -1) {
	    return false;
	}
	entitys[index] = entity;
	entity.setIndex(index + 1);
	if (index == (entitys.length - 1)) {
	    pointer = 0;
	} else {
	    pointer = index;
	}
	++size;
	return true;
    }

    public boolean remove(T character) {
	int index = character.getIndex() - 1;
	if (index < 0 || index >= entitys.length) {
	    return false;
	}
	if (entitys[index] == character) {
	    entitys[index] = null;
	    character.setIndex(-1);
	    --size;
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public Iterator<T> iterator() {
	return new CharacterRepositoryIterator();
    }

    private final class CharacterRepositoryIterator implements Iterator<T> {

	private int previousIndex = -1;

	private int index = 0;

	@Override
	public boolean hasNext() {
	    for (int i = index; i < entitys.length; ++i) {
		if (entitys[i] != null) {
		    index = i;
		    return true;
		}
	    }
	    return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
	    T character = null;
	    for (int i = index; i < entitys.length; ++i) {
		if (entitys[i] != null) {
		    character = (T) entitys[i];
		    index = i;
		    break;
		}
	    }
	    if (character == null) {
		throw new NoSuchElementException();
	    }
	    previousIndex = index;
	    ++index;
	    return character;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove() {
	    if (previousIndex == -1) {
		throw new IllegalStateException();
	    }
	    EntityList.this.remove((T) entitys[previousIndex]);
	    previousIndex = -1;
	}

    }

}