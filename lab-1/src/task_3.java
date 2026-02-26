//Написати програму для друку назви місяця на основі введеного
//користувачем числа (1-12)
String convertValToMonth(int val){
    return switch (val) {
        case 1 -> ("January");
        case 2 -> ("February");
        case 3 -> ("March");
        case 4 -> ("April");
        case 5 -> ("May");
        case 6 -> ("June");
        case 7 -> ("July");
        case 8 -> ("August");
        case 9 -> ("September");
        case 10 -> ("October");
        case 11 -> ("November");
        case 12 -> ("December");
        default -> ("Not a month.");
    };
}

void main() {
    IO.println(convertValToMonth(1));
    IO.println(convertValToMonth(2));
    IO.println(convertValToMonth(5));
    IO.println(convertValToMonth(9));
    IO.println(convertValToMonth(32));
}