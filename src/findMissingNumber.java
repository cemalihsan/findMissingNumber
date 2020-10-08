import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.*;
import java.util.Scanner;
import static java.lang.Integer.sum;

public class findMissingNumber {

    public static void shuffle(List<Integer> array, int size){

        int current;
        Random random = new Random();

        for(int i = 0; i < size; i++){
            int index = i + random.nextInt(size - i);

            current = array.get(i);
            array.set(i, array.get(index));
            array.set(index,current);

        }

    }

    public static void writeFile(String filename, List<Integer> array, int size) throws IOException {

        FileWriter fileWriter = new FileWriter(filename + ".txt");
        for(int i = 0; i <size-1; i++){
            fileWriter.write(array.get(i).toString());

            if(i < size - 1){
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
    }

    public static Object[] readFile(String filename) throws IOException {

        File file = new File(filename);
        Scanner scanner = new Scanner(new FileReader(file));

        List<Integer> intList = new ArrayList<Integer>();
        while(scanner.hasNextInt()) {
            intList.add(scanner.nextInt());
        }

        return intList.toArray();
    }

    public static void findNumber(Object[] array, int size){

        int resultOfNCalculation = 0;
        int missingNumberCalculation = 0;
        for(int i = 1; i <= size; i++){
            resultOfNCalculation = resultOfNCalculation + i;
        }

        for(int i = 0; i < array.length ; i++){
            missingNumberCalculation = sum(missingNumberCalculation, (Integer) array[i]);
        }
        System.out.println("From 1 to Size Sum:" + resultOfNCalculation);
        System.out.println("Txt File Sum:" + missingNumberCalculation);

        int missingNumber = resultOfNCalculation - missingNumberCalculation;
        System.out.println("Missing Number:" + missingNumber);

    }

    public static void main(String[] args) throws IOException {

        List<Integer> list = new ArrayList<Integer>();

        Object[] listArray;

        int size = 100; //it works for all sizes but my computer could not handle 1 billion so i used 100 as base

        for(int i = 1; i <=size; i++){
            list.add(i);
        }

        shuffle(list,size);
       /* for(int j : list){
            System.out.println(j);
        }*/

        writeFile("numbers", list, size);

        listArray = readFile("numbers.txt");
        System.out.println("------------------------------------------------------");
       /* for(Object j : listArray){
            System.out.println(j);
        }*/

        findNumber(listArray,size);

    }
}
