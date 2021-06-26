package application;

import java.util.HashMap;
import java.util.Scanner;

public class difficult {


    public static int countCharacterOccurences(String[] words) { // count ch sau ie sau ei
        int points = 0;
        for (String word: words) {
            char[] letters = word.toCharArray();
            for (int i = 0; i < word.length() - 1; i++) {
                if ((letters[i] == 'c' && letters[i + 1] == 'h') ||
                    (letters[i] == 'i' && letters[i + 1] == 'e') ||
                    (letters[i] == 'e' && letters[i + 1] == 'i')) {
                    points++;
                }
            }


        }

        return points;
    }

    public static int countVowelsOccurences(String[] words) { // count daca sunt mai mult de 3 vocale
        int vowel = 0;
        int index = 0;

        for (String word: words) {
            index = 0;

            char[] letters = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {

                if ((letters[i] == 'a') ||
                    (letters[i] == 'e') ||
                    (letters[i] == 'i') ||
                    (letters[i] == 'o') ||
                    (letters[i] == 'u') ||
                    (letters[i] == 'y')) {
                    index++;

                    if (index >= 3) {
                        vowel++;
                        index = 0;
                    }
                } else index = 0;

            }
        }

        return vowel;
    }


    public static int countDoubleConsonants(String[] words) { // daca esista consoane care se repeta si sunt identice contorizeaza
        int index = 0;
        int doubleConsonant = 0;
        for (String word: words) {
            index = 0;
            char[] letters = word.toCharArray();
            for (int i = 0; i < word.length() - 1; i++) {
                if ((letters[i] != 'a') &&
                    (letters[i] != 'e') &&
                    (letters[i] != 'i') &&
                    (letters[i] != 'o') &&
                    (letters[i] != 'u') &&
                    (letters[i] != 'y') &&
                    (letters[i] == letters[i + 1])) {
                    index++;
                    if (index > 0) {
                        doubleConsonant++;
                    }


                }

            }
        }
        return doubleConsonant;
    }



    public static int sentenceDifficult(String[] words) {

      
         int finalIndex = 0;
         Integer km[] = new Integer[words.length];
         for(int i=0;i<km.length;i++)
         {
        	 km[i] =0;
         }
         
      //  HashMap < String, Integer > cuvinte = new HashMap < String, Integer > ();
        String[] word = new String[1];
       
        for (int j = 0; j < words.length; j++) {
        	 word[0] = words[j];
        	 if(countCharacterOccurences(word)!=0 || countVowelsOccurences(word)!=0 || countDoubleConsonants(word)!=0 ) {
        		 km[j] = 1;
        	 } else {
        		 km[j] = 0;
        	 }
        }
        
        
        for(int  k=0; k< km.length-2;k++ ){
            if((km[k]== 1) && (km[k+1] == 1)){
                finalIndex +=2;
            }
            if((km[k]== 1) && (km[k+2] == 1)){
                finalIndex++;
            }
        }
        
   
        return finalIndex;
    }









    public static void main(String[] args) {
        System.out.println("enter the sentence");
        try (Scanner sc = new Scanner(System.in)) {
            String sentence;
            sentence = sc.nextLine();
            String[] words = sentence.toLowerCase().split(" ");
            int n;
            int vowe;
            int consonant;
            n = countCharacterOccurences(words);
            consonant = countDoubleConsonants(words);
            vowe = countVowelsOccurences(words);
            int finalIndex = sentenceDifficult(words);

            System.out.println("numarul de car " + n + "numarul vocale " + vowe + " numar duble " + consonant + " difficultate " + finalIndex);
        }


    }
}