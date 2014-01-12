package com.bigeauofn.adventure.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
* A really pathetic file lexer
* */
public class ActorFileLexer {
    // protected?
    public static enum TokenType {
        HEADER,
        KEY,
        SCORE,
        KLEENE,
        NONE
    }

    protected static enum Header {
        BASE_DATA,
        STATS,
        SKILLS,
        ABILITIES,
        WEAPON,
        ITEM,
        POWER,
        EFFECT,
        FEAT,
        CONDITION,
        END
    }

    protected static Header headerMap(int i) {
        Header ret;
        switch (i) {
            case 0:
                ret = Header.BASE_DATA;
                break;
            case 1:
                ret = Header.STATS;
                break;
            case 2:
                ret = Header.SKILLS;
                break;
            case 3:
                ret = Header.ABILITIES;
                break;
            case 4:
                ret = Header.WEAPON;
                break;
            case 5:
                ret = Header.ITEM;
                break;
            case 6:
                ret = Header.POWER;
                break;
            case 7:
                ret = Header.EFFECT;
                break;
            case 8:
                ret = Header.FEAT;
                break;
            case 9:
                ret = Header.CONDITION;
                break;
            case 10:
            default:
                ret = Header.END;
                break;
        }
        return ret;
    }

    protected static final String[] headerStr = new String[]{
            "base data",
            "stats",
            "skills",
            "abilities",
            "weapon",
            "item",
            "ability",
            "effect",
            "feat",
            "condition",
            "end"};

    protected Scanner fileScanner;
    protected boolean eof;
    protected Pattern header;
    protected Pattern score;
    protected Pattern key;
    protected TokenType nextType;
    protected String nextToken;
    protected String assignment = "=";
    protected String[] headerDelims = new String[]{"\\[", "\\]"};
    protected String blankLine = "^\\w*\\n";

    public ActorFileLexer(String filePath) {
        eof = false;
        try {
            fileScanner = new Scanner(new FileInputStream(filePath));
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found : " + filePath);
            fnfe.printStackTrace(System.err);
            eof = true;
        }
        header = Pattern.compile(headerDelims[0] + "[\\w ]+" + headerDelims[1] + "");
        score = Pattern.compile("\\d+");
        key = Pattern.compile("\\w+" + assignment);
    }

    public boolean endOfInput() {
        return eof;
    }

    public String sanitise(String str) {
        return sanitise(str, '\\');
    }

    public String sanitise(String str, char c) {
        String ret = new String();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != c) {
                ret += str.charAt(i);
            }
        }
        return ret;
    }

    public String nextDump() {
        String ret = nextType + " : " + nextToken;
        return ret;
    }

    public Header nextHeader() {
        Header ret = Header.END;
        String token;
        if (nextType == TokenType.HEADER) {
            int beg, end;
            beg = nextToken.indexOf(sanitise(headerDelims[0])) + 1;
            end = nextToken.lastIndexOf(sanitise(headerDelims[1]));
            if (beg == -1 || end == -1) {
                System.err.println("Invalid token substring : [" + beg + "," + end + "] : " + nextToken);
                token = nextToken;
            } else {
                token = nextToken.substring(beg, end);
            }
            for (int i = 0; i < headerStr.length; i++) {
                if (headerStr[i].length() == token.length() && token.compareTo(headerStr[i]) == 0) {
                    ret = headerMap(i);
                    break;
                }
            }
        } else {
            System.err.println("TokenType is not a 'header' and cannot be acquired as such! " + nextDump());
            ret = Header.END;
        }
        return ret;
    }

    public int nextScore() {
        int ret;
        if (nextType == TokenType.SCORE) {
            ret = Integer.parseInt(nextToken);
        } else {
            System.err.println("TokenType is not a 'score' and cannot be acquired as such!" + nextDump());
            ret = Integer.MIN_VALUE;
        }
        return ret;
    }

    public String nextKey() {
        String ret;
        int indexOf;
        if (nextType == TokenType.KEY) {
            indexOf = nextToken.indexOf(sanitise(assignment));
            if (indexOf == -1) {
                System.err.println("Invalid token substring : " + indexOf + " : " + nextToken);
                ret = nextToken;
            } else {
                ret = nextToken.substring(0, indexOf);
            }
        } else {
            System.err.println("TokenType is not 'key' and cannot be acquired as such!" + nextDump());
            ret = null;
        }
        return ret;
    }

    public String nextToken() {
        return nextToken;
    }

    public TokenType nextTokenType() {
        if (endOfInput()) {
            nextType = TokenType.NONE;
        } else {
            if ((nextToken = fileScanner.findInLine(header)) != null) {
                nextType = TokenType.HEADER;
            } else if ((nextToken = fileScanner.findInLine(key)) != null) {
                nextType = TokenType.KEY;
            } else if ((nextToken = fileScanner.findInLine(score)) != null) {
                nextType = TokenType.SCORE;
            } else {
                // kleene pattern if for some reason I need it later.
                // kleene = Pattern.compile("[. ]*\\n");
                nextToken = fileScanner.nextLine();
                nextType = TokenType.KLEENE;
                // empty line
                if (nextToken.compareTo("") == 0) {
                    nextType = nextTokenType();
                }
            }
            if (!fileScanner.hasNext()) {
                eof = true;
            }
        }
        return nextType;
    }
}
