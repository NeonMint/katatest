import jdk.jshell.Snippet;
import java.util.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;


public class TestCalc {
    public static Map<String, Integer> romanMap = new HashMap<>();

    static { // нельзя просто пихать метод посреди класса. Вот это статический блок инициализации. Выполняется до конструктора, готов к работе
        romanMap.put("I", 1);
        romanMap.put("II", 2);
        romanMap.put("III", 3);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("VI", 6);
        romanMap.put("VII", 7);
        romanMap.put("VIII", 8);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);

//        for (Map.Entry m : romanMap.entrySet()) return;
    }

    public static TreeMap<Integer, String> arabMap = new TreeMap<>();

    static {
        arabMap.put(1, "I");
        arabMap.put(2, "II");
        arabMap.put(3, "III");
        arabMap.put(4, "IV");
        arabMap.put(5, "V");
        arabMap.put(6, "VI");
        arabMap.put(7, "VII");
        arabMap.put(8, "VIII");
        arabMap.put(9, "IX");
        arabMap.put(10, "X");
        arabMap.put(20, "XX");
        arabMap.put(30, "XXX");
        arabMap.put(40, "XL");
        arabMap.put(50, "L");
        arabMap.put(60, "LX");
        arabMap.put(70, "LXX");
        arabMap.put(80, "LXXX");
        arabMap.put(90, "XC");
        arabMap.put(100, "C");
    }
    // попытка сравнить Map через equals
    //   for(romanMap.get )

    public static void main(String[] args) {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // просто нравится :)
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите математическое выражение с 1 до 10 или с I до X: ");
        String exp = scan.nextLine();
        System.out.println(calc(exp));
    }

    public static String calc(String exp) throws RuntimeException {
        // Задача 1: добавить сканнер и возможность ввода данных:
        //public String calc (String input) { <- дурацкое название, изменить!! Выдаёт ошибку неправильного наименования
        // exp - обозначает выражение математическое
        // Задача 1 - ок!!

        // Задача 2: Контроль ввода арифметических знаков:
        String[] block = {"+", "-", "/", "*"};
        //  String[] regexBlock = {"\\+", "-", "/", "\\*"}; // сюка!!!
        int blockIndex = -1;  // какой бы знак не нашли, индекс будет больше 0 и подойдёт
        for (int i = 0; !(i >= block.length); i++) {
            if (exp.contains(block[i])) {
                blockIndex = i; //была -1, сделала 1 и стало:
                break;
            }
            // Вывод ошибки, если пользователь ввёл не нужный знак:
        }

        if (blockIndex == -1) { //для пробы убираю
            throw new RuntimeException("Введён некорректный знак");
        }
        String[] data = exp.split("[\\+\\*-/]");

        if (data[0].matches("\\d+") && data[1].matches("\\d+")) {
            int num1;
            int num2;
            //конвертация арабских чисел из строки в число:
            num1 = Integer.parseInt(data[0]);
            num2 = Integer.parseInt(data[1]);
            int result;
            if (num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) {
                switch (block[blockIndex]) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("Неверная операция");
                        //       System.out.println(converter.romanToInt());
                        //        return String.valueOf(result);
                }
                return String.valueOf(result);
            } else {
                throw new RuntimeException("Ошибка. Введено неверное число. Можно вводить числа от 1 до 10.");
            }
        } else if (romanMap.containsKey(data[0]) && romanMap.containsKey(data[1])) {
            int num1, num2;
            if (romanMap.containsKey(data[0])) {
                num1 = romanMap.get(data[0]);
                if (romanMap.containsKey(data[1])) {
                    num2 = romanMap.get(data[1]);
                    int result;
                    switch (block[blockIndex]) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            result = num1 / num2;
                            break;
                        default:
                            throw new RuntimeException("Неверная операция");
                            //       System.out.println(converter.romanToInt());
                            //        return String.valueOf(result);
                    }
                    String roman = "";
                    if (result<=0) throw new RuntimeException("хреновый результат");
                    int arab;
                    do {
                        arab = arabMap.floorKey(result);   //floorKey - метод, который ищет ключ в мапе (снизу - вверх)
                        roman += arabMap.get(arab);
                        result -= arab;
                    } while (result !=0);
                    return roman;
                    //        return String.valueOf(result);
                }


            }
//            int result;
//            InputMismatchException converter = null;
//            num1 = converter.romanToInt(data[0]);
//            num2 = converter.romanToInt(data[1]);

//            if (num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) {
//            } else {
//                throw new RuntimeException("Ошибка. Введено неверное число. Можно вводить числа от 1 до 10.");
//            }
//
////            for (int i = 0; i < num1.length(); i++) {
//            // временно убран: int result;
//            int operation;
//            switch (block[blockIndex]) {
//                case "+":
//                    result = num1 + num2;
//                    break;
//                case "-":
//                    result = num1 - num2;
//                    break;
//                case "*":
//                    result = num1 * num2;
//                    break;
//                case "/":
//                    result = num1 / num2;
//                    break;
//                default:
//                    throw new RuntimeException("Неверная операция");
//                    //       System.out.println(converter.romanToInt());
//                    //        return String.valueOf(result);
//            }
//            if (return String.valueOf(result)); //вывод римского выражения
//            else {
//                return (intToRoman(result)); //вывод арабского выражения с конвертацией
//            }


        }
        throw new RuntimeException("хреновые числа");
    }

}



// public int isRoman(String number) {
//    return romanMap.containsKey(number.int(0));
//        }
//
//        public String inToRoman(int number) {
//    String roman = "";
//    int arab;
//    do {
//        arab = arabMap.floorKey(number);
//        roman += arabMap.get(arab);
//        number -= arab;
//        } while (number !=0);
//    return roman;
//        }
//
//        public int romanToInt(String s) {
//    int end = s.length() -1;
//    char[] arr = s.toCharArray();
//    int arabian;
//    int result = romanMap.get(arr[end]);
//    for (int i = end - 1; i >= 0; i--) {
//
//        }
//        }


        /*при помощи сплита разделяем наши значения и смотрим,
        что прописано в выражении: рим+рим или араб+араб
        или рим+араб, какие именно символы */

//Задача 4: арифметические действия калькулятора:

//            if (value1 >= 1 && value1 <= 10 && value2 >= 1 && value2 <= 10) {
//            } else {
//                if (value1 >= "I" && value1 <= "X" && value2 >= "I" && value2 <= "X") {
//            } else {
//                throw new IOException("Вы ввели неверное число");
//            }
////
//
//
//                String romanNumeral = "";
//                Object value = map.getOrDefault();
//            return Integer.parseInt(String)ж
//
//        }
//        }
//      }
//  }


//            public int isRoman (String number) {return romanMap.containsKey(number);}

//            public String intToRoman(int number){
//              String roman = "";    // строка для римского числа
//              int arab;
//              int number;
//                do {
//                  arab = arabMap.floorKey(number);   //floorKey - метод, который ищет ключ в мапе (снизу - вверх)
//                  roman += arabMap.get(arab);
//                  number -= arab;
//              }  while (number !=0);
//              return roman;
//            }

//            public int romanToInt(String str){
//              int end = str.length() -1;
//              String[]arr = str.toIntArray();
//              int arab;
//              int result = romanMap.get(arr[end]);
//              for(int i = end-1; i>=0; i--);
//                {         //мне кажется должно быть i>0
//                    arab = romanMap.get(arr[end]);
//                  if (arab < romanMap.get(arr[i + 1])) {
//                     result -= arab;
//                } else {
//                  result += arab;
//            }
//         }
//           return result;
