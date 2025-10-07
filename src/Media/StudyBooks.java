package Media;

import Person.Author;

import java.util.Objects;

public class StudyBooks extends Book {
    private String lesson;

    public StudyBooks(long book_ID, Author author, String name, double price, String edition, MediaType mediaType, String lesson) {
        super(book_ID, author, name, price, edition, mediaType);
        this.lesson = lesson;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public void display() {
        super.display();
        System.out.println("lesson: " + lesson);
    }

    public String toString() {
        return super.toString() +
                ", lesson=" + lesson;
    }
}