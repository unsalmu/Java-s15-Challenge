package model;

public abstract class Person {
    private String name;

    protected Person(String name) {
        this.name = name;
    }

    protected Person() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public abstract String whoYouAre();
}