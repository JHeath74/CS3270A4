package edu.weber.cs.cs3270a4;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final TextWatcher ItemsListWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String value = s.toString();

            String ItemA = ItemAmount1.getText().toString();
            BigDecimal IAA = new BigDecimal(ItemA);

            String ItemB = ItemAmount2.getText().toString();
         //   BigDecimal IAB = new BigDecimal(ItemB); //Crashes the System

            String ItemC = ItemAmount3.getText().toString();
        //    BigDecimal IAC = new BigDecimal(ItemC); //Crashes the System

            String ItemD = ItemAmount4.getText().toString();
         //   BigDecimal IAD = new BigDecimal(ItemD); //Crashes the System

         //   BigDecimal TotalAmountofLineAmounts = IAA.add(IAB.add(IAC.add(IAD)));

        }
    };



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;
    private TextInputEditText ItemAmount1;
    private TextInputEditText ItemAmount2;
    private TextInputEditText ItemAmount3;
    private TextInputEditText ItemAmount4;

    private int ia1;
    private int ia2;
    private int ia3;
    private int ia4;

    public ItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        ItemAmount1 = root.findViewById(R.id.ItemAmount1);
        ItemAmount2 = root.findViewById(R.id.ItemAmount2);
        ItemAmount3 = root.findViewById(R.id.ItemAmount3);
        ItemAmount4 = root.findViewById(R.id.ItemAmount4);

        TextInputEditText ItemAmount1 = root.findViewById(R.id.ItemAmount1);
            ItemAmount1.addTextChangedListener(ItemsListWatcher);
        TextInputEditText ItemAmount2 = root.findViewById(R.id.ItemAmount2);
            ItemAmount2.addTextChangedListener(ItemsListWatcher);
        TextInputEditText ItemAmount3 = root.findViewById(R.id.ItemAmount3);
            ItemAmount3.addTextChangedListener(ItemsListWatcher);
        TextInputEditText ItemAmount4 = root.findViewById(R.id.ItemAmount4);
            ItemAmount4.addTextChangedListener(ItemsListWatcher);

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
         ia1 = prefs.getInt("ItemAmount1",0);
         ia2 = prefs.getInt("ItemAmount2",0);
         ia3 = prefs.getInt("ItemAmount3",0);
         ia4 = prefs.getInt("ItemAmount4",0);

        ItemAmount1.setText(ia1+"");
        ItemAmount2.setText(ia2+"");
        ItemAmount3.setText(ia3+"");
        ItemAmount4.setText(ia4+"");

    }

    @Override
    public void onPause() {
        super.onPause();

            SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putInt("ItemAmount1",ia1);
            prefsEditor.putInt("ItemAmount2",ia2);
            prefsEditor.putInt("ItemAmount3",ia3);
            prefsEditor.putInt("ItemAmount4",ia4);

        prefsEditor.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_items, container, false);
    }
}