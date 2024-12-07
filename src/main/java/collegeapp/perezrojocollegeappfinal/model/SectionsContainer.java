package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class SectionsContainer implements Serializable {
    private TreeMap<String, Section> sectionBag;

    public SectionsContainer() {
        sectionBag = new TreeMap<>();
    }

    public void addSection(Section section) {
        sectionBag.put(section.getCrn(),section);
    }

    public TreeMap<String, Section> getSectionBag() {
        return sectionBag;
    }

    /*
    public SectionsContainer() {
        sectionBag = new GenericBag<>(Section.class, SchoolSettings.MAX_SECTION_SIZE.getSize());
        numOfSections = 0;
    }

    public Section[] getSectionsOfMajor(Major major) {
        return sectionBag.search(Section.class, section -> section.getMajor().equals(major));
    }

    public Section[] getAllSections() {
        return sectionBag.search(Section.class, section -> true);
    }

    public void addSection(Section section) {
        sectionBag.add(section);
        numOfSections++;
    }

    public Section[] removeSection(String crn) {
        return sectionBag.removeCopy(Section.class, section -> section.getCrn().equals(crn));
    }

    public void displaySections() {
        sectionBag.display();
    }

    public int getNumOfSections() {
        return numOfSections;
    }

     */

    @Override
    public String toString() {
        return "SectionsContainer{" +
                "sectionBag=" + sectionBag.toString() +
                ", numOfSections=" + sectionBag.size() +
                '}';
    }


}
