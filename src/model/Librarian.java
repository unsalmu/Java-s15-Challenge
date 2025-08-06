package model;

public class Librarian extends Person {
    private final String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() { return password; }

    @Override
    public String whoYouAre() {
        return "Librarian " + getName();
    }
}