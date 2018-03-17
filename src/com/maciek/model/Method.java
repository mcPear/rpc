package com.maciek.model;

import java.util.Vector;

/**
 * Created by maciej on 04.03.18.
 */
public class Method {
    public String name;
    public String fullName;
    public Vector<String> paramTypes;
    public String description;

    public Method(String name, String fullName, Vector<String> paramTypes, String description) {
        this.name = name;
        this.fullName = fullName;
        this.paramTypes = paramTypes;
        this.description = description;
    }

    @Override
    public String toString() {
        return "method " + fullName + " - " + description;
    }
}
