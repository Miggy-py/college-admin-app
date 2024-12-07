package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;

public enum Buildings implements Serializable {
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
