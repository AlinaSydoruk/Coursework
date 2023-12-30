package com.example.trpzmacrosproject.events.util;

import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyUtil {
    private static final Map<Integer, String> keys = new HashMap<>();

    public static Optional<String> getNameByCode(int code) {
        String result = keys.get(code);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public static Optional<Integer> getCodeByName(String name) {
        for (Map.Entry<Integer, String> entry : keys.entrySet()) {
            if (entry.getValue().equals(name)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }


    static {
        keys.put(0, "TS_UP");
        keys.put(1, "LBUTTON");
        keys.put(2, "RBUTTON");
        keys.put(3, "CANCEL");
        keys.put(4, "MBUTTON");
        keys.put(5, "XBUTTON1");
        keys.put(6, "XBUTTON2");
        keys.put(8, "BACK");
        keys.put(9, "TAB");
        keys.put(12, "CLEAR");
        keys.put(13, "RETURN");
        keys.put(16, "SHIFT");
        keys.put(17, "CONTROL");
        keys.put(18, "MENU");
        keys.put(19, "PAUSE");
        keys.put(20, "CAPITAL");
        keys.put(21, "KANA/HANGUEL/HANGUL");
        keys.put(23, "JUNJA");
        keys.put(24, "FINAL");
        keys.put(25, "HANJA/KANJI");
        keys.put(27, "ESCAPE");
        keys.put(28, "CONVERT");
        keys.put(29, "NONCONVERT");
        keys.put(30, "ACCEPT");
        keys.put(31, "MODECHANGE");
        keys.put(32, "SPACE");
        keys.put(33, "PRIOR");
        keys.put(34, "NEXT");
        keys.put(35, "END");
        keys.put(36, "HOME");
        keys.put(37, "LEFT");
        keys.put(38, "UP");
        keys.put(39, "RIGHT");
        keys.put(40, "DOWN");
        keys.put(41, "SELECT");
        keys.put(42, "PRINT");
        keys.put(43, "EXECUTE");
        keys.put(44, "SNAPSHOT");
        keys.put(45, "INSERT");
        keys.put(46, "DELETE");
        keys.put(47, "HELP");
        keys.put(48, "0");
        keys.put(49, "1");
        keys.put(50, "2");
        keys.put(51, "3");
        keys.put(52, "4");
        keys.put(53, "5");
        keys.put(54, "6");
        keys.put(55, "7");
        keys.put(56, "8");
        keys.put(57, "9");
        keys.put(65, "A");
        keys.put(66, "B");
        keys.put(67, "C");
        keys.put(68, "D");
        keys.put(69, "E");
        keys.put(70, "F");
        keys.put(71, "G");
        keys.put(72, "H");
        keys.put(73, "I");
        keys.put(74, "J");
        keys.put(75, "K");
        keys.put(76, "L");
        keys.put(77, "M");
        keys.put(78, "N");
        keys.put(79, "O");
        keys.put(80, "P");
        keys.put(81, "Q");
        keys.put(82, "R");
        keys.put(83, "S");
        keys.put(84, "T");
        keys.put(85, "U");
        keys.put(86, "V");
        keys.put(87, "W");
        keys.put(88, "X");
        keys.put(89, "Y");
        keys.put(90, "Z");
        keys.put(91, "LWIN");
        keys.put(92, "RWIN");
        keys.put(93, "APPS");
        keys.put(95, "SLEEP");
        keys.put(96, "NUMPAD0");
        keys.put(97, "NUMPAD1");
        keys.put(98, "NUMPAD2");
        keys.put(99, "NUMPAD3");
        keys.put(100, "NUMPAD4");
        keys.put(101, "NUMPAD5");
        keys.put(102, "NUMPAD6");
        keys.put(103, "NUMPAD7");
        keys.put(104, "NUMPAD8");
        keys.put(105, "NUMPAD9");
        keys.put(106, "MULTIPLY");
        keys.put(107, "ADD");
        keys.put(108, "SEPARATOR");
        keys.put(109, "SUBTRACT");
        keys.put(110, "DECIMAL");
        keys.put(111, "DIVIDE");
        keys.put(112, "F1");
        keys.put(113, "F2");
        keys.put(114, "F3");
        keys.put(115, "F4");
        keys.put(116, "F5");
        keys.put(117, "F6");
        keys.put(118, "F7");
        keys.put(119, "F8");
        keys.put(120, "F9");
        keys.put(121, "F10");
        keys.put(122, "F11");
        keys.put(123, "F12");
        keys.put(124, "F13");
        keys.put(125, "F14");
        keys.put(126, "F15");
        keys.put(127, "F16");
        keys.put(128, "F17");
        keys.put(129, "F18");
        keys.put(130, "F19");
        keys.put(131, "F20");
        keys.put(132, "F21");
        keys.put(133, "F22");
        keys.put(134, "F23");
        keys.put(135, "F24");
        keys.put(144, "NUMLOCK");
        keys.put(145, "SCROLL");
        keys.put(160, "LSHIFT");
        keys.put(161, "RSHIFT");
        keys.put(162, "LCONTROL");
        keys.put(163, "RCONTROL");
        keys.put(164, "LMENU");
        keys.put(165, "RMENU");
        keys.put(166, "BROWSER_BACK");
        keys.put(167, "BROWSER_FORWARD");
        keys.put(168, "BROWSER_REFRESH");
        keys.put(169, "BROWSER_STOP");
        keys.put(170, "BROWSER_SEARCH");
        keys.put(171, "BROWSER_FAVORITES");
        keys.put(172, "BROWSER_HOME");
        keys.put(173, "VOLUME_MUTE");
        keys.put(174, "VOLUME_DOWN");
        keys.put(175, "VOLUME_UP");
        keys.put(176, "MEDIA_NEXT_TRACK");
        keys.put(177, "MEDIA_PREV_TRACK");
        keys.put(178, "MEDIA_STOP");
        keys.put(179, "MEDIA_PLAY_PAUSE");
        keys.put(180, "LAUNCH_MAIL");
        keys.put(181, "LAUNCH_MEDIA_SELECT");
        keys.put(182, "LAUNCH_APP1");
        keys.put(183, "LAUNCH_APP2");
        keys.put(186, "OEM_1");
        keys.put(187, "OEM_PLUS");
        keys.put(188, "OEM_COMMA");
        keys.put(189, "OEM_MINUS");
        keys.put(190, "OEM_PERIOD");
        keys.put(191, "OEM_2");
        keys.put(192, "OEM_3");
        keys.put(219, "OEM_4");
        keys.put(220, "OEM_5");
        keys.put(221, "OEM_6");
        keys.put(222, "OEM_7");
        keys.put(223, "OEM_8");
        keys.put(226, "OEM_102");
        keys.put(229, "PROCESSKEY");
        keys.put(231, "PACKET");
        keys.put(246, "ATTN");
        keys.put(247, "CRSEL");
        keys.put(248, "EXSEL");
        keys.put(249, "EREOF");
        keys.put(250, "PLAY");
        keys.put(251, "ZOOM");
        keys.put(252, "NONAME");
        keys.put(253, "PA1");
        keys.put(254, "OEM_CLEAR");
    }

}
