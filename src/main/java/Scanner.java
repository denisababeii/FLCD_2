import java.io.*;
import java.security.KeyPair;
import java.util.*;

public class Scanner {
    public SymbolTable<String> symbolTable;
    public List<Pair<String, Integer>> pif;

    public Scanner(SymbolTable<String> symbolTable, List<Pair<String, Integer>> pif) {
        this.symbolTable = symbolTable;
        this.pif = pif;
    }

    public void execute (String input, FileWriter pifFile) throws IOException {
        var tokens = tokenize(input);
        addToPIF(tokens, input, pifFile);
    }

    private ArrayList<Pair<String,Integer>> tokenize(String filename) {
        var tokens = new ArrayList<Pair<String,Integer>>();
        BufferedReader reader;
        int lineNo = 1;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                var temp = line.split("((?<=[{}:;,()\s\t\n=%<>]|\\+|\\*)|(?=[{}:;,()\s\t\n=%<>]|\\+|\\*))");
                for (var item:
                     temp) {
                    tokens.add(new Pair(item,lineNo));
                }
                line = reader.readLine();
                lineNo++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    private boolean isReservedWord(String token) {
        if (token.matches("False|True|func|exit|if|for|from|to|or|orif|read|write|while|and"))
            return true;
        return false;
    }

    private boolean isOperator(String token) {
        if (token.matches("<|>|<=|>=|=|<->|\\+|-|//|\\*|%|<>|}|->|\\{"))
            return true;
        return false;
    }

    private boolean isSeparator(String token) {
        if (token.matches("[{}:;,()\s\t\n]"))
            return true;
        return false;
    }

    private boolean isIdentifier(String token) {
        if(token.matches("^[A-Za-z][a-zA-Z0-9_]*$"))
            return true;
        return false;
    }

    private boolean isConstant(String token) {
        if(token.matches("^-?\\d+\\.?\\d*$"))  // Looks for decimal numbers, with optional hyphen minus and optional full stop plus zero or more decimal numbers following.
            return true;
        if(token.matches("^\"[a-zA-Z0-9_]*\"$")) // String
            return true;
        if(token.matches( "^[a-zA-Z]+\\[[0-9a-zA-Z,]*[0-9a-zA-Z]*]$")) // Array
            return true;
        if(token.matches("'[A-Za-z0-9_]'")) // Character
            return true;
        return false;
    }

    private void addToPIF(ArrayList<Pair<String,Integer>> tokens, String input, FileWriter pifFile) throws IOException {
        for (var item :
                tokens) {
            var token = item.getFirst();
            if (isReservedWord(token) || isOperator(token) || isSeparator(token))
                pif.add(new Pair(token, 0));
            else if (isIdentifier(token) || isConstant(token)) {
                symbolTable.add(token);
                var index = symbolTable.get(token);
                pif.add(new Pair(token, index));
            } else {
                System.out.println("Lexical error at line "+item.getSecond()+", token "+item.getFirst());
            }
        }

        pifFile.append("********"+input+"********\n");
        for (var item :
                pif) {
            pifFile.append(item.toString()+"\n");
        }
        pifFile.flush();

    }
}
