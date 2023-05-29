import java.util.*;
import java.util.Scanner;

public class TestCalc {
    public static Map<String, Integer> romanMap = new HashMap<>();

    static {
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите математическое выражение с 1 до 10 или с I до X: ");
        String exp = scan.nextLine();
        System.out.println(calc(exp));
    }
    public static String calc(String exp) throws RuntimeException {

        String[] block = {"+", "-", "/", "*"};
        int blockIndex = -1;
        for (int i = 0; !(i >= block.length); i++) {
            if (exp.contains(block[i])) {
                blockIndex = i;
                break;
            }
        }

        if (blockIndex == -1) {
            throw new RuntimeException("Введён некорректный знак");
        }
        String[] data = exp.split("[\\+\\*-/]");
        if(data.length > 2){
            throw new RuntimeException("Ошибка. В выражении нужно вводить только 2 числа");
        }
        if (data[0].matches("\\d+") && data[1].matches("\\d+")) {
            int num1;
            int num2;
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
                    }
                    String roman = "";
                    if (result<=0) throw new RuntimeException("Ошибка. У римских чисел нет числа 0.");
                    int arab;
                    do {
                        arab = arabMap.floorKey(result);
                        roman += arabMap.get(arab);
                        result -= arab;
                    } while (result !=0);
                    return roman;
                }
            }
        }
        throw new RuntimeException("Плохие числа");
    }
}
