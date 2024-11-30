package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.Arrays;

public class TextbookContainer implements Serializable {
    private GenericBag<Textbook> backpack;

    public TextbookContainer() {
        backpack = new GenericBag<>(Textbook.class, Settings.MAX_TEXTBOOKS_PER_SECTION.getSize());
    }

    public GenericBag<Textbook> getBackpack() {
        return backpack;
    }

    public void addBook(Textbook book) {
        backpack.add(book);
    }

    @Override
    public String toString() {
        return "TextbookContainer{" +
                "Course Textbooks=" + Arrays.toString(backpack.search(Textbook.class, textbook -> true)) +
                '}';
    }
}
