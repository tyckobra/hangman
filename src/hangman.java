import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Hangman är ett spel som går ut på att man ska komma på ett ord som man inte vet vad det är genom att gissa på vilka bokstäver
//som används i ordet. Man får veta hur många bokstäver det är i ordet, om man gissar på fel bokstav så ritas en del av en gubbe
//som foten, sen benet, sen huvudet tills man har ritat så långt att gubben blivit hängd. Eller så listar man ut ordet innan
//gubben hängts.

//Delarna man behöver programmera för att hänga gubbe ska funka är

//En startmeny
//Antal liv
//Bokstäverna som får användas
//Vad som händer när man skriver samma bokstav igen eller om man skriver ett tecken som inte får användas eller om man gissar fel
//Olika endings på spelet
//Vad sm händer när man skriver rätt bokstav
//

public class hangman {

    public static String [] words = {"EVEREST", "CINEMA", "APEX", "CLIMAX", "WISDOM", "PREMIERE", "HEREDITARY", "PRIMARY"};
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> arr = new ArrayList<>();

    public static void printBoard(ArrayList<String>arr, int life){
        for(String x: arr){
            System.out.print(x);

        }
        System.out.println(" " + life + " life left");
    }

    public static boolean checkWinner(ArrayList<String>arr, String theWord, int life){
        String winnerCheck = "";
        for(String x: arr){
            winnerCheck+=x;
//Olika slut på spelet
        }
        if(winnerCheck.equals(theWord)){
            System.out.println("You Won!");
            return false;
        }else if(life==0){
            System.out.println("No life left");
            System.out.println("Game Over!");
            return false;
        }
        return false;
    }
//Antal liv
    public static void main(String[] args) {
        while(true){
            String theWord = "";
            arr.clear();
            int life = 8;
//Menyn
            System.out.println("Welcome to hangman!");
            System.out.println();
            System.out.println("Press n for new game");
            System.out.println("Press q to quit");
            String menu = scanner.nextLine();

            if(menu.equals("q")){
                System.out.println("Exiting...");
                break;
            }else if(menu.equals("n")){
                int index = random.nextInt(words.length);
                theWord += words[index];
                for(int i = 0; i<theWord.length(); i++){
                    arr.add("_");
                }
//Bokstäverna som får användas
                printBoard(arr, life);
                while(checkWinner(arr, theWord, life)){
                    System.out.println("Enter a letter (A-Z)");

                    String answer = scanner.nextLine();

                    if(!Pattern.matches("[A-Z]", answer)){
                        System.out.println("Only capital letters!");
                        continue;
                    }
//Ifall man skriver en bokstav man redan skrivit
                    char letter = answer.charAt(0);
                    if(arr.contains(answer)){
                        System.out.println("Already entered");
                    }else{
                        for(int i = 0; i<theWord.length(); i++){
                            if(theWord.charAt(i) == letter){
                                arr.remove(i);
                                arr.add(i, answer);
                            }
//Ifall man skriver en fel bokstav
                        }
                        if(!arr.contains(answer)){
                            life-=1;
                        }

                    }
                    printBoard(arr, life);
                    System.out.println();
                }
            }else{
                System.out.println("Invalid Character");
                continue;
            }
        }
    }
}
