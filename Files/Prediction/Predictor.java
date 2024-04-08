import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Predictor {
    private static final String separation = " ";
    private Tree racine = new Tree("", null);

    private static String reformateChar(int c) {
        // min -> maj
        if (c<=122 && c>=97) {
            c -= 32;
        } 
        // remplacer saut de ligne par espace
        else if (c==10) {
            c=32;
        }
        // ne pas prendre en compte les caractères non présents sur le clavier
        else if (c<=191 && c>=123 || c<=96 && c>=91 || c<=62 && c>=34 || c==11 || c==13 || c<=31 && c>=1) {
            c = 0;
        }
        // é è ê ë -> E
        else if (c<=235 && c>=232 || c<=203 && c>=200) {
            c = 69;
        }
        // à ... -> A
        else if (c<=197 && c>=192 || c<=229 && c>=224) {
            c = 65;
        }
        // ù -> U
        else if (c<=220 && c>=218 || c<=252 && c>=249) {
            c = 85;
        }
        // ö -> O
        else if (c<=214 && c>=210 || c<=246 && c>=242) {
            c = 79;
        }
        // ï -> I
        else if (c<=207 && c>=204 || c<=239 && c>=236) {
            c = 73;
        }

        return ""+(char) c;
    }


    private static void createCorpusTXT() {
        try {
            File wiki = new File("Prediction/wikipediaFR-TXT.txt/wikipediaTXT.txt");
            FileReader wikiReader = new FileReader(wiki);
            BufferedReader wikiBufferedReader = new BufferedReader(wikiReader);
            File scrabble = new File("Prediction/dico.txt");
            FileReader scrabbleReader = new FileReader(scrabble);
            BufferedReader scrabbleBufferedReader = new BufferedReader(scrabbleReader);
            PrintWriter writer = new PrintWriter("Prediction/corpus.txt");
            try {
                int c=wikiBufferedReader.read();
                for (int i=0; i<10000000 && c!=-1; i++) {
                    writer.print(reformateChar(c));
                    c = wikiBufferedReader.read();
                }
                while((c = scrabbleBufferedReader.read()) != -1)
                {
                    writer.print(reformateChar(c));
                }
            } finally {
                wikiReader.close();
                wikiBufferedReader.close();
                scrabbleReader.close();
                scrabbleBufferedReader.close();
                writer.close();
            }
        } catch(IOException e)
        {
          e.printStackTrace();
        }
    }

    private List<String> createCorpus() {
        List<String> corpus = new ArrayList<>();
        try {
            File file = new File("Prediction/corpus.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                int c=0;
                while((c = bufferedReader.read()) != -1)
                {
                    corpus.add(reformateChar(c));
                }
            } finally {
                fileReader.close();
                bufferedReader.close();
            }
        } catch(IOException e)
        {
          e.printStackTrace();
        }
        return corpus; 
    }

    public void createTree() {
        Tree current;
        List<String> corpus = createCorpus();

        current = racine;
        for (String letter : corpus) {
            if (letter.equals(separation)) {
                current = racine;
            } else {
                current = current.addLettre(letter, current);
            }
        }
    }

    public Tree getRacine() {
        return racine;
    }

    public static void main(String[] args) {
        Predictor predictor = new Predictor();
        createCorpusTXT();
        predictor.createTree();
        Tree racine = predictor.getRacine();
        List<String> pred = racine.predictNext(true);

        for (String l : pred) {
            System.out.print(l+", ");
        }

        // affichage arbre
        // 
        // racine.printTree(0);
    }
}
