package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class SectionsContainer implements Serializable {
    // TreeMap for unknown sizes and lookup by the CRN
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

    @Override
    public String toString() {
        return "SectionsContainer{" +
                "sections=" + sections.toString() +
                ", numOfSections=" + sections.size() +
                '}';
    }


}
