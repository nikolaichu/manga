package belstu.diploma.mobile.mangabook.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Book;
import belstu.diploma.mobile.mangabook.model.Translator;
import belstu.diploma.mobile.mangabook.ui.AddTranslatorFragment;
import belstu.diploma.mobile.mangabook.ui.TranslatorFragment;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

public class SendFragment extends Fragment {


    DatabaseUtils db;
    private SendViewModel sendViewModel;
    ArrayList<Translator> translators;
    ArrayList<String> adapterStrings;

    Fragment fragment;
    FragmentManager fragmentManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fragment = this;
        translators = new ArrayList<Translator>();
        db = MainActivity.getDb();
        translators.addAll(db.getAllTranslators());
        final ListView translatorsList = (ListView) root.findViewById(R.id.translatorList);

        adapterStrings = new ArrayList<String>();
        for (Translator t : translators) {
            adapterStrings.add(t.getTranslatorName());
        }

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, adapterStrings);

        // устанавливаем для списка адаптер
        translatorsList.setAdapter(adapter);
        translatorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // по позиции получаем выбранный элемент

                Fragment newFragment = new TranslatorFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                Bundle args = new Bundle();
                args.putInt("position", translators.get(position).getTranslatorId());
                newFragment.setArguments(args);


                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed

                transaction.replace(R.id.nav_host_fragment, newFragment);
                transaction.addToBackStack(null);

//                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                transaction.commit();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new AddTranslatorFragment())
                        .commit();
            }
        });
        return root;

    }

}