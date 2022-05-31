package edu.weber.cs.cs3270a4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorEventListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaxFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnSeekChanged mCallback;

    interface OnSeekChanged{
        void onSeekUpdate(int value);
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;
    public TextView TaxRate;
    public TextView TaxAmount;

    private SeekBar seek;

    BigDecimal TotalAmountofLineAmounts;


    public TaxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaxFragment newInstance(String param1, String param2) {
        TaxFragment fragment = new TaxFragment();
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

        seek = root.findViewById(R.id.seekBar);

        TaxRate = root.findViewById(R.id.TaxRate);
        TaxAmount = root.findViewById(R.id.TaxAmount);



        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        int position = prefs.getInt("seek_progress", 10);
        int TR = prefs.getInt("tax_rate",0);
        int TA = prefs.getInt("tax_amount",0);
        seek.setProgress(position);

        TaxRate.setText(TR+"");
        TaxAmount.setText(TA+"");

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCallback.onSeekUpdate(progress);
                setAmount(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (OnSeekChanged) activity;
        } catch(ClassCastException e){
            throw new ClassCastException( activity.toString() + " must implement the OnSeekChanged Interface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_tax, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        TaxRate = root.findViewById(R.id.TaxRate);
        TaxAmount = root.findViewById(R.id.TaxAmount);

    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("seek_position", seek.getProgress() );



        prefsEditor.commit();

    }

    public void setAmount(int progress)
    {
        BigDecimal values = new BigDecimal(progress);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

        TaxRate.setText(values.doubleValue()+"%");
       // BigDecimal TaxDollarAmount = TotalAmountofLineAmounts.multiply(values);
      //  TaxAmount.setText(numberFormat.format(TaxDollarAmount.doubleValue()));
    }
}