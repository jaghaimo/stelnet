package stelnet.market.filter;

public interface Filter<T> {

    public boolean accept(T object);
}
