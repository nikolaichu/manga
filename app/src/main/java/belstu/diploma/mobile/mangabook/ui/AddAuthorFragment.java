package belstu.diploma.mobile.mangabook.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Author;
import belstu.diploma.mobile.mangabook.model.Translator;
import belstu.diploma.mobile.mangabook.ui.gallery.GalleryFragment;
import belstu.diploma.mobile.mangabook.ui.send.SendFragment;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

public class AddAuthorFragment extends Fragment {

    public AddAuthorFragment() {
        // Required empty public constructor
    }

    EditText name;
    EditText name_eng;
    EditText description;

    Button save;
    Author author;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_author, container, false);

        name = root.findViewById(R.id.addAuthorFragment_nameEditText);
        name_eng = root.findViewById(R.id.addAuthorFragment_name_eng_EditText);
        description = root.findViewById(R.id.addAuthorFragment_descriptionEditText);
        save = root.findViewById(R.id.addAuthorFragment_save_Button);

        author = new Author();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                author.setAuthorNameRus(name.getText().toString());
                author.setAuthorNameEng(name_eng.getText().toString());
                author.setAuthorDescription(description.getText().toString());
                createAuthor(author);
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new GalleryFragment())
                        .commit();
            }
        });

        return root;
    }

    public void onBackPressed() {
        super.getActivity().onBackPressed();
        getFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment, new SendFragment())
                .commit();
    }
    private void createAuthor(Author author) {
        DatabaseUtils db = MainActivity.getDb();
        db.insertAuthor(author);
    }
}
