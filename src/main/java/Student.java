import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Student {
    private String name;
    private List<Book> books;

    @JsonCreator
    public Student(
            @JsonProperty("name") String name,
            @JsonProperty("books") List<Book> books){
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return String.format("Student { name = '%s',\n books = \n %s}", name, books);
    }
}
