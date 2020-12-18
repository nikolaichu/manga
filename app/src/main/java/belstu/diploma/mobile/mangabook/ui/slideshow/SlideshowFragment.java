package belstu.diploma.mobile.mangabook.ui.slideshow;

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

import java.util.ArrayList;

import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Translator;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    ArrayList<Translator> translators;
    // набор данных, которые свяжем со списком
    String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        // получаем элемент ListView
        ListView countriesList = (ListView) root.findViewById(R.id.authorsList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, countries);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
        return root;
    }
}