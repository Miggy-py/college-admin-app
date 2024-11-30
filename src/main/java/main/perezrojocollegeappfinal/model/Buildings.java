package main.perezrojocollegeappfinal.model;

public enum Buildings {
    RI("Riverhead"),
    IS("Islip Arts"),
    SM("Smithtown Science"),
    SO("Southampton"),
    WJL("William J Lindsay Life Sciences");

    private String name;

    Buildings(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
