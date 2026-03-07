package org.generics;

public class Box<T> {

    private T item;

    public void putItem(T item){
        this.item =  item;
    }

    public T getItem(T item){
        return item;
    }
}
