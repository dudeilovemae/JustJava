package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.NumberFormat;

import static android.R.attr.order;
import static android.R.id.message;


//This app displays an order form to order coffee.
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

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

        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate ));

    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
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
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {




        String message = "Name: Ray Franco";
        message = message + "\nAdd whipped cream? " + addWhippedCream;
        message = message + "\nAdd Chocolate? " + addChocolate;
        message = message + "\nQuantity: " + quantity;
        message = message + "\nTotal: $" + price;
        message = message + "\nThank You!";

        return message;
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
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

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}