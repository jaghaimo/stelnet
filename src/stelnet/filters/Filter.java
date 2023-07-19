package stelnet.filters;

public interface Filter<T> {
    public boolean accept(T object);
}
