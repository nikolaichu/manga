package belstu.diploma.mobile.mangabook.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private long user_id;
    private String user_login;
    private String user_mail;
    private String user_password;
    private int user_isAdmin;
    private String user_name;
    private String user_lastname;
    private String user_sex;
    private Date user_date_of_birth;
    private String user_country;
    private String user_about;
    private String user_pic;

    public long getUser_id() {
        return user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public int getUser_isAdmin() {
        return user_isAdmin;
    }

    public String getUser_password() {
        return user_password;
    }

    public Date getUser_date_of_birth() {
        return user_date_of_birth;
    }

    public String getUser_about() {
        return user_about;
    }

    public String getUser_country() {
        return user_country;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public void setUser_isAdmin(int user_isAdmin) {
        this.user_isAdmin = user_isAdmin;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_about(String user_about) {
        this.user_about = user_about;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public void setUser_date_of_birth(Date user_date_of_birth) {
        this.user_date_of_birth = user_date_of_birth;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    //USER_USER TABLE
    public static final String TABLE_USER = "USER";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_LOGIN = "USER_LOGIN";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_MAIL = "USER_MAIL";
    public static final String COLUMN_USER_IS_ADMIN = "USER_ISADMIN";

    //USER_INFO TABLE
    public static final String TABLE_USER_INFO = "USER_INFO";
    public static final String COLUMN_USER_INFO_ID = "USER_INFO_ID";
    public static final String COLUMN_USER_INFO_NAME = "USER_INFO_NAME";
    public static final String COLUMN_USER_INFO_LASTNAME = "USER_INFO_LASTNAME";
    public static final String COLUMN_USER_INFO_SEX = "USER_INFO_SEX";
    public static final String COLUMN_USER_INFO_DATE_OF_BIRTH = "USER_INFO_DATE_OF_BIRTH";
    public static final String COLUMN_USER_INFO_COUNTRY = "USER_INFO_COUNTRY";
    public static final String COLUMN_USER_INFO_ABOUT = "USER_INFO_ABOUT";
    public static final String COLUMN_USER_INFO_PIC = "USER_INFO_PIC";


    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USER_LOGIN + " TEXT, " +
            COLUMN_USER_PASSWORD + " TEXT, " +
            COLUMN_USER_MAIL + " TEXT," +
            COLUMN_USER_IS_ADMIN + " INTEGER);";


    public static final String CREATE_TABLE_USER_INFO = "CREATE TABLE " + TABLE_USER_INFO + "(" +
            COLUMN_USER_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USER_INFO_NAME + " TEXT, " +
            COLUMN_USER_INFO_LASTNAME + " TEXT, " +
            COLUMN_USER_INFO_SEX + " TEXT," +
            COLUMN_USER_INFO_DATE_OF_BIRTH + " TEXT," +
            COLUMN_USER_INFO_COUNTRY + " TEXT," +
            COLUMN_USER_INFO_ABOUT + " TEXT," +
            COLUMN_USER_INFO_PIC + " TEXT," +
            COLUMN_USER_ID + " INTEGER," +
            "FOREIGN KEY ("+ COLUMN_USER_ID + ") REFERENCES "
            + TABLE_USER +"(" + COLUMN_USER_ID +")" +
            ");";
}
