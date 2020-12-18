package belstu.diploma.mobile.mangabook.model;

import java.io.Serializable;

public class Chapter implements Serializable {

    private int chapterId;
    private int chapterNumber;
    private String chapterNameRus;
    private String chapterFolder;
    private int chapterCount;

    public int getChapterCount() {
        return chapterCount;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }

    public long getChapterId() {
        return chapterId;
    }

    public String getChapterFolder() {
        return chapterFolder;
    }

    public String getChapterNameRus() {
        return chapterNameRus;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterFolder(String chapterFolder) {
        this.chapterFolder = chapterFolder;
    }

    public void setChapterNameRus(String chapterNameRus) {
        this.chapterNameRus = chapterNameRus;
    }


    public static final String TABLE_CHAPTER = "CHAPTER";
    public static final String COLUMN_CHAPTER_ID = "CHAPTER_ID";
    public static final String COLUMN_CHAPTER_NUMBER = "CHAPTER_NUMBER";
    public static final String COLUMN_CHAPTER_NAME_RUS = "CHAPTER_NAME_RUS";
    public static final String COLUMN_CHAPTER_COUNT_PAGES = "CHAPTER_COUNT_PAGES";

    public static final String COLUMN_CHAPTER_FOLDER = "CHAPTER_FOLDER";

    public static final String CREATE_TABLE_CHAPTER = "CREATE TABLE " + TABLE_CHAPTER + "(" +
            COLUMN_CHAPTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_CHAPTER_NUMBER + " INTEGER, " +
            COLUMN_CHAPTER_NAME_RUS + " TEXT, " +
            COLUMN_CHAPTER_FOLDER + " TEXT," +
            COLUMN_CHAPTER_COUNT_PAGES + " INTEGER, " +
            Book.COLUMN_BOOK_ID + " INTEGER, " +
            "FOREIGN KEY ("+ Book.COLUMN_BOOK_ID + ") REFERENCES "
            + Book.TABLE_BOOK +"(" + Book.COLUMN_BOOK_ID +")" +
            ");";
}
