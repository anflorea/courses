package ro.ubbcluj.scs.java.snake.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by flo on 04/06/2017.
 */
public class Game implements Serializable {
    private int size;
    private int length;
    private int[][] matrix;
    private int headX;
    private int headY;
    private int treatX;
    private int treatY;

    public Game() {
        size = 32;
        length = 4;
        matrix = new int[size][size];
        headX = size / 2;
        headY = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int auxX = Math.abs(random.nextInt() % size);
            int auxY = Math.abs(random.nextInt() % size);
            matrix[auxX][auxY] = -1;
        }
        treatX = Math.abs(random.nextInt() % size);
        treatY = Math.abs(random.nextInt() % size);
        while (matrix[treatY][treatX] != 0) {
            treatX = Math.abs(random.nextInt() % size);
            treatY = Math.abs(random.nextInt() % size);
        }
        matrix[treatY][treatX] = -2;
        matrix[headY][headX] = length;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getHeadX() {
        return headX;
    }

    public int getTreatX() {
        return treatX;
    }

    public int getTreatY() {
        return treatY;
    }

    public int getHeadY() {
        return headY;
    }

    public void moveUp() throws Exception {
        headY -= 1;
        if (headY < 0 || headY >= size) {
            throw new Exception("Out of bounds");
        }
        if (matrix[headY][headX] == -2) {
            length += 1;
            regenerateTreat();
        }
        else if (matrix[headY][headX] != 0) {
            throw new Exception("You hit something");
        } else {
            clearTail();
        }
        matrix[headY][headX] = length;
    }

    public void moveDown() throws Exception {
        headY += 1;
        if (headY < 0 || headY >= size)
            throw new Exception("Out of bounds");
        if (matrix[headY][headX] == -2) {
            length += 1;
            regenerateTreat();
        }
        else if (matrix[headY][headX] != 0)
            throw new Exception("You hit something");
        else {
            clearTail();
        }
        matrix[headY][headX] = length;
    }

    public void moveRight() throws Exception {
        headX += 1;
        if (headX < 0 || headX >= size)
            throw new Exception("Out of bounds");
        if (matrix[headY][headX] == -2) {
            length += 1;
            regenerateTreat();
        }
        else if (matrix[headY][headX] != 0) {
            throw new Exception("You hit something");
        } else
            clearTail();
        matrix[headY][headX] = length;
    }

    public void moveLeft() throws Exception {
        headX -= 1;
        if (headX < 0 || headX >= size)
            throw new Exception("Out of bounds");
        if (matrix[headY][headX] == -2) {
            length += 1;
            regenerateTreat();
        }
        else if (matrix[headY][headX] != 0)
            throw new Exception("You hit something");
        else
            clearTail();
        matrix[headY][headX] = length;
    }

    private void clearTail() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == headY && j == headX)
                    continue;
                if (matrix[i][j] > 0)
                    matrix[i][j] -= 1;

            }
        }
    }

    private void regenerateTreat() {
        Random random = new Random();
        treatX = Math.abs(random.nextInt() % size);
        treatY = Math.abs(random.nextInt() % size);
        while (matrix[treatY][treatX] != 0) {
            treatX = Math.abs(random.nextInt() % size);
            treatY = Math.abs(random.nextInt() % size);
        }
        matrix[treatY][treatX] = -2;
    }
}
