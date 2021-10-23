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
            tokenizer.execute("src/main/resources/p1.txt", "src/main/resources/PIF.out", "src/main/resources/ST.out");
            tokenizer.execute("src/main/resources/p2.txt", "src/main/resources/PIF.out", "src/main/resources/ST.out");
            tokenizer.execute("src/main/resources/p3.txt", "src/main/resources/PIF.out", "src/main/resources/ST.out");
            tokenizer.execute("src/main/resources/p1err.txt", "src/main/resources/PIF.out", "src/main/resources/ST.out");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
