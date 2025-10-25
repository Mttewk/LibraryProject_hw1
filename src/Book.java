public class Book {
    private String title;
    private String author;
    private int year;
    private String genre;
    private String id;

    private static int idCounter = 1;

    public static void setIdCounter(int newValue) {
        idCounter = newValue;
    }

    // Конструктор для создания книги
    public Book(String title, String author, int year, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.id = generateId();
    }

    // Конструктор для загрузки из файла
    public Book(String title, String author, int year, String genre, String id) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.id = id;

        updateIdCounter(id);
    }

    // Метод для автоматической генерации ID
    private String generateId() {
        return String.format("%04d", idCounter++);
    }

    // Метод для обновления счетчика при загрузке из файла
    private static void updateIdCounter(String id) {
        try {
            int num = Integer.parseInt(id);
            if (num >= idCounter) {
                idCounter = num + 1;
            }
        } catch (NumberFormatException e) {
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + title + ", Автор: " + author + ", Год: " + year + ", Жанр: " + genre;
    }
}