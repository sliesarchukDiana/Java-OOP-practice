package org.animals;

import java.util.ArrayList;
import java.util.List;

public class AnimalShelter {
    private final List<Dog> dogs = new ArrayList<>();
    private final List<Animal> otherAnimals = new ArrayList<>();

    public void addDog (Dog dog){
        dogs.add(dog);
    }

    public void addOtherAnimals (Animal animal){
        otherAnimals.add(animal);
    }

    public void printAnimalSounds(List< ? extends Animal> animals){
        for (Animal animal : animals){
            animal.makeSound();
        }
    }

    public void showAll(){
        System.out.println("Who let the dogs out?");
        printAnimalSounds(dogs);
        System.out.println("Cats are also here!");
        printAnimalSounds(otherAnimals);
    }
}
