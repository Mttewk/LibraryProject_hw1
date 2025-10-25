import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LibraryManager libraryManager = new LibraryManager();

        while (true) {
            // Печать меню
            System.out.println("\nМеню:");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Редактировать книгу");
            System.out.println("3. Вывести список книг");
            System.out.println("4. Найти книгу");
            System.out.println("5. Сохранить в файл");
            System.out.println("6. Загрузить из файла");
            System.out.println("7. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    // Добавление книги
                    System.out.print("Введите название книги: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите автора книги: ");
                    String author = scanner.nextLine();

                    int year = 0;
                    boolean validYear = false;
                    while (!validYear) {
                        System.out.print("Введите год издания: ");
                        try {
                            year = scanner.nextInt();
                            validYear = true;
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Ошибка! Год должен быть числом. Попробуйте снова.");
                            scanner.nextLine();
                        }
                    }
                    scanner.nextLine();

                    System.out.print("Введите жанр книги: ");
                    String genre = scanner.nextLine();

                    Book book = new Book(title, author, year, genre);
                    libraryManager.addBook(book);
                    System.out.println("Книге присвоен ID: " + book.getId());
                    break;
                }
                case 2: {
                    // Редактирование книги
                    System.out.print("Введите ID книги для редактирования: ");
                    String id = scanner.nextLine();

                    if (!libraryManager.bookExists(id)) {
                        System.out.println("Книга с ID " + id + " не найдена.");
                        break;
                    }

                    System.out.print("Введите новое название книги: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите нового автора книги: ");
                    String author = scanner.nextLine();

                    int year = 0;
                    boolean validYear = false;
                    while (!validYear) {
                        System.out.print("Введите новый год издания: ");
                        try {
                            year = scanner.nextInt();
                            validYear = true;
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Ошибка! Год должен быть числом. Попробуйте снова.");
                            scanner.nextLine();
                        }
                    }
                    scanner.nextLine();

                    System.out.print("Введите новый жанр книги: ");
                    String genre = scanner.nextLine();

                    libraryManager.editBook(id, title, author, year, genre);
                    break;
                }
                case 3: {
                    // Вывод списка книг
                    libraryManager.printAllBooks();
                    break;
                }
                case 4: {
                    // Нахождение книги
                    System.out.print("Введите название книги для поиска: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите автора книги для поиска: ");
                    String author = scanner.nextLine();
                    System.out.print("Введите жанр книги для поиска: ");
                    String genre = scanner.nextLine();

                    libraryManager.findBook(title, author, genre);
                    break;
                }
                case 5: {
                    // Сохранение книг в файл
                    System.out.print("Введите имя файла для сохранения: ");
                    String filename = scanner.nextLine();
                    libraryManager.saveToFile(filename);
                    break;
                }
                case 6: {
                    // Загрузка книг из файла
                    System.out.print("Введите имя файла для загрузки: ");
                    String filename = scanner.nextLine();
                    libraryManager.loadFromFile(filename);
                    break;
                }
                case 7: {
                    // Выход из программы
                    System.out.println("Выход из программы...");
                    scanner.close();
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Неверный выбор! Пожалуйста, выберите правильную опцию.");
                    break;
                }
            }
        }
    }
}