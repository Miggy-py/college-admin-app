package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.Random;

public class Textbook implements Comparable<Textbook>, Serializable {
    private String title;
    private Name author;
    private String ISBN;

    public Textbook(String title, Name author) {
        this.title = title;
        this.author = author;
        this.ISBN = generateISBN13();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Name getAuthor() {
        return author;
    }

    public void setAuthor(Name author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    /*
    ISBNs are actually made using a mathematical equation
    The ends of them are a check digit
     */
    public static String generateISBN13() {
        Random random = new Random();
        int[] isbn = new int[12];

        // Makes random first 12 digits
        for (int i = 0; i < 12; i++) {
            isbn[i] = random.nextInt(10);
        }

        // This is the check digit
        int checksum = 0;
        for (int i = 0; i < 12; i++) {
            int weight = (i % 2 == 0) ? 1 : 3;
            checksum += isbn[i] * weight;
        }
        checksum = (10 - (checksum % 10)) % 10;

        StringBuilder isbn13 = new StringBuilder();
        for (int i : isbn) {
            isbn13.append(i);
        }
        isbn13.append(checksum);

        return isbn13.toString();
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }

    @Override
    public int compareTo(Textbook o) {
        return title.compareTo(o.title);
    }
}
