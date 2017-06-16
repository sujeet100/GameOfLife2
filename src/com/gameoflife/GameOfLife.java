package com.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sujit on 6/15/17.
 */
public class GameOfLife {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String line;
        List<int[]> seed = new ArrayList<>();
        while(!(line=scanner.nextLine()).isEmpty()){
            String[] positions = line.split(",");
            seed.add(new int[]{Integer.parseInt(positions[0].trim()), Integer.parseInt(positions[1].trim())});
        }

        int[][]seedArray = new int[seed.size()][2];
        for(int i=0; i< seed.size(); i++) {
            seedArray[i] = seed.get(i);
        }
        Grid grid = Grid.withSeed(seedArray);

        while (true) {
            grid.print();
            grid.nextGeneration();
            Thread.sleep(1000);
        }

    }
}
