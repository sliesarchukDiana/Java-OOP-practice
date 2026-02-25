int countOddSum (int edge){
    int sum = 0;
    if (edge >= 0){
        int i = 1;
        while (i <= edge){ // while (i < edge){
            sum += i;
            i+=2;
        }
    }
    else{
    int i = -1;
    while (i >= edge){   // while (i > edge){
        sum+= i;
        i-=2;
    }
    }
    return sum;
}

void main() {
    IO.println(countOddSum(0));
    IO.println(countOddSum(5));
    IO.println(countOddSum(-5));
    IO.println(countOddSum(9));
    IO.println(countOddSum(-10));
}