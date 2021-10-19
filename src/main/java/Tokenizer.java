import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    public SymbolTable<String> symbolTable;

    public Tokenizer(SymbolTable<String> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void execute (String filename) {
        var tokens = tokenize(filename);
        addToSymbolTable(tokens);
    }

    public ArrayList<String> tokenize(String filename) {
        var tokens = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                var temp = (line.split("[\\W]"));
                for (var item:
                     temp) {
                    tokens.add(item);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    public void addToSymbolTable(ArrayList<String> tokens) {
        for (var token:
             tokens) {
            symbolTable.add(token);
        }
    }
}
