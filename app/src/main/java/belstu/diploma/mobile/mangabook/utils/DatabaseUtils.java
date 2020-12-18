package belstu.diploma.mobile.mangabook.utils;

import android.content.ContentValues;
import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import belstu.diploma.mobile.mangabook.model.Author;
import belstu.diploma.mobile.mangabook.model.Book;
import belstu.diploma.mobile.mangabook.model.Bookmark;
import belstu.diploma.mobile.mangabook.model.Chapter;
import belstu.diploma.mobile.mangabook.model.Translator;
import belstu.diploma.mobile.mangabook.model.User;

public class DatabaseUtils extends SQLiteOpenHelper {

    private static DatabaseUtils instance;

    private static final String password = "somepassword";

    private static final int DATABASE_VER = 1;
    public static final String DATABASE_NAME = "manga.db";


    public DatabaseUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
        SQLiteDatabase.loadLibs(context);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        //db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(User.CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(Author.CREATE_TABLE_AUTHOR);
        sqLiteDatabase.execSQL(Translator.CREATE_TABLE_TRANSLATOR);
        sqLiteDatabase.execSQL(User.CREATE_TABLE_USER_INFO);
        sqLiteDatabase.execSQL(Book.CREATE_TABLE_BOOK);
        sqLiteDatabase.execSQL(Chapter.CREATE_TABLE_CHAPTER);
        sqLiteDatabase.execSQL(Bookmark.CREATE_TABLE_BOOKMARK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Bookmark.TABLE_BOOKMARK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Chapter.TABLE_CHAPTER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Book.TABLE_BOOK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.TABLE_USER_INFO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Translator.TABLE_TRANSLATOR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Author.TABLE_AUTHOR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public long insertBook(Book book) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `book status` will be inserted automatically.
        // no need to add them
        values.put(Book.COLUMN_BOOK_NAME_RUS, book.getBookNameRus());
        values.put(Book.COLUMN_BOOK_NAME_ENG, book.getBookNameEng());
        values.put(Book.COLUMN_BOOK_NAME_ORIG, book.getBookNameOrig());
        values.put(Book.COLUMN_BOOK_NAME_ALT, book.getBookNameAlt());
        values.put(Book.COLUMN_BOOK_TYPE, book.getBookType());
        values.put(Book.COLUMN_BOOK_YEAR, book.getBookYear());
        values.put(Book.COLUMN_BOOK_COVER, book.getCover());
        values.put(Book.COLUMN_BOOK_DESCRIPTION, book.getBookDescription());
        values.put(Author.COLUMN_AUTHOR_ID, book.getAuthorId());
        values.put(Translator.COLUMN_TRANSLATOR_ID, book.getTranslatorId());
        values.put(User.COLUMN_USER_ID, book.getUserId());

        // insert row
        long id = db.insert(Book.TABLE_BOOK, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertAuthor(Author author) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `book status` will be inserted automatically.
        // no need to add them
        values.put(Author.COLUMN_AUTHOR_NAME_RUS, author.getAuthorNameRus());
        values.put(Author.COLUMN_AUTHOR_NAME_ENG, author.getAuthorNameEng());
        values.put(Author.COLUMN_AUTHOR_DESCRIPTION, author.getAuthorDescription());

        // insert row
        long id = db.insert(Author.TABLE_AUTHOR, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertTranslator(Translator translator) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `book status` will be inserted automatically.
        // no need to add them
        values.put(Translator.COLUMN_TRANSLATOR_NAME, translator.getTranslatorName());
        values.put(Translator.COLUMN_TRANSLATOR_SITE, translator.getTranslatorSite());
        values.put(Translator.COLUMN_TRANSLATOR_DESCRIPTION, translator.getTranslatorDescription());

        // insert row
        long id = db.insert(Translator.TABLE_TRANSLATOR, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Book getBook(long id) {
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Book.TABLE_BOOK,
                new String[]{
                        Book.COLUMN_BOOK_ID,
                        Book.COLUMN_BOOK_NAME_RUS,
                        Book.COLUMN_BOOK_NAME_ENG,
                        Book.COLUMN_BOOK_NAME_ORIG,
                        Book.COLUMN_BOOK_NAME_ALT,
                        Book.COLUMN_BOOK_TYPE,
                        Book.COLUMN_BOOK_YEAR,
                        Book.COLUMN_BOOK_COVER,
                        Book.COLUMN_BOOK_DESCRIPTION,
                        Author.COLUMN_AUTHOR_ID,
                        Translator.COLUMN_TRANSLATOR_ID,
                        User.COLUMN_USER_ID
                },
                Book.COLUMN_BOOK_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Book book = new Book();
        book.setBookId(cursor.getInt(cursor.getColumnIndex(Book.COLUMN_BOOK_ID)));
        book.setBookNameRus(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_RUS)));
        book.setBookNameEng(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ENG)));
        book.setBookNameOrig(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ORIG)));
        book.setBookNameAlt(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ALT)));
        book.setBookType(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_TYPE)));
        book.setBookYear(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_YEAR)));
        book.setCover(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_COVER)));
        book.setBookDescription(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_DESCRIPTION)));
        book.setAuthorId(cursor.getInt(cursor.getColumnIndex(Author.COLUMN_AUTHOR_ID)));
        book.setTranslatorId(cursor.getInt(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_ID)));
        book.setUserId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_USER_ID)));

        // close the db connection
        cursor.close();

        return book;
    }
    public Translator getTranslator(long id) {
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Translator.TABLE_TRANSLATOR,
                new String[]{
                        Translator.COLUMN_TRANSLATOR_ID,
                        Translator.COLUMN_TRANSLATOR_NAME,
                        Translator.COLUMN_TRANSLATOR_SITE,
                        Translator.COLUMN_TRANSLATOR_DESCRIPTION
                },
                Translator.COLUMN_TRANSLATOR_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Translator translator = new Translator();
        translator.setTranslatorId(cursor.getInt(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_ID)));
        translator.setTranslatorName(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_NAME)));
        translator.setTranslatorSite(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_SITE)));
        translator.setTranslatorDescription(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_DESCRIPTION)));

        // close the db connection
        cursor.close();

        return translator;
    }
    public Translator getTranslatorByName(String name) {
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Translator.TABLE_TRANSLATOR,
                new String[]{
                        Translator.COLUMN_TRANSLATOR_ID,
                        Translator.COLUMN_TRANSLATOR_NAME,
                        Translator.COLUMN_TRANSLATOR_SITE,
                        Translator.COLUMN_TRANSLATOR_DESCRIPTION
                },
                Translator.COLUMN_TRANSLATOR_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Translator translator = new Translator();
        translator.setTranslatorId(cursor.getInt(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_ID)));
        translator.setTranslatorName(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_NAME)));
        translator.setTranslatorSite(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_SITE)));
        translator.setTranslatorDescription(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_DESCRIPTION)));

        // close the db connection
        cursor.close();

        return translator;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Book.TABLE_BOOK;
