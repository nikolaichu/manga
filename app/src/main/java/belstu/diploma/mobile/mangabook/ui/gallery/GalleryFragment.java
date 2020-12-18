package belstu.diploma.mobile.mangabook.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Author;
import belstu.diploma.mobile.mangabook.model.Translator;
import belstu.diploma.mobile.mangabook.ui.AddAuthorFragment;
import belstu.diploma.mobile.mangabook.ui.AddTranslatorFragment;
import belstu.diploma.mobile.mangabook.ui.send.SendViewModel;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

public class GalleryFragment extends Fragment {

    DatabaseUtils db;


    ArrayList<Author> authors;
    ArrayList<String> adapterStrings;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        authors = new ArrayList<Author>();
        FloatingActionButton fab = root.findViewById(R.id.fab_gallery);

        db = MainActivity.getDb();

        authors.addAll(db.getAllAuthors());
        final ListView authorsList = (ListView) root.findViewById(R.id.authorList);

        adapterStrings = new ArrayList<String>();
        for (Author t: authors) {
            adapterStrings.add(t.getAuthorNameRus());
        }

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, adapterStrings);

        // устанавливаем для списка адаптер
        authorsList.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new AddAuthorFragment())
                        .commit();
            }
        });
        return root;
    }
}