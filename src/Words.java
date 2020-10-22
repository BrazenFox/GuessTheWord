import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Words {
    private final String word;
    private String currentStateWord;

    public Words(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try {
            File file = new File("src/" + fileName + ".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        this.word = words.get(random.nextInt(words.size()));
        this.currentStateWord = "_".repeat(this.word.length());

    }

    public String getWord() {
        return word;
    }

    public String getCurrentStateWord() {
        return currentStateWord;
    }

    public String getEncryptedWord(String inputStr) {
        if (inputStr.length() == 1) {
            StringBuilder word = new StringBuilder(this.getCurrentStateWord());
            if (this.getWord().contains(inputStr)) {
                for (int i = 0; i < this.getWord().length(); i++) {
                    if (this.getWord().charAt(i) == inputStr.charAt(0))
                        word.setCharAt(i, inputStr.charAt(0));
                }
            }
            this.currentStateWord = String.valueOf(word);
        } else if (this.getWord().equals(inputStr)) {
            this.currentStateWord = inputStr;
        }
        return this.getCurrentStateWord();
    }
}
