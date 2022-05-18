package SRP;

import java.io.FileNotFoundException;

public class Demo {
    public static void main( String[] args ) throws FileNotFoundException {
        Journal j = new Journal();

        j.addEntry("I cried today");
        j.addEntry("I ate a bug");

        System.out.println(j);

        Journal.Persistance persistance = new Journal.Persistance();

        String fileName = "/home/amimul/IdeaProjects/design_pattern/src/SRP/output/abcd.txt";
        persistance.saveToFile(j, fileName, true);
    }
}
