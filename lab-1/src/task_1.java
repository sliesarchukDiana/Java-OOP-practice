//Написати програму для заміни двох чисел за допомогою тимчасової змінної.
void main() {
    int firstVal = 5, secondVal = 56;
    IO.println(firstVal + " " + secondVal);
    var temp = firstVal;
    firstVal = secondVal;
    secondVal = temp;
    IO.println(firstVal + " " + secondVal);
}


