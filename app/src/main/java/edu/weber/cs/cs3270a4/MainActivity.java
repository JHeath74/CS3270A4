package edu.weber.cs.cs3270a4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements TaxFragment.OnSeekChanged {

    private FragmentManager fragmentManager;
    private TotalsFragment totalsFragment;
    private TaxFragment taxFragment;
    private ItemsFragment itemsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            fragmentManager = getSupportFragmentManager() ;
            fragmentManager.beginTransaction()
                    .replace(R.id.totalsFragment,new TotalsFragment(),"Totals Fragment")
                    .replace(R.id.taxFragment,new TaxFragment(),"Tax Fragment")
                    .replace(R.id.itemsFragment, new ItemsFragment(),"Items Fragment")
                    .commit();

    }

    @Override
    public void onSeekUpdate(int value) {



        if(totalsFragment == null){totalsFragment = (TotalsFragment) fragmentManager.findFragmentByTag("Totals Fragment");}
        if(totalsFragment != null){totalsFragment.TotalAmount(value);}


        if(taxFragment == null){taxFragment = (TaxFragment) fragmentManager.findFragmentByTag("Tax Fragment");}
        if(taxFragment != null){taxFragment.setAmount(value);}

        if(itemsFragment == null){itemsFragment = (ItemsFragment) fragmentManager.findFragmentByTag("Items Fragment");}


    }
}