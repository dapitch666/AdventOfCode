package org.anne.common;

/*
    A Pair of objects where the order of the objects does not matter.
 */
public record Pair<T>(T first, T second) {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair<?> other) {
            return (first.equals(other.first) && second.equals(other.second)) ||
                    (first.equals(other.second) && second.equals(other.first));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }
}
