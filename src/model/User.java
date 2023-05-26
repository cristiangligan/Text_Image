package model;

import java.awt.*;

public record User(String name, Image image) implements Comparable<User> {

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
