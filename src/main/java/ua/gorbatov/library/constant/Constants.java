package ua.gorbatov.library.constant;

public class Constants {
    public static final String USER = "user";
    public static final String LIBRARIAN = "librarian";
    public static final String ADMIN = "admin";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LOCALE = "locale";
    public static final String ROLE = "role";
    public static final String PAGE = "page";
    public static final String SORT = "sort";
    public static final String SORT_DIR = "sortDir";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";
    public static final int ONE = 1;
    public static final int SIX = 6;
    public static final double ONE_DOUBLE = 1.0;
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    public static final String FIND_BOOKS_SORT_TITLE_ASC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY title ASC LIMIT ?, ?";
    public static final String FIND_BOOKS_SORT_AUTHOR_ASC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY author ASC LIMIT ?, ?";
    public static final String FIND_BOOKS_SORT_PUBLISHER_ASC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY publisher ASC LIMIT ?, ?";
    public static final String FIND_BOOKS_SORT_DATE_ASC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY publish_date ASC LIMIT ?, ?";

    public static final String FIND_BOOKS_SORT_TITLE_DESC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY title DESC LIMIT ?, ?";
    public static final String FIND_BOOKS_SORT_AUTHOR_DESC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY author DESC LIMIT ?, ?";
    public static final String FIND_BOOKS_SORT_PUBLISHER_DESC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY publisher DESC LIMIT ?, ?";
    public static final String FIND_BOOKS_SORT_DATE_DESC = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY publish_date DESC LIMIT ?, ?";

    public static final String FIND_BOOKS = "SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY id ASC LIMIT ?, ?";
}
