package es.iessaladillo.pedrojoya.greet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    String treatment = "", name, surname;
    int count = 0;
    boolean polite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViews();
    }

    private void setupViews() {

        binding.lblCountBar.setText(getString(R.string.countBarText, count));
        binding.rdgTreatment.setOnCheckedChangeListener((radioGroup, i) -> checkTreatment());
        binding.greetBtn.setOnClickListener(l -> printResult());
        binding.checkGreetStyle.setOnClickListener(l -> checkStyle());
        binding.lblpremiumSwitcher.setOnClickListener(l -> showBar());

    }

    private void checkStyle() {

        if (binding.checkGreetStyle.isChecked()) {
            polite = true;
        } else {
            polite = false;
        }
    }

    private void printResult() {
        name = binding.inputName.getText().toString();
        surname = binding.inputSurname.getText().toString();

        if (count > 10) {
            binding.lblOutputGreet.setText(R.string.buyPremium);
        } else {
            checkPremium();
            checkTreatment();
            checkStyle();

            if (polite) {
                binding.lblOutputGreet.setText(getString(R.string.greetFormal, treatment, name, surname));


            } else {
                binding.lblOutputGreet.setText(getString(R.string.greetInformal, name, surname));

            }

        }

    }



    private void checkTreatment() {
        if (binding.rdMr.isChecked()) {
            binding.imgTreatment.setImageResource(R.drawable.ic_mr);
            treatment = binding.rdMr.getText().toString();

        } else if(binding.rdMrs.isChecked()) {
            binding.imgTreatment.setImageResource(R.drawable.ic_ms);
            treatment = binding.rdMrs.getText().toString();

        } else {
            binding.imgTreatment.setImageResource(R.drawable.ic_mrs);
            treatment = binding.rdMs.getText().toString();

        }

    }

    private void showBar() {
        count = 0;
        binding.progresBar.setProgress(count);
        if (binding.lblpremiumSwitcher.isChecked()) {
            binding.progresBar.setVisibility(View.GONE);
            binding.lblCountBar.setVisibility(View.GONE);
        } else {
            checkPremium();
            binding.progresBar.setVisibility(View.VISIBLE);
            binding.lblCountBar.setVisibility(View.VISIBLE);

        }
    }

    private void checkPremium() {
        if(binding.lblpremiumSwitcher.isChecked()) {
        binding.progresBar.setProgress(count);
        binding.lblCountBar.setText(getString(R.string.countBarText, count, count));

    } else {

            binding.progresBar.setProgress(count);
            binding.lblCountBar.setText(getString(R.string.countBarText, count, count));
            
            count++;
        }

        }

}