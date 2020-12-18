package belstu.diploma.mobile.mangabook.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Author;
import belstu.diploma.mobile.mangabook.model.Book;
import belstu.diploma.mobile.mangabook.ui.AddAuthorFragment;
import belstu.diploma.mobile.mangabook.ui.AddTranslatorFragment;
import belstu.diploma.mobile.mangabook.ui.BookAdapter;
import belstu.diploma.mobile.mangabook.ui.tools.ToolsFragment;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

public class HomeFragment extends Fragment {


    DatabaseUtils db;
    private HomeViewModel homeViewModel;
    GridView simpleList;
    ArrayList<Book> books;
    BookAdapter bookAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        simpleList = (GridView) root.findViewById(R.id.simpleGridView);

        books = new ArrayList<Book>();


        db = MainActivity.getDb();


        books.addAll(db.getAllBooks());

        bookAdapter = new BookAdapter(getActivity(),R.layout.grid_view_items, books);
        simpleList.setAdapter(bookAdapter);

        return root;
    }
}