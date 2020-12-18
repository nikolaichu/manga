package belstu.diploma.mobile.mangabook.model;

import java.io.Serializable;

public class Author implements Serializable {
    private int authorId;
    private String authorNameRus;
    private String authorNameEng;
    private String authorDescription;

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public String getAuthorNameEng() {
        return authorNameEng;
    }

    public String getAuthorNameRus() {
        return authorNameRus;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

    public void setAuthorNameEng(String authorNameEng) {
        this.authorNameEng = authorNameEng;
    }

    public void setAuthorNameRus(String authorNameRus) {
        this.authorNameRus = authorNameRus;
    }

    public static final String TABLE_AUTHOR = "AUTHOR";
    public static final String COLUMN_AUTHOR_ID = "AUTHOR_ID";
    public static final String COLUMN_AUTHOR_NAME_RUS = "AUTHOR_NAME_RUS";
    public static final String COLUMN_AUTHOR_NAME_ENG = "AUTHOR_NAME_ENG";
    public static final String COLUMN_AUTHOR_DESCRIPTION = "AUTHOR_DESCRIPTION";

    public static final String CREATE_TABLE_AUTHOR =  "CREATE TABLE " + TABLE_AUTHOR + "(" +
            COLUMN_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_AUTHOR_NAME_RUS + " TEXT, " +
            COLUMN_AUTHOR_NAME_ENG + " TEXT, " +
            COLUMN_AUTHOR_DESCRIPTION + " TEXT);";
}
