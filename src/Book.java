public class Book {
    private String title;  // Название книги
    private String author; // Автор книги
    private int year;      // Год издания
    private String genre;  // Жанр книги
    private String id;     // Уникальный идентификатор книги



    // Статический счетчик для автоматической генерации ID
    private static int idCounter = 1;

    // Конструктор для создания книги
    public Book(String title, String author, int year, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.id = generateId();
    }

    // Конструктор для загрузки из файла (с уже существующим ID)
    public Book(String title, String author, int year, String genre, String id) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.id = id;
        // Обновляем счетчик, если загруженный ID больше текущего
        updateIdCounter(id);
    }

    // Метод для автоматической генерации ID
    private String generateId() {
        return String.format("%04d", idCounter++);
    }

    // Метод для обновления счетчика при загрузке из файла
    private static void updateIdCounter(String id) {
        if (id.startsWith("B")) {
            try {
                int num = Integer.parseInt(id.substring(1));
                if (num >= idCounter) {
                    idCounter = num + 1;
                }
            } catch (NumberFormatException e) {
                // Если не удалось распарсить, оставляем текущий счетчик
            }
        }
    }

    // Геттеры и сеттеры для всех полей

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