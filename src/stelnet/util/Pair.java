package stelnet.util;

import lombok.Value;

@Value(staticConstructor = "of")
public class Pair<K, V> {

    private K key;
    private V value;
}
