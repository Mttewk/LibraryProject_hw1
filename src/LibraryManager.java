public class LibraryManager {
    private Book[] books;
    private int bookCount;

    public LibraryManager(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
            System.out.println("Книга добавлена: " + book);
        } else {
            System.out.println("Библиотека полна, невозможно добавить книгу.");
        }
    }

    //  метод для проверки существования книги по ID
    public boolean bookExists(String id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public void editBook(String id, String title, String author, int year, String genre) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equals(id)) {
                books[i].setTitle(title);
                books[i].setAuthor(author);
                books[i].setYear(year);
                books[i].setGenre(genre);
                System.out.println("Книга отредактирована: " + books[i]);
                return;
            }
        }
        System.out.println("Книга с ID " + id + " не найдена.");
    }

    public void printAllBooks() {
        if (bookCount == 0) {
            System.out.println("Библиотека пуста.");
        } else {
            System.out.println("Список книг:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println(books[i]);
            }
        }
    }


    public void findBook(String title, String author, String genre) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().contains(title) && books[i].getAuthor().contains(author) && books[i].getGenre().contains(genre)) {
                System.out.println("Найдено совпадение: " + books[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Книги не найдены.");
        }
    }


    public void saveToFile(String filename) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filename))) {
            for (int i = 0; i < bookCount; i++) {
                // Сохраняем в том же формате: название, автор, год, жанр
                writer.write(books[i].getTitle() + ", " + books[i].getAuthor() + ", " + books[i].getYear() + ", " + books[i].getGenre());
                writer.newLine();
            }
            System.out.println("Сохранено в файл: " + filename + " (" + bookCount + " книг)");
        } catch (java.io.IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }


    public void loadFromFile(String filename) {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename))) {
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
        } catch (java.io.IOException e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}