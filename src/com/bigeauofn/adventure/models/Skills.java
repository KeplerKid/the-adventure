package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.utilities.ListUtility;
import com.bigeauofn.adventure.utilities.StringUtility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Skills {
    private HashMap<String, Skill> skillList;
    private Actor actor;

    public Skills(Actor actor) {
        this.actor = actor;
        skillList = new HashMap<>();
        this.loadBaseSkills();
    }

    public Skill getSkill(String skillName) {
        return skillList.get(skillName);
    }

    public void addTrainedSkills(HashMap<String, Boolean> tempSolution) {
        for (String s : tempSolution.keySet()) {
            if (tempSolution.get(s)) {
                this.getSkill(s).setTrained();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        List<String> keyList = ListUtility.asSortedList(this.skillList.keySet());
        int maxSkillNameLength = StringUtility.maxStringLength(keyList);
        for (String s : keyList) {
            toReturn.append(this.skillList.get(s).toString(maxSkillNameLength, 2));
            toReturn.append('\n');
        }

        return toReturn.toString();
    }


    private void loadBaseSkills() {
        String path = "actors/Skill_List.txt";

        BufferedReader br = openFileForReading(path);

        try {
            String line;
            while ((line = br.readLine()) != null) {

                if (!line.startsWith(";") && line.contains("=")) {

                    // add a new KV pair
                    int splitter = line.indexOf("=");
                    String abilityName = line.substring(1, splitter - 1);
                    String values = line.substring(splitter + 1, line.length());
                    ArrayList<String> namesOfSkills;
                    namesOfSkills = new ArrayList<>(Arrays.asList(values.split(",")));
                    for (String s : namesOfSkills) {
                        Skill toPut = new Skill(s, this.actor, abilityName, "skill");
                        this.skillList.put(s, toPut);
                    }

                } else {
                    continue;
                    // do nothing, ; indicates comment
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedReader openFileForReading(String fileNameAndPath) {
        // create the file reader
        FileReader fr = null;
        try {
            fr = new FileReader(fileNameAndPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new BufferedReader(fr);
    }

    public boolean isNewThing(String line) {
        return line.startsWith("[");
    }


}
