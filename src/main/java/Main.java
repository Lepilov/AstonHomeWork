import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        // создаём экземпляр ObjectMapper класса Jackson
        new ObjectMapper().

                // читает JSON и десериализуем в тип <List<Student>
                readValue(Main.class.getResourceAsStream("/students.json"), // загружаем файл students.json из classpath
                        new TypeReference<List<Student>>() {})

                // переводим полученный List<Student> в поток Stream<Student>
                .stream()

                // для каждого элемента потока вызывает System.out.println(student) переопределенный toString().
                .peek(System.out::println)

                // для каждого студента (s) берём список книг (s.getBooks()),
                // переводим в Stream<Book> и разворачиваем все эти потоки в один общий Stream<Book>
                .flatMap(s -> s.getBooks().stream())

                // сортируем книги по количеству страниц по возрастанию
                .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))

                // убираем дубликаты из потока
                // сравниваем объекты через переопределенные equals() и hashCode().
                .distinct()

                // оставляем в потоке только книги, у которых год выпуска больше 2000
                .filter(b -> b.getYear() > 2000)

                // ограничиваем поток первыми тремя элементами
                .limit(3)

                // преобразуем каждый элемент потока из Book в book.getYear() год выпуска.
                .map(Book::getYear)

                // возвращаем Optional<Integer> с первым элементом потока
                .findFirst()

                // метод Optional, который выполняет одно из двух действий:
                .ifPresentOrElse(
                        // Consumer<Integer> — выполняется, если Optional содержит значение
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        // Runnable — выполняется, если Optional пуст
                        () -> System.out.println("Такая книга отсутствует")
                );
    }
}