//                + " WHERE " + User.COLUMN_USER_ID + "= " + user_id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Book.TABLE_BOOK,
                new String[]{
                        Book.COLUMN_BOOK_ID,
                        Book.COLUMN_BOOK_NAME_RUS,
                        Book.COLUMN_BOOK_NAME_ENG,
                        Book.COLUMN_BOOK_NAME_ORIG,
                        Book.COLUMN_BOOK_NAME_ALT,
                        Book.COLUMN_BOOK_TYPE,
                        Book.COLUMN_BOOK_YEAR,
                        Book.COLUMN_BOOK_COVER,
                        Book.COLUMN_BOOK_DESCRIPTION,
                        Author.COLUMN_AUTHOR_ID,
                        Translator.COLUMN_TRANSLATOR_ID,
                        User.COLUMN_USER_ID
                },
                null, null, null, null,
                null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setBookId(cursor.getInt(cursor.getColumnIndex(Book.COLUMN_BOOK_ID)));
                book.setBookNameRus(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_RUS)));
                book.setBookNameEng(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ENG)));
                book.setBookNameOrig(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ORIG)));
                book.setBookNameAlt(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ALT)));
                book.setBookType(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_TYPE)));
                book.setBookYear(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_YEAR)));
                book.setCover(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_COVER)));
                book.setBookDescription(cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_DESCRIPTION)));
                book.setAuthorId(cursor.getInt(cursor.getColumnIndex(Author.COLUMN_AUTHOR_ID)));
                book.setTranslatorId(cursor.getInt(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_ID)));
                book.setUserId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_USER_ID)));

                books.add(book);
            } while (cursor.moveToNext());
        }

        //
        cursor.close();
        // close db connection
        db.close();

        // return notes list
        return books;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Author.TABLE_AUTHOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Author.TABLE_AUTHOR,
                new String[]{
                        Author.COLUMN_AUTHOR_ID,
                        Author.COLUMN_AUTHOR_NAME_RUS,
                        Author.COLUMN_AUTHOR_NAME_ENG,
                        Author.COLUMN_AUTHOR_DESCRIPTION
                },
                null, null, null, null,
                null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Author author = new Author();
                author.setAuthorId(cursor.getInt(cursor.getColumnIndex(Author.COLUMN_AUTHOR_ID)));
                author.setAuthorNameRus(cursor.getString(cursor.getColumnIndex(Author.COLUMN_AUTHOR_NAME_RUS)));
                author.setAuthorNameEng(cursor.getString(cursor.getColumnIndex(Author.COLUMN_AUTHOR_NAME_ENG)));
                author.setAuthorDescription(cursor.getString(cursor.getColumnIndex(Author.COLUMN_AUTHOR_DESCRIPTION)));
                authors.add(author);
            } while (cursor.moveToNext());
        }

        //
        cursor.close();
        // close db connection
        db.close();

        // return notes list
        return authors;
    }

    public List<Translator> getAllTranslators() {
        List<Translator> translators = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Translator.TABLE_TRANSLATOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Translator.TABLE_TRANSLATOR,
                new String[]{
                        Translator.COLUMN_TRANSLATOR_ID,
                        Translator.COLUMN_TRANSLATOR_NAME,
                        Translator.COLUMN_TRANSLATOR_SITE,
                        Translator.COLUMN_TRANSLATOR_DESCRIPTION
                },
                null, null, null, null,
                null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Translator translator = new Translator();
                translator.setTranslatorId(cursor.getInt(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_ID)));
                translator.setTranslatorName(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_NAME)));
                translator.setTranslatorSite(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_SITE)));
                translator.setTranslatorDescription(cursor.getString(cursor.getColumnIndex(Translator.COLUMN_TRANSLATOR_DESCRIPTION)));
                translators.add(translator);
            } while (cursor.moveToNext());
        }

        //
        cursor.close();
        // close db connection
        db.close();

        // return notes list
        return translators;
    }

    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_BOOK_NAME_RUS, book.getBookNameRus());
        values.put(Book.COLUMN_BOOK_NAME_ENG, book.getBookNameEng());
        values.put(Book.COLUMN_BOOK_NAME_ORIG, book.getBookNameOrig());
        values.put(Book.COLUMN_BOOK_NAME_ALT, book.getBookNameAlt());
        values.put(Book.COLUMN_BOOK_TYPE, book.getBookType());
        values.put(Book.COLUMN_BOOK_YEAR, book.getBookYear());
        values.put(Book.COLUMN_BOOK_COVER, book.getCover());
        values.put(Book.COLUMN_BOOK_DESCRIPTION, book.getBookDescription());
        values.put(Author.COLUMN_AUTHOR_ID, book.getAuthorId());
        values.put(Translator.COLUMN_TRANSLATOR_ID, book.getTranslatorId());
        values.put(User.COLUMN_USER_ID, book.getUserId());

        // updating row
        db.update(Book.TABLE_BOOK, values, Book.COLUMN_BOOK_ID + " = ?",
                new String[]{String.valueOf(book.getBookId())});
    }

    public void updateTranslator(Translator translator) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Translator.COLUMN_TRANSLATOR_NAME, translator.getTranslatorName());
        values.put(Translator.COLUMN_TRANSLATOR_SITE, translator.getTranslatorSite());
        values.put(Translator.COLUMN_TRANSLATOR_DESCRIPTION, translator.getTranslatorDescription());

        // updating row
        db.update(Translator.TABLE_TRANSLATOR, values, Translator.COLUMN_TRANSLATOR_ID + " = ?",
                new String[]{String.valueOf(translator.getTranslatorId())});
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Book.TABLE_BOOK, Book.COLUMN_BOOK_ID + " = ?",
                new String[]{String.valueOf(book.getBookId())});
        db.close();
    }
    public void deleteTranslator(Translator translator) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Translator.TABLE_TRANSLATOR, Translator.COLUMN_TRANSLATOR_ID + " = ?",
                new String[]{String.valueOf(translator.getTranslatorId())});
        db.close();
    }
    public SQLiteDatabase getReadableDatabase() {
        return (super.getReadableDatabase(password));
    }

    public SQLiteDatabase getWritableDatabase() {
        return (super.getWritableDatabase(password));
    }

}
