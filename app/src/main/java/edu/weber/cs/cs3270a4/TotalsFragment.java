package edu.weber.cs.cs3270a4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TotalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TotalsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;
    public TextView TotalAmount;


    //BigDecimal TotalAmountofLineAmounts;



    public TotalsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TotalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TotalsFragment newInstance(String param1, String param2) {
        TotalsFragment fragment = new TotalsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_totals, container, false);

    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onResume() {
        super.onResume();

        TotalAmount = root.findViewById(R.id.TotalAmount);


        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        int total = prefs.getInt("total_amount",0);
        TotalAmount.setText(total+"");

    }

    public void TotalAmount(int value)
    {

       /* BigDecimal Tax = new BigDecimal(value);
        BigDecimal TotalTax = TotalAmountofLineAmounts.multiply(Tax); // TotalAmountofLineAmounts is null
        BigDecimal TotalAmountWithTax = TotalAmountofLineAmounts.add(TotalTax);


        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        TotalAmount.setText((numberFormat.format(TotalAmountWithTax.doubleValue())));*/
    }
}