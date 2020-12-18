package belstu.diploma.mobile.mangabook.model;

public class Translator {

    private int translatorId;
    private String translatorName;
    private String translatorSite;
    private String translatorDescription;

    public int getTranslatorId() {
        return translatorId;
    }

    public String getTranslatorDescription() {
        return translatorDescription;
    }

    public String getTranslatorName() {
        return translatorName;
    }

    public String getTranslatorSite() {
        return translatorSite;
    }

    public void setTranslatorId(int translatorId) {
        this.translatorId = translatorId;
    }

    public void setTranslatorDescription(String translatorDescription) {
        this.translatorDescription = translatorDescription;
    }

    public void setTranslatorName(String translatorName) {
        this.translatorName = translatorName;
    }

    public void setTranslatorSite(String translatorSite) {
        this.translatorSite = translatorSite;
    }

    public static final String TABLE_TRANSLATOR = "TRANSLATOR";
    public static final String COLUMN_TRANSLATOR_ID = "TRANSLATOR_ID";
    public static final String COLUMN_TRANSLATOR_NAME = "TRANSLATOR_NAME";
    public static final String COLUMN_TRANSLATOR_SITE = "TRANSLATOR_SITE";
    public static final String COLUMN_TRANSLATOR_DESCRIPTION = "TRANSLATOR_DESCRIPTION";

    public static final String CREATE_TABLE_TRANSLATOR =  "CREATE TABLE " + TABLE_TRANSLATOR + "(" +
            COLUMN_TRANSLATOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TRANSLATOR_NAME + " TEXT, " +
            COLUMN_TRANSLATOR_SITE + " TEXT, " +
            COLUMN_TRANSLATOR_DESCRIPTION + " TEXT);";
}
