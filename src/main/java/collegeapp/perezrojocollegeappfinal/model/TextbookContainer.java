package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.config.SchoolSettings;

import java.io.Serializable;
import java.util.ArrayList;

public class TextbookContainer implements Serializable {
    private ArrayList<Textbook> backpack;

    public TextbookContainer() {
        backpack = new ArrayList<>(SchoolSettings.MAX_TEXTBOOKS_PER_SECTION.getSize());
    }

    public ArrayList<Textbook> getBackpack() {
        return backpack;
    }

    public void addBook(Textbook book) {
        backpack.add(book);
    }

    /*
    @Override
    public String toString() {
        return "TextbookContainer{" +
                "Course Textbooks=" + Arrays.toString(backpack.search(Textbook.class, textbook -> true)) +
                '}';
    }

     */
}
