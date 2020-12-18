package belstu.diploma.mobile.mangabook.model;

import java.io.Serializable;


public class Book implements Serializable {

    private int bookId;
    private String bookNameRus;
    private String bookNameEng;
    private String bookNameOrig;
    private String bookNameAlt;
    private String bookType;
    private String bookYear;
    private String bookDescription;
    private String cover;
    private int authorId;
    private int translatorId;
    private int userId;

    public Book() {

    }

    public int getAuthorId() {
        return authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBookYear() {
        return bookYear;
    }

    public int getTranslatorId() {
        return translatorId;
    }

    public int getUserId() {
        return userId;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookNameAlt() {
        return bookNameAlt;
    }

    public String getBookNameEng() {
        return bookNameEng;
    }

    public String getBookNameOrig() {
        return bookNameOrig;
    }

    public String getBookNameRus() {
        return bookNameRus;
    }

    public String getBookType() {
        return bookType;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookNameAlt(String bookNameAlt) {
        this.bookNameAlt = bookNameAlt;
    }

    public void setBookNameEng(String bookNameEng) {
        this.bookNameEng = bookNameEng;
    }

    public void setBookNameOrig(String bookNameOrig) {
        this.bookNameOrig = bookNameOrig;
    }

    public void setBookNameRus(String bookNameRus) {
        this.bookNameRus = bookNameRus;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public void setTranslatorId(int translatorId) {
        this.translatorId = translatorId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //BOOK TABLE
    public static final String TABLE_BOOK = "BOOK";
    public static final String COLUMN_BOOK_ID = "BOOK_ID";
    public static final String COLUMN_BOOK_NAME_RUS = "BOOK_NAME_RUS";
    public static final String COLUMN_BOOK_NAME_ENG = "BOOK_NAME_ENG";
    public static final String COLUMN_BOOK_NAME_ORIG = "BOOK_NAME_ORIG";
    public static final String COLUMN_BOOK_NAME_ALT = "BOOK_NAME_ALT";
    public static final String COLUMN_BOOK_TYPE = "BOOK_TYPE";
    public static final String COLUMN_BOOK_YEAR = "BOOK_YEAR";
    public static final String COLUMN_BOOK_COVER = "BOOK_COVER";
    public static final String COLUMN_BOOK_DESCRIPTION = "BOOK_DESCRIPTION";

    public static final String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK + "(" +
            COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_BOOK_NAME_RUS + " TEXT, " +
            COLUMN_BOOK_NAME_ENG + " TEXT, " +
            COLUMN_BOOK_NAME_ORIG + " TEXT, " +
            COLUMN_BOOK_NAME_ALT + " TEXT, " +
            COLUMN_BOOK_TYPE + " TEXT, " +
            COLUMN_BOOK_YEAR + " TEXT, " +
            COLUMN_BOOK_COVER + " TEXT, " +
            COLUMN_BOOK_DESCRIPTION + " TEXT, " +
            Author.COLUMN_AUTHOR_ID + " INTEGER, " +
            Translator.COLUMN_TRANSLATOR_ID + " INTEGER, " +
            User.COLUMN_USER_ID + " INTEGER" +
//            ", " +
//            "FOREIGN KEY ("+ Author.COLUMN_AUTHOR_ID + ") REFERENCES "
//            + Author.TABLE_AUTHOR +"(" + Author.COLUMN_AUTHOR_ID +")," +
//            "FOREIGN KEY ("+ Translator.COLUMN_TRANSLATOR_ID + ") REFERENCES "
//            + Translator.TABLE_TRANSLATOR +"(" + Translator.COLUMN_TRANSLATOR_ID +")," +
//            "FOREIGN KEY ("+ User.COLUMN_USER_ID + ") REFERENCES "
//            + User.TABLE_USER +"(" + User.COLUMN_USER_ID +")" +
            ");";
}
