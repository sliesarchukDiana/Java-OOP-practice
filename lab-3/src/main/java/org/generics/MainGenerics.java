package org.generics;

public class MainGenerics {
    public static <T extends Comparable<T>> T findMax(T[] array){
        if (array == null || array.length == 0){
            return null;
        }
        T max = array[0];
        for (int i = 1; i < array.length; i++){
            if(array[i].compareTo(max) > 0){
                max = array[i];
            }
        }
        return max;
    }

    static void main(){
        Box<String> stringBox = new Box<>();
        stringBox.putItem("Hi from inside!");
        System.out.println("Message from the box: " + stringBox.getItem());

        Box<Integer> integerBox = new Box<>();
        integerBox.putItem(67);
        System.out.println("The number in the box is " + integerBox.getItem());

        Integer[] intArray = {1, 2, 3, 9, 5, 3, 7, 6};
        Double[] doubleArray = {1.34, 5.67, 8.13, 9.67};
        Character[] charArray = {'a', 'b', 'c', 'd'};
        System.out.println("The max among integers is " + findMax(intArray));
        System.out.println("The max among doubles is " + findMax(doubleArray));
        System.out.println("The max among chars is " + findMax(charArray));
    }
}
