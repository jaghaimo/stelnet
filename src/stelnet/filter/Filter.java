package stelnet.filter;

@Deprecated
public interface Filter<T> {
    public boolean accept(T object);
}
