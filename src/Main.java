import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your name");
        String name = sc.next();
        Player player = new Player(name);
        boolean run = true;
        while (run) {
            System.out.println("Select difficulty (1 to 3):");
            String fileName = sc.next();
            while (!(fileName.equals("1") || fileName.equals("2") || fileName.equals("3"))) {
                System.out.println("The difficulty you choose does not exist!");
                fileName = sc.next();
            }
            boolean win = false;
            Words word = new Words(fileName);
            int trial = 0;
            while (!win) {
                String guessWord = word.getCurrentStateWord();
                if (trial != 0)
                    System.out.println("Trial " + trial);
                System.out.println("Your word: " + guessWord);
                System.out.println("Key in one character or your guess word or enter \"end\" to exit the game: ");
                String str = sc.next();
                trial += 1;
                if (str.equals("end")) {
                    win = true;
                    run = false;
                }
                if (str.length()>1 && !str.equals(word.getWord())) {
                    win = true;
                    System.out.println("You lose(((");
                }
                guessWord = word.getEncryptedWord(str);
                if (guessWord.equals(word.getWord())) {
                    win = true;
                    player.setWord(guessWord);
                    player.setTrial(trial);
                    player.addToFile();
                    System.out.println(word.getWord());
                    System.out.println("Congratulation!");
                    System.out.println("You got in " + trial + (trial == 1 ? " trial" : " trials"));
                    System.out.println("Do you want to continue playing? Enter y/n");
                    String choice = sc.next();
                    while (!(choice.equals("n") || choice.equals("y"))) {
                        System.out.println("Incorrect answer");
                        choice = sc.next();
                    }
                    if (choice.equals("n")) {
                        run = false;
                    }
                }
            }


        }


    }

}

