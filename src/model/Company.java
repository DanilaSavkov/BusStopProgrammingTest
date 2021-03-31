package model;

public enum Company {
    POSH("Posh", 2), GROTTY("Grotty", 1);

    private final String name;
    private final int comfortRate;

    Company(String name, int comfortRate) {
        this.name = name;
        this.comfortRate = comfortRate;
    }

    public String getName() {
        return name;
    }

    public int getComfortRate() {
        return comfortRate;
    }
}
