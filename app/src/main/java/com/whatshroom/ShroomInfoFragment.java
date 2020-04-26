package com.whatshroom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ShroomInfoFragment extends Fragment {

    TextView nameOfShroomTextView, probabilityTextView;
    ImageView shroomImageView;
    Button againButton;
    View view;
    String shroomName;
    Float shroomProbability;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shroom_info_fragment, container, false);
        nameOfShroomTextView = view.findViewById(R.id.nameOfShroomextView);
        probabilityTextView = view.findViewById(R.id.probabilityTextView);
        shroomImageView = view.findViewById(R.id.shroomImageView);
        againButton = view.findViewById(R.id.againButton);
        shroomName = getArguments().getString("shroomName");
        shroomProbability = getArguments().getFloat("shroomProbability");

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ShroomFragment()).commit();
            }
        });

        switch (shroomName){
            case("borowik szlachetny"):
                shroomImageView.setImageResource(R.drawable.borowik_szlachetny);
                break;
            case("czubajka kania"):
                shroomImageView.setImageResource(R.drawable.czubajka_kania);
                break;
            case("goryczak żółciowy"):
                shroomImageView.setImageResource(R.drawable.goryczak_zolciowy);
                break;
            case("hubiak pospolity"):
                shroomImageView.setImageResource(R.drawable.hubiak_pospolity);
                break;
            case("koźlarz czerwony"):
                shroomImageView.setImageResource(R.drawable.kozlarz_czerowny);
                break;
            case("lisówka pomarańczowa"):
                shroomImageView.setImageResource(R.drawable.lisowka_pomaranczowa);
                break;
            case("maślak żółty"):
                shroomImageView.setImageResource(R.drawable.maslak_zolty);
                break;
            case("muchomor czerwony"):
                shroomImageView.setImageResource(R.drawable.muchomor_czerowony);
                break;
            case("muchomor sromotnikowy"):
                shroomImageView.setImageResource(R.drawable.muchomor_sromotnikowy);
                break;
            case("pieprznik jadalny"):
                shroomImageView.setImageResource(R.drawable.pieprznik_jadalny);
                break;
            case("podgrzybek brunatny"):
                shroomImageView.setImageResource(R.drawable.podgrzybek_brunatny);
                break;
            case("purchawka chropowata"):
                shroomImageView.setImageResource(R.drawable.purchawka_chropowata);
                break;
        }

        nameOfShroomTextView.setText("Znalazłeś " + shroomName +"*");
        probabilityTextView.setText("*Z prawdopodobieństwem " + shroomProbability.toString());

        return view;
    }
}
