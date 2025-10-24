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
                writer.write(books[i].getId() + "," + books[i].getTitle() + "," + books[i].getAuthor() + "," + books[i].getYear() + "," + books[i].getGenre());
                writer.newLine();
            }
            System.out.println("Сохранено в файл: " + filename);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFromFile(String filename) {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String title = data[1];
                    String author = data[2];
                    int year = Integer.parseInt(data[3]);
                    String genre = data[4];
                    Book book = new Book(title, author, year, genre, id);
                    addBook(book); // Добавляем книгу в библиотеку
                }
            }
            System.out.println("Загружено книг из файла: " + filename);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}