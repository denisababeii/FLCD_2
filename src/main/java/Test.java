import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        SymbolTable<String> table = new SymbolTable<>();
        var pif = new ArrayList<Pair<String, Integer>>();
        Scanner tokenizer = new Scanner(table, pif);
        try {
            var pifFile = new FileWriter("src/main/resources/PIF.out");
            var stFile = new FileWriter("src/main/resources/ST.out");
            stFile.append(table.toString());
            stFile.append("\nThe Symbol Table is built on a HashTable with Linked Lists of HashNodes. Each HashNode contains the token and its hash code.");
            stFile.flush();
            tokenizer.execute("src/main/resources/p1.txt", pifFile);
            tokenizer.execute("src/main/resources/p2.txt", pifFile);
            tokenizer.execute("src/main/resources/p3.txt", pifFile);
            tokenizer.execute("src/main/resources/p1err.txt", pifFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
