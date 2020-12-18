package belstu.diploma.mobile.mangabook.model;

public class Bookmark {

    private int bookmarkId;
    private int bookmarkPage;
    private String bookmarkType;
    private int bookID;
    private int chapterId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public int getBookID() {
        return bookID;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public int getBookmarkPage() {
        return bookmarkPage;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getBookmarkType() {
        return bookmarkType;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookmarkPage(int bookmarkPage) {
        this.bookmarkPage = bookmarkPage;
    }

    public void setBookmarkType(String bookmarkType) {
        this.bookmarkType = bookmarkType;
    }

    //BOOKMARK TABLE
    public static final String TABLE_BOOKMARK = "BOOKMARK";
    public static final String COLUMN_BOOKMARK_ID = "BOOKMARK_ID";
    public static final String COLUMN_BOOKMARK_PAGE = "BOOKMARK_PAGE";
    public static final String COLUMN_BOOKMARK_TYPE = "BOOKMARK_TYPE";

    public static final String CREATE_TABLE_BOOKMARK = "CREATE TABLE " + TABLE_BOOKMARK + "(" +
            COLUMN_BOOKMARK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_BOOKMARK_PAGE + " TEXT, " +
            COLUMN_BOOKMARK_TYPE + " TEXT, " +
            Book.COLUMN_BOOK_ID + " INTEGER, " +
            Chapter.COLUMN_CHAPTER_ID + " INTEGER, " +
            User.COLUMN_USER_ID + " INTEGER, " +
            "FOREIGN KEY ("+ Book.COLUMN_BOOK_ID + ") REFERENCES "
            + Book.TABLE_BOOK +"(" + Book.COLUMN_BOOK_ID +")," +
            "FOREIGN KEY ("+ Chapter.COLUMN_CHAPTER_ID + ") REFERENCES "
            + Chapter.TABLE_CHAPTER +"(" + Chapter.COLUMN_CHAPTER_ID +")," +
            "FOREIGN KEY ("+ User.COLUMN_USER_ID + ") REFERENCES "
            + User.TABLE_USER +"(" + User.COLUMN_USER_ID +")" +
            ");";
}
