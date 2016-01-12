package word;

public class Letter {
    private char letter;
    private String letterString;
    
    public Letter(char l){
        letter = l;
        letterString = String.valueOf(letter);
    }
    
    public char getLetter(){
        return letter;
    }
    
    public String getLetterStr(){
        return letterString;
    }
}
