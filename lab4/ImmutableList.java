import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;

public class ImmutableList<T> {
	private final List<T> list;

	ImmutableList(List<T> list) {
		this.list = new ArrayList<T>(list);
	}

	@SafeVarargs
	ImmutableList(T... items) {
		list = new ArrayList<T>();
		for (T item : items) {
			this.list.add(item);
		}
	}

	public List<T> getList() {
		return this.list;
	}

	public ImmutableList<T> add(T item) {
		List<T> newList = new ArrayList<T>(this.list);
		newList.add(item);
		return new ImmutableList<T>(newList);
	}

	public ImmutableList<T> replace(T oldItem, T newItem) {
		List<T> newList = new ArrayList<T>(this.list);
		for (int i = 0; i < newList.size(); i++) {
			if (newList.get(i) == oldItem) {
				newList.set(i, newItem);
			}
		}
		return new ImmutableList<T>(newList);
	}

	public ImmutableList<T> remove(T item) {
		List<T> newList = new ArrayList<T>(this.list);
		for (int i = 0; i < newList.size(); i++) {
			if (newList.get(i) == item) {
				newList.remove(i);
				break;
			}
		}
		return new ImmutableList<T>(newList);
	}

	public ImmutableList<T> limit(int size) {
		List<T> newList = new ArrayList<T>();
		if (size < 0)
			throw new IllegalArgumentException("limit size < 0");
		for (int i = 0; i < size; i++) {

			if (i == this.list.size())
				break;

			newList.add(this.list.get(i));
		}
		return new ImmutableList<T>(newList);
	}

	public ImmutableList<T> sorted(Comparator<T> comparator) {
		if (comparator == null) {
			throw new NullPointerException("Comparator is null");
		}
		List<T> newList = new ArrayList<T>(this.list);
		newList.sort(comparator);
		return new ImmutableList<T>(newList);

	}

	public Object[] toArray() {
		return this.list.toArray();
	}

	public <E> E[] toArray(E[] array) {
		try {
			return list.toArray(array);
		} catch (NullPointerException e) {
			throw new NullPointerException("Input array cannot be null");
		} catch (Exception e) {
			throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
		}

	}

	public ImmutableList<T> filter(Predicate<? super T> predicate) {
		List<T> newList = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			if (predicate.test(this.list.get(i))) {
				newList.add(this.list.get(i));
			}
		}
		return new ImmutableList<T>(newList);
	}

	public <R> ImmutableList<R> map(Function<? super T, ? extends R> function) {
		List<R> newList = new ArrayList<R>();
		try {
			for (int i = 0; i < list.size(); i++) {
				newList.add(function.apply(list.get(i)));
			}
		} catch (IllegalArgumentException e) {
			System.err.println("IllegalArgumentException");
		}

		return new ImmutableList<R>(newList);
	}

	@Override
	public boolean equals(Object another) {
		if (this == another) {
			return true;
		} else if (another instanceof ImmutableList) {
			ImmutableList<?> otherImmutableList = (ImmutableList<?>) another;
			List<?> otherList = otherImmutableList.getList();
			if (otherList.size() == list.size()) {
				for (int i = 0; i < list.size(); i++) {
					if (!list.get(i).equals(otherList.get(i)))
						return false;
				}
				return true;
			}
		}

		return false;

	}

	@Override
	public String toString() {
		return this.list.toString();
	}

}
