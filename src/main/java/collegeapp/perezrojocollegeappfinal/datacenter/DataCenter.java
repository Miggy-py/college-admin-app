package collegeapp.perezrojocollegeappfinal.datacenter;

import collegeapp.perezrojocollegeappfinal.config.SchoolSettings;
import collegeapp.perezrojocollegeappfinal.model.*;

import java.io.*;
import java.util.*;

public class DataCenter implements Serializable{
    private static DataCenter instance;
    private SchoolData schoolData;
    private int studentIDBase;
    private int instructorIDBase;
    // private int schoolIDBase; Not in use anymore, maybe in the future for greater endeavors
    private final ArrayList<String> FIRST_NAMES;
    private final ArrayList<String> LAST_NAMES;
    private final int SIZE_OF_FIRSTNAMES = 6000;
    private final int SIZE_OF_LASTNAMES = 90000;

    private DataCenter() {
        schoolData = new SchoolData();
        studentIDBase = SchoolSettings.STUDENT_ID_BASE.getSize();
        instructorIDBase = SchoolSettings.INSTRUCTOR_ID_BASE.getSize();
        // schoolIDBase = SchoolSettings.SCHOOL_ID_BASE.getSize();
        FIRST_NAMES = new ArrayList<>(SIZE_OF_FIRSTNAMES);
        LAST_NAMES = new ArrayList<>(SIZE_OF_LASTNAMES);
        loadNames();
    }

    public SchoolData getSchoolData() {
        return schoolData;
    }

    public int getStudentIDBase() {
        return ++studentIDBase;
    }

    public int getInstructorIDBase() {
        return ++instructorIDBase;
    }

    public ArrayList<String> getFIRST_NAMES() {
        return FIRST_NAMES;
    }

    public ArrayList<String> getLAST_NAMES() {
        return LAST_NAMES;
    }

    private void loadNames() {
        try (Scanner scan = new Scanner(new File("first-names.txt"))) {
            while (scan.hasNextLine()) {
                FIRST_NAMES.add(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Scanner scan = new Scanner(new File("last-names.txt"))) {
            while (scan.hasNextLine()) {
                LAST_NAMES.add(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = load();
            if (instance == null) {
                instance = new DataCenter();
            }
        }

        return instance;
    }

    public static DataCenter load() {
        DataCenter inst = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/collegeapp/perezrojocollegeappfinal/datacenter/collegeData.dat"))) {
            inst = (DataCenter)ois.readObject();
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return inst;
    }

    public boolean save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/collegeapp/perezrojocollegeappfinal/datacenter/collegeData.dat"))) {
            oos.writeObject(instance);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}