package main.perezrojocollegeappfinal.config;

public enum ThemeConfiguration {
    NPCC("North Pole Community College", "#FF0000 ", "#00FF00", "src/main/resources/images/NPCC_logo.jpg", "npcc");

    private final String nameOfSchool;
    private final String primaryColor;
    private final String secondaryColor;
    private final String logoPath;
    private final String emailAt;

    ThemeConfiguration(String name, String primaryColor, String secondaryColor, String logoPath, String emailAt) {
        this.nameOfSchool = name;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.logoPath = logoPath;
        this.emailAt = emailAt;
    }

    public String getNameOfSchool() {
        return nameOfSchool;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getEmailAt() {
        return emailAt;
    }
}
