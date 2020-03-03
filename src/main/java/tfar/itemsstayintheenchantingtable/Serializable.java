package tfar.itemsstayintheenchantingtable;

public interface Serializable<T> {
	T serialize();
	void deserialize(T t);
}
