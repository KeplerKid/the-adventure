package com.bigeauofn.adventure.models;

public class ActorFileParser {

    protected ActorFileParser() {
    }

    public void print() {
        System.out.println("Hi! ^_^");
    }

    protected static Actor parseFile(ActorFileLexer lexer) {
        Actor ret = new Actor(new ActorFileParser());
        // TODO
        return null;
    }

    public static Actor parseFile(String filePath) {
        Actor ret;
        ActorFileLexer lexer = new ActorFileLexer(filePath);

        if (lexer.endOfInput()) {
            // There's been an error
            return null;
        }

        ret = parseFile(lexer);

        return ret;
    }

    public static void writeToFile(String filePath, Actor actor) {

    }
}
