package belstu.diploma.mobile.mangabook.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Translator;
import belstu.diploma.mobile.mangabook.ui.send.SendFragment;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslatorFragment extends Fragment {

    DatabaseUtils db;
    EditText name;
    EditText site;
    EditText description;

    Button translator_save;
    Button translator_delete;

    FragmentManager fragmentManager;


    public TranslatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_translator, container, false);

//        FloatingActionButton fab = root.findViewById(R.id.fab_edit);
        db = MainActivity.getDb();

        fragmentManager = getActivity().getSupportFragmentManager();
        name = root.findViewById(R.id.translatorFragment_Name_EditText);
        site = root.findViewById(R.id.translatorFragment_Site_EditText);
        description = root.findViewById(R.id.translatorFragment_description);

        translator_save = root.findViewById(R.id.translator_save);
        translator_delete = root.findViewById(R.id.translator_delete);

        //Retrieve the value
        final int position = getArguments().getInt("position");

        final Translator translator = db.getTranslator(position);
        name.setText(translator.getTranslatorName());
        site.setText(translator.getTranslatorSite());
        description.setText(translator.getTranslatorDescription());

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        translator_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translator.setTranslatorName(name.getText().toString());
                translator.setTranslatorSite(site.getText().toString());
                translator.setTranslatorDescription(description.getText().toString());

                // updating note in db
                db.updateTranslator(translator);
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new SendFragment())
                        .commit();
            }
        });

        translator_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteTranslator(translator);
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new SendFragment())
                        .commit();
            }
        });


        return root;
    }

}
