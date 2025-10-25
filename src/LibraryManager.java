import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class LibraryManager {
    private List<Book> books;

    public LibraryManager() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Книга добавлена: " + book);
    }

    // метод для проверки существования книги по ID
    public boolean bookExists(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void editBook(String id, String title, String author, int year, String genre) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setYear(year);
                book.setGenre(genre);
                System.out.println("Книга отредактирована: " + book);
                return;
            }
        }
        System.out.println("Книга с ID " + id + " не найдена.");
    }

    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста.");
        } else {
            System.out.println("Список книг:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void findBook(String title, String author, String genre) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().contains(title) && book.getAuthor().contains(author) && book.getGenre().contains(genre)) {
                System.out.println("Найдено совпадение: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Книги не найдены.");
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : books) {
                // Сохраняем в том же формате: название, автор, год, жанр
                writer.write(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear() + ", " + book.getGenre());
                writer.newLine();
            }
            System.out.println("Сохранено в файл: " + filename + " (" + books.size() + " книг)");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int loadedCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",\\s*", 4);
                if (data.length == 4) {
                    try {
                        String title = data[0].trim();
                        String author = data[1].trim();
                        int year = Integer.parseInt(data[2].trim());
                        String genre = data[3].trim();

                        // Создаем книгу - ID сгенерируется автоматически
                        Book book = new Book(title, author, year, genre);
                        addBook(book);
                        loadedCount++;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка в формате года издания в строке: " + line);
                    }
                } else {
                    System.out.println("Некорректный формат строки: " + line);
                }
            }

            System.out.println("Загружено книг из файла: " + filename + " (" + loadedCount + " книг)");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}