package SRP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Journal {

    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry( String text ) {
        entries.add(" " + (++count) + ": " + text);
    }

    public void removeEntry( int index ) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    static class Persistance {

        public void saveToFile( Journal journal, String fileName, boolean overWrite) throws FileNotFoundException {

            if(overWrite || new File(fileName).exists()){
                try (PrintStream printStream = new PrintStream(fileName)) {
                    printStream.println(journal.toString());
                }
            }
        }

        public void load( String fileName ) {

        }

        public void load( URL url ) {

        }
    }
}
