package com.possiblelabs.speechtest;

/**
 * Created by Alvaro Orellana on 10/13/16.
 */

public class SpellUtil {

    public static String convertToSpellOnce(String words) {
        StringBuilder sb = new StringBuilder();
        for (char letter : words.toCharArray()) {
            sb.append(convert(letter));
            sb.append(",");
        }
        return sb.toString();
    }

    private static String convert(char letter) {
        switch (letter) {
            case 'a':
                return "a";
            case 'b':
                return "b";
            case 'c':
                return "c";
            case 'd':
                return "d";
            case 'e':
                return "e";
            case 'f':
                return "f";
            case 'g':
                return "g";
            case 'h':
                return "h";
            case 'i':
                return "i";
            case 'j':
                return "j";
            case 'k':
                return "k";
            case 'l':
                return "l";
            case 'm':
                return "m";
            case 'n':
                return "n";
            case 'o':
                return "o";
            case 'p':
                return "p";
            case 'q':
                return "q";
            case 'r':
                return "r";
            case 's':
                return "s";
            case 't':
                return "t";
            case 'u':
                return "u";
            case 'v':
                return "v";
            case 'w':
                return "w";
            case 'x':
                return "x";
            case 'y':
                return "y";
            case 'z':
                return "z";
        }
        return "";
    }

    private static String convertSoundBased(char letter) {
        switch (letter) {
            case 'a':
                return "a";
            case 'b':
                return "bee";
            case 'c':
                return "cee";
            case 'd':
                return "dee";
            case 'e':
                return "e";
            case 'f':
                return "ef";
            case 'g':
                return "gee";
            case 'h':
                return "aitch";
            case 'i':
                return "i";
            case 'j':
                return "jay";
            case 'k':
                return "kay";
            case 'l':
                return "el";
            case 'm':
                return "em";
            case 'n':
                return "en";
            case 'o':
                return "o";
            case 'p':
                return "pee";
            case 'q':
                return "cue";
            case 'r':
                return "ar";
            case 's':
                return "ess";
            case 't':
                return "tee";
            case 'u':
                return "u";
            case 'v':
                return "vee";
            case 'w':
                return "double-u";
            case 'x':
                return "ex";
            case 'y':
                return "wy";
            case 'z':
                return "zed";
        }
        return "";

    }
}
