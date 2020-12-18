package belstu.diploma.mobile.mangabook.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Translator;
import belstu.diploma.mobile.mangabook.ui.send.SendFragment;
import belstu.diploma.mobile.mangabook.utils.DatabaseUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTranslatorFragment extends Fragment {


    EditText name;
    EditText site;
    EditText description;

    Button save;
    Translator translator;

    public AddTranslatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_translator, container, false);

        name = root.findViewById(R.id.addTranslatorFragment_nameEditText);
        site = root.findViewById(R.id.addTranslatorFragment_siteEditText);
        description = root.findViewById(R.id.addTranslatorFragment_descriptionEditText);
        save = root.findViewById(R.id.addTranslatorFragment_save_Button);

        translator = new Translator();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translator.setTranslatorName(name.getText().toString());
                translator.setTranslatorSite(site.getText().toString());
                translator.setTranslatorDescription(description.getText().toString());
                createTranslator(translator);
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new SendFragment())
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
    private void createTranslator(Translator translator) {
        DatabaseUtils db = MainActivity.getDb();
        db.insertTranslator(translator);
    }

}
