import java.io.*;
import java.util.*;
//10 массивов (9 заготовлены и 1 с консоли) Считать с файла массив, найти среднее арифметическое
// и найти колличество максимально близких (Разница должна быть одинакова) и записать в файл
public class Main {

    public static final String sizeOfArray = "Введите размер массива: ";

    public static final String enteringElements = "Введите элементы массива: ";
    public static String solution(int[] number) {
        double sum = 0;
        double average = 0;
        for (int x : number) { // Подсчёт среднего значения в массиве
            sum += x;
            average = sum / number.length;
        }
        //System.out.println(average);
        double close = 1000;
        for (int x : number) { // Нахождения самой близкой разницы
            if (Math.abs(x - average) < close) {
                close = Math.abs(x - average);
            }
        }
        int count = 0;
        String str = null;
        for (int x : number) { //Посчёт колличества таких элементов одинаково близких к разнице
            if (Math.abs(x - average) == close) {
                count = count + 1;
                str = String.valueOf(count);
            }
        }
        return str;
    }

    public static void main(String[] args) throws IOException {
        int[] number;
        try {
            Scanner scanner = new Scanner(new FileReader("input"));//Считать из файла
            while (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" ");
                number = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();

//                System.out.println(Arrays.toString(number));
//                System.out.println(solution(number));

                BufferedWriter writer = new BufferedWriter(new FileWriter("output", true));//Запись в файл

                writer.append(' ');
                writer.append(solution(number));

                writer.close();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner manualInput = new Scanner(System.in);
        System.out.println(sizeOfArray);
        int size = manualInput.nextInt();

        number = new int[size];

        System.out.println(enteringElements);
        for (int i = 0; i < size; i++) {
            number[i] = manualInput.nextInt(); // Заполняем массив элементами, введёнными с клавиатуры
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("output", true));//Запись в файл

        writer.append(' ');
        writer.append(solution(number));

        writer.close();
    }
}