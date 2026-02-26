//Написати програму для визначення частоти кожного символу в рядку.
void countQuantity(String message){
     char[] charArray = message.toCharArray();
     boolean[] counted = new boolean[message.length()];
     for (int i = 0; i < charArray.length; i++) {
         if (counted[i]) continue;
         int charCount = 1;
         for (int j = i + 1; j < charArray.length; j++) {
             if (charArray[i] == charArray[j]) {
                 charCount++;
                 counted[j] = true;
             }
         }
                IO.println(charArray[i] + " : " + charCount);
     }
}

void main() {
    countQuantity("Hello World!");
    countQuantity("Java");

}