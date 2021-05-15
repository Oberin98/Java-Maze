package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner systemScanner = new Scanner(System.in);
        Scanner fileScanner = new Scanner(new File(systemScanner.nextLine()));
        ArrayList<String[]> maze = new ArrayList<>();
        int[] start = new int[]{-1, -1};
        int[] end = new int[]{-1, -1};

        // generate nested list - maze
        while (fileScanner.hasNextLine()) {
            String[] row = fileScanner.nextLine().split("");

            // looking for start and end points
            for (int i = 0; i < row.length; i++) {
                if (row[i].equals("o")) {
                    if (start[0] == -1) {
                        start = new int[]{maze.size(), i};
                    } else {
                        end = new int[]{maze.size(), i};
                    }
                }
            }

            maze.add(row);
        }

        if (start[0] == -1 || end[0] == -1) {
            System.out.println("INCORRECT INPUT");
        } else if (Main.run(maze, start, end, new TreeSet<>())) {
            System.out.println("EXIST");
        } else {
            System.out.println("DOES NOT EXIST");
        }


    }

    static boolean isNotWall(List<String[]> maze, int[] position) {
        int y = position[0];
        int x = position[1];
        return !maze.get(y)[x].equals("x");
    }

    static boolean isNotVisited(TreeSet<String> visited, int[] position) {
        int y = position[0];
        int x = position[1];
        return !visited.contains(y + "" + x);
    }

    static boolean run(List<String[]> maze, int[] position, int[] end, TreeSet<String> visited) {
        int y = position[0];
        int x = position[1];

        if (x == end[1] && y == end[0]) {
            return true;
        }

        int[] rightPos = new int[]{y, x + 1};
        int[] leftPos = new int[]{y, x - 1};
        int[] topPos = new int[]{y + 1, x};
        int[] bottomPos = new int[]{y - 1, x};

        visited.add(y + "" + x);

        // go to right
        if (isNotWall(maze, rightPos) && isNotVisited(visited, rightPos) && run(maze, rightPos, end, visited)) {
            return true;
        }
        // go to left
        if (isNotWall(maze, leftPos) && isNotVisited(visited, leftPos) && run(maze, leftPos, end, visited)) {
            return true;
        }
        // go to top
        if (isNotWall(maze, topPos) && isNotVisited(visited, topPos) && run(maze, topPos, end, visited)) {
            return true;
        }
        // go to bottom
        if (isNotWall(maze, bottomPos) && isNotVisited(visited, bottomPos) && run(maze, bottomPos, end, visited)) {
            return true;
        }

        return false;
    }
}
