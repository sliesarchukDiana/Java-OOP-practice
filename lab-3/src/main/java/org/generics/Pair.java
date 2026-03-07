package org.generics;

import java.util.Objects;

public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public U getSecond(){
        return second;
    }

    public boolean comparePair(Pair <T, U> otherPair){
        if (otherPair == null){
            return false;
        }
        boolean isFirstEqual = Objects.equals(this.first, otherPair.first);
        boolean isSecondEqual = Objects.equals(this.second, otherPair.second);
        return isFirstEqual && isSecondEqual;
    }

    @Override
    public String toString () {
        return "Pair {" + first + " " + second + "}";
    }
}
