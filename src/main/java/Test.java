public class Test {
    public static void main(String[] args) {
        SymbolTable<String> table = new SymbolTable<>();
        Tokenizer tokenizer = new Tokenizer(table);
        tokenizer.execute("resources/p1.txt");
        System.out.println(tokenizer.symbolTable);
    }
}
