package Maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private int[] start = new int[2];
    private int[] finish = new int[2];

    public Maze(String f) {
        readFile(f);
    }

    private void readFile(String f) {
        try {
            File file = new File(f);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR");
        }
    }

}
