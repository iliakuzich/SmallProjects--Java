package ru.habrahabr.arlidor.chapter14;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DungeonTest {

    public static void main(String[] args) {
        DungeonGame d = new DungeonGame();
        System.out.println(d.getX() + d.getY() + d.getZ());
        try {
            FileOutputStream fos = new FileOutputStream("dg.ser");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(d);
            }
            FileInputStream fis = new FileInputStream("dg.ser");
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                d = (DungeonGame) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(DungeonTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
