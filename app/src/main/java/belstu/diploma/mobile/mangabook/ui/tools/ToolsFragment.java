package belstu.diploma.mobile.mangabook.ui.tools;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Book;
import belstu.diploma.mobile.mangabook.ui.home.HomeFragment;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

import static android.app.Activity.RESULT_OK;

public class ToolsFragment extends Fragment {

    ImageView coverImageView;
    EditText name;
    EditText nameEng;
    EditText nameOrig;
    EditText type;
    EditText year;
    EditText author;
    EditText translator;

    Book book;

    Button save;

    private static final int REQUEST_PERMISSION_WRITE = 1001;
    final int REQUEST_CODE_GALLERY = 999;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        checkPermissions();
        book = new Book();
        save = root.findViewById(R.id.save_changes_Button);
        name = root.findViewById(R.id.tools_name_EditText);
        nameEng = root.findViewById(R.id.tools_name_eng_EditText);
        nameOrig = root.findViewById(R.id.tools_name_orig_EditText);
        type = root.findViewById(R.id.tools_type_EditText);
        year = root.findViewById(R.id.tools_year_EditText);
        author = root.findViewById(R.id.tools_author_EditText);
        translator = root.findViewById(R.id.tools_translator_EditText);
        coverImageView = root.findViewById(R.id.tools_cover_ImageView);
        coverImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setBookNameRus(name.getText().toString());
                book.setBookNameEng(nameEng.getText().toString());
                book.setBookNameOrig(nameOrig.getText().toString());
                book.setBookType(type.getText().toString());
                book.setBookYear(year.getText().toString());
                createBook(book);

                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new HomeFragment())
                        .commit();
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                String imageFileName = "JPEG_" + nameEng.getText().toString() + "_.png";
                String path = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath();
                File dir = new File(path, "MangaBook");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = new File(dir, imageFileName);
                //checking if we can write to external memory
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {

                    try (FileOutputStream out = new FileOutputStream(file)) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    } catch (IOException e) {
                        Log.d("CreateNew", e.getMessage());
                    }

                    book.setCover(file.getAbsolutePath());
                    coverImageView.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    //permissions
    private boolean checkPermissions() {
        if (!isExternalStorageReadable() || !isExternalStorageWritable()) {
            Toast.makeText(this.getContext(), "Внешнее хранилище не доступно", Toast.LENGTH_LONG).show();
            return false;
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        }
        return true;
    }
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    private void createBook(Book book) {
        DatabaseUtils db = MainActivity.getDb();
        db.insertBook(book);
    }
}