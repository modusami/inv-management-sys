package dev.modusami.invmanagementsys.model;

public class IdCreator {
    private static int id = 1;
    private static int nextId = -1;
    private static int MAX_ID = 100;

    public IdCreator() {

    }

    public static int id() {
        int numberToReturn = id;
        if (id >= MAX_ID && nextId != -1) {
            id = 1;
            return id;
        }

        if (nextId == -1) {
            id++;
            return numberToReturn;
        }
        else {
            numberToReturn = nextId;
            nextId = -1;
            return numberToReturn;
        }

    }

    public static void setNextIdToUse(int number) {
        nextId = number;
    }

}
