package concepts.g.t;

import java.util.Random;

public class AlphabetGenerator {
    public static void main(String[] args) {

        String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder ticket;
        Random rnd = new Random();
        int numberOfAlphabetsInATicket = 50;
        int numberOfTickets = 40;

        for (int i = 0; i < numberOfTickets; i++) {
            ticket = new StringBuilder();
            while (ticket.length() < numberOfAlphabetsInATicket) {
                int index = (int) (rnd.nextFloat() * ALPHABETS.length());
                char generateCharacter = ALPHABETS.charAt(index);
                if (!(ticket.indexOf(String.valueOf(generateCharacter)) > -1)) {
                    ticket.append(generateCharacter).append(' ').append(' ').append(' ').append(' ').append(' ');
                }
            }
            System.out.print(i+1+".       "+ticket+"\n\n");
        }
    }
}