package word;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import nu.xom.*;

public class Word {
    private String word;
    private Letter[] letters;
    private int type;
    private File file;
    
    public Word(String w, int t){
        word = w;
        type = t;
        letters = new Letter[word.length()];
        file = new File("Data/Word/" + word + ".xml");
        
        for(int i = 0; i < word.length(); i++){
            letters[i] = new Letter(word.charAt(i));
        }
        
        //check if the file exsists
        if(!file.exists()){ //just for testing purposes
            load();
        }else{
            save();
        }
    }
    
    public String getWord(){
        return word;
    }
    
    public Letter[] getLetters(){
        return letters;
    }
    
    private void save(){
        Element elmWord = new Element("word");
        
        Element elmWORD = new Element("word");
        elmWORD.appendChild(word);
        elmWord.appendChild(elmWORD);
        
        for(int i = 0; i < word.length(); i++){
            Element elmChar = new Element("char");
            elmChar.appendChild(letters[i].getLetterStr());
            elmWord.appendChild(elmChar);
        }
        
        Element elmType = new Element("type");
        elmType.appendChild(Integer.toString(type));
        elmWord.appendChild(elmType);
        
        Document doc = new Document(elmWord);
        
        try {
            Serializer serializer = new Serializer(System.out, "ISO-8859-1");
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(doc);  
        }catch (IOException ex) {
            System.err.println(ex); 
        }
        
        //System.out.println(doc.toXML()); 
        
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(doc.toXML());
            writer.close();
            writer.flush();
        } catch (IOException ex) {
            //debug.debug.error("Couldn't create new word file");
        }
        
        System.out.println("Saved");
    }
    
    private void load(){
        
    }
}
