package org.animals;

public class MainAnimalShelter {
    public static void main(){
        AnimalShelter animalShelter = new AnimalShelter();
        Dog mutt = new Dog();
        Labrador labrador = new Labrador();
        Cat kitty = new Cat();

        animalShelter.addDog(mutt);
        animalShelter.addDog(labrador);
        animalShelter.addOtherAnimals(kitty);

        animalShelter.showAll();
    }
}
