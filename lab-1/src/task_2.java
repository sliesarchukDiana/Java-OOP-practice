String isLongEnough (String password){
    if (password.length()>=8){
        return ("Password is appropriate!");
    }
    else return ("Password is too short!");
}

void main() {
    String wrongPassword1 = "Hello!", wrongPassword2 = "meow", rightPassword = "helloWorld";
    IO.println(isLongEnough(wrongPassword1));
    IO.println(isLongEnough(wrongPassword2));
    IO.println(isLongEnough(rightPassword));
}
