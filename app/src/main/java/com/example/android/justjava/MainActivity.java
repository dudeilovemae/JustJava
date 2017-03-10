package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.order;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


//This app displays an order form to order coffee.
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //This method is called when the order button is clicked.
    public void submitOrder(View view) {

        CheckBox whippedCheck = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCheck.isChecked();

        CheckBox chocolateCheck = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheck.isChecked();

        EditText nameText = (EditText) findViewById(R.id.name_field);
        String name = nameText.getText().toString();

        int price = calculatePrice(hasChocolate, hasWhippedCream);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, hasWhippedCream, hasChocolate, name ));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));


    }

    /**
     * Calculates the price of the order.
     * @param addChocolate bool if they want chocolate
     * @param addWhippedCream bool if they want whipped cream
     * @return total price
     */
    private int calculatePrice(boolean addChocolate, boolean addWhippedCream) {
        int basePrice = 5;
        if (addChocolate)
        {
            basePrice = basePrice + 2;
        }

        if (addWhippedCream)
        {
            basePrice = basePrice + 1;
        }

        int price = quantity * basePrice;
        return price;
    }

    /**
     * Create summary of the order
     *
     * @param price price of the order
     * @param addWhippedCream do they want whipped cream
     * @param addChocolate do they want Chocolate
     * @return message
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String n) {




        String message = "Name: " + n;
        message = message + "\nAdd whipped cream? " + addWhippedCream;
        message = message + "\nAdd Chocolate? " + addChocolate;
        message = message + "\nQuantity: " + quantity;
        message = message + "\nTotal: $" + price;
        message = message + "\n" + getString(R.string.thank_you);

        return message;
    }

    public void increment(View view) {
        if (quantity == 100)
        {
            Toast.makeText(this, "Quantity cannot be more than 100", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0)
        {
            Toast.makeText(this, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
            return;
        }

            quantity = quantity - 1;
            displayQuantity(quantity);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int inputNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + inputNumber);
    }



}