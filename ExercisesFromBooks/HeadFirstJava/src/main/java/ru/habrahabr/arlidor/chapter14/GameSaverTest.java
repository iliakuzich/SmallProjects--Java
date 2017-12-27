package ru.habrahabr.arlidor.chapter14;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSaverTest {

    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"bow", "sword", "dust"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"bare hands", "big axe"});
        GameCharacter three = new GameCharacter(120, "Magician", new String[]{"spells", "invisibility"});

        try {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"))) {
                os.writeObject(one);
                os.writeObject(two);
                os.writeObject(three);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameSaverTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println("One's type: " + oneRestore.getType());
            System.out.println("Two's type: " + twoRestore.getType());
            System.out.println("Three's type: " + threeRestore.getType());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(GameSaverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
