import java.io.*;
import java.util.ArrayList;

public class Player {
    private final String name;
    private String word;
    private int trial;
    ArrayList<String> players = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getTrial() {
        return trial;
    }

    public void setTrial(int trial) {
        this.trial = trial;
    }

    public void addToFile() throws IOException {
        File file = new File("src/players.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            boolean check = false;
            while (line != null) {
                this.players.add(line + "\r");
                if (line.startsWith("NAME")) {
                    if (line.split(":")[1].equals(this.name)) {
                        check = true;
                        this.players.add(" word: " + this.getWord() + "; trial: " + this.getTrial() + "\r");
                    }
                }
                line = reader.readLine();
            }
            FileWriter writer = new FileWriter(file);
            for (String player : this.players) {
                writer.write(player);
            }
            if (!check)
                writer.write("NAME:" + this.getName() + "\r" + " word: " + this.getWord() + "; trial: " + this.getTrial() + "\r");
            writer.flush();
            writer.close();
            this.players.clear();
        } catch (IOException e) {
            FileWriter writer = new FileWriter(file);
            writer.write("NAME:" + this.getName() + "\r" + " word: " + this.getWord() + "; trial: " + this.getTrial() + "\r");
            writer.flush();
            writer.close();
        }
    }


}

