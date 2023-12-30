package com.example.trpzexecutorproject.plugins.keyplugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyUtil {
   private static final Map<Integer, String> keys = new HashMap<>();

    public static Optional<String> getNameByCode(int code){
        String result = keys.get(code);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public static Optional<Integer> getCodeByName(String name){
        for (Map.Entry<Integer, String> entry : keys.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(name)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }



       static {
        keys.put(0x03, "CANCEL");
        keys.put(0x08, "BACK_SPACE");
        keys.put(0x09, "TAB");
        keys.put(0x0C, "CLEAR");
        keys.put(0x10, "SHIFT");
        keys.put(0x11, "CONTROL");
        keys.put(0x12, "ALT");
        keys.put(0x13, "PAUSE");
        keys.put(0x14, "CAPS_LOCK");
        keys.put(0x1B, "ESCAPE");
        keys.put(0x20, "SPACE");
        keys.put(0x21, "PAGE_UP");
        keys.put(0x22, "PAGE_DOWN");
        keys.put(0x23, "END");
        keys.put(0x24, "HOME");
        keys.put(0x25, "LEFT");
        keys.put(0x26, "UP");
        keys.put(0x27, "RIGHT");
        keys.put(0x28, "DOWN");
        keys.put(0x2C, "COMMA");
        keys.put(0x2D, "MINUS");
        keys.put(0x2E, "PERIOD");
        keys.put(0x2F, "SLASH");
        keys.put(0x30, "0");
        keys.put(0x31, "1");
        keys.put(0x32, "2");
        keys.put(0x33, "3");
        keys.put(0x34, "4");
        keys.put(0x35, "5");
        keys.put(0x36, "6");
        keys.put(0x37, "7");
        keys.put(0x38, "8");
        keys.put(0x39, "9");
        keys.put(0x3B, "SEMICOLON");
        keys.put(0x3D, "EQUALS");
        keys.put(0x41, "A");
        keys.put(0x42, "B");
        keys.put(0x43, "C");
        keys.put(0x44, "D");
        keys.put(0x45, "E");
        keys.put(0x46, "F");
        keys.put(0x47, "G");
        keys.put(0x48, "H");
        keys.put(0x49, "I");
        keys.put(0x4A, "J");
        keys.put(0x4B, "K");
        keys.put(0x4C, "L");
        keys.put(0x4D, "M");
        keys.put(0x4E, "N");
        keys.put(0x4F, "O");
        keys.put(0x50, "P");
        keys.put(0x51, "Q");
        keys.put(0x52, "R");
        keys.put(0x53, "S");
        keys.put(0x54, "T");
        keys.put(0x55, "U");
        keys.put(0x56, "V");
        keys.put(0x57, "W");
        keys.put(0x58, "X");
        keys.put(0x59, "Y");
        keys.put(0x5A, "Z");
        keys.put(0x5B, "OPEN_BRACKET");
        keys.put(0x5C, "BACK_SLASH");
        keys.put(0x5D, "CLOSE_BRACKET");
        keys.put(0x60, "NUMPAD0");
        keys.put(0x61, "NUMPAD1");
        keys.put(0x62, "NUMPAD2");
        keys.put(0x63, "NUMPAD3");
        keys.put(0x64, "NUMPAD4");
        keys.put(0x65, "NUMPAD5");
        keys.put(0x66, "NUMPAD6");
        keys.put(0x67, "NUMPAD7");
        keys.put(0x68, "NUMPAD8");
        keys.put(0x69, "NUMPAD9");
        keys.put(0x6A, "MULTIPLY");
        keys.put(0x6B, "ADD");
        keys.put(0x6C, "SEPARATOR");
        keys.put(0x6D, "SUBTRACT");
        keys.put(0x6E, "DECIMAL");
        keys.put(0x6F, "DIVIDE");
        keys.put(0x70, "F1");
        keys.put(0x71, "F2");
        keys.put(0x72, "F3");
        keys.put(0x73, "F4");
        keys.put(0x74, "F5");
        keys.put(0x75, "F6");
        keys.put(0x76, "F7");
        keys.put(0x77, "F8");
        keys.put(0x78, "F9");
        keys.put(0x79, "F10");
        keys.put(0x7A, "F11");
        keys.put(0x7B, "F12");
        keys.put(0x7C, "F13");
        keys.put(0x7D, "F14");
        keys.put(0x7E, "F15");
        keys.put(0x7F, "F16");
        keys.put(0x80, "F17");
        keys.put(0x81, "F18");
        keys.put(0x82, "F19");
        keys.put(0x83, "F20");
        keys.put(0x84, "F21");
        keys.put(0x85, "F22");
        keys.put(0x86, "F23");
        keys.put(0x87, "F24");
        keys.put(0x90, "NUM_LOCK");
        keys.put(0x91, "SCROLL_LOCK");
        keys.put(0xA0, "GREATER");
        keys.put(0xA1, "BRACELEFT");
        keys.put(0xA2, "BRACERIGHT");
        keys.put(0xA3, "AT");
        keys.put(0xA4, "COLON");
        keys.put(0xA5, "CIRCUMFLEX");
        keys.put(0xA6, "DOLLAR");
        keys.put(0xA7, "EURO_SIGN");
        keys.put(0xA8, "EXCLAMATION_MARK");
        keys.put(0xA9, "INVERTED_EXCLAMATION_MARK");
        keys.put(0xAA, "LEFT_PARENTHESIS");
        keys.put(0xAB, "NUMBER_SIGN");
        keys.put(0xAC, "PLUS");
        keys.put(0xAD, "RIGHT_PARENTHESIS");
        keys.put(0xAE, "UNDERSCORE");
        keys.put(0xAF, "WINDOWS");
        keys.put(0xB0, "CONTEXT_MENU");
        keys.put(0xC0, "FINAL");
        keys.put(0xC1, "CONVERT");
        keys.put(0xC2, "NONCONVERT");
        keys.put(0xC3, "ACCEPT");
        keys.put(0xC4, "MODECHANGE");
        keys.put(0xC5, "KANA");
        keys.put(0xC6, "KANJI");
        keys.put(0xC7, "ALPHANUMERIC");
        keys.put(0xC8, "KATAKANA");
        keys.put(0xC9, "HIRAGANA");
        keys.put(0xCA, "FULL_WIDTH");
        keys.put(0xCB, "HALF_WIDTH");
        keys.put(0xCC, "ROMAN_CHARACTERS");
        keys.put(0xCD, "ALL_CANDIDATES");
        keys.put(0xCE, "PREVIOUS_CANDIDATE");
        keys.put(0xCF, "CODE_INPUT");
        keys.put(0xD0, "JAPANESE_KATAKANA");
        keys.put(0xD1, "JAPANESE_HIRAGANA");
        keys.put(0xD2, "JAPANESE_ROMAN");
        keys.put(0xD3, "KANA_LOCK");
        keys.put(0xD4, "INPUT_METHOD_ON_OFF");
        keys.put(0xD5, "CUT");
        keys.put(0xD6, "COPY");
        keys.put(0xD7, "PASTE");
        keys.put(0xD8, "UNDO");
        keys.put(0xD9, "AGAIN");
        keys.put(0xDA, "FIND");
        keys.put(0xDB, "PROPS");
        keys.put(0xDC, "STOP");
        keys.put(0xDD, "COMPOSE");
        keys.put(0xDE, "ALT_GRAPH");
        keys.put(0xDF, "BEGIN");
    }
}
