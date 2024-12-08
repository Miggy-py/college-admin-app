package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class SectionsContainer implements Serializable {
    private TreeMap<String, Section> sections;

    public SectionsContainer() {
        sections = new TreeMap<>();
    }

    public void addSection(Section section) {
        sections.put(section.getCrn(),section);
    }

    public TreeMap<String, Section> getSections() {
        return sections;
    }

    public ArrayList<String> getAllCRNs() {
        return Utility.getAllOfFrom(sections, Section::getCrn);
    }

    /*
    public SectionsContainer() {
        sections = new GenericBag<>(Section.class, SchoolSettings.MAX_SECTION_SIZE.getSize());
        numOfSections = 0;
    }

    public Section[] getSectionsOfMajor(Major major) {
        return sections.search(Section.class, section -> section.getMajor().equals(major));
    }

    public Section[] getAllSections() {
        return sections.search(Section.class, section -> true);
    }

    public void addSection(Section section) {
        sections.add(section);
        numOfSections++;
    }

    public Section[] removeSection(String crn) {
        return sections.removeCopy(Section.class, section -> section.getCrn().equals(crn));
    }

    public void displaySections() {
        sections.display();
    }

    public int getNumOfSections() {
        return numOfSections;
    }

     */

    @Override
    public String toString() {
        return "SectionsContainer{" +
                "sections=" + sections.toString() +
                ", numOfSections=" + sections.size() +
                '}';
    }


}
