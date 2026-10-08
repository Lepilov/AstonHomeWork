import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int pages;
    private int year;

    @JsonCreator
    public Book(
            @JsonProperty("title")  String title,
            @JsonProperty("author") String author,
            @JsonProperty("pages")  int pages,
            @JsonProperty("year")   int year){
        this.title  = title;
        this.author = author;
        this.pages  = pages;
        this.year   = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', pages=%d, year=%d}\n", title, author, pages, year);
    }

    // equals/hashCode нужны для работы distinct() и уникальности книг
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages, year);
    }
}
