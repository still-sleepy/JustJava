/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.content.Context;
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

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        if (quantity >= 100){
            quantity = 100;
            Context context = this; // could have used "getApplicationContext()"
            CharSequence text = "Can't have more than 100 coffees!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 0){
            quantity = 0;
            Context context = getApplicationContext(); // could have used "this"
            CharSequence text = "Can't have less than 0 coffees!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the check box is clicked.

    public void isWhipChecked(View view) {
        Boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whip_checkbox)).isChecked();
        /* Log.v("MainActivity", "CheckBox is: " + chkbx);
    }*/

    /**
     * Calculates the price of the order, including whether whipped cream / chocolateis added
     *
     * @return total price
     */
    private int calculatePrice(boolean hasWC, boolean hasCH) {
        int unitPrice = 5;

        if (hasWC){
            unitPrice = unitPrice + 1;
        }

        if (hasCH){
            unitPrice = unitPrice + 2;
        }

        return quantity * unitPrice;

    }

    /**
     * This method creates the order name.
     * @return order name
     */
    public String createOrderName() {

        EditText nameInput = (EditText)findViewById(R.id.order_name);
        String orderName = nameInput.getText().toString();

        return orderName;
    }

    /**
     * This method creates the order subject.
     * @return order subject
     */
    public String createOrderSubject() {

        String orderSubject = "JustJava order for " + createOrderName();

        return orderSubject;
    }

    /**
     * This method creates the order summary.
     * @return order summary
     */
    public String createOrderSummary() {

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whip_checkbox);
        Boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocoCheckbox = (CheckBox) findViewById(R.id.choco_checkbox);
        Boolean hasChocolate = chocoCheckbox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String priceMessage = "Name: " + createOrderName();
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }


    /**
     * This method creates the e-mail when the order button is clicked.
     */
    // String[] addresses, String subject, String orderSummary
    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"janosmann@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, createOrderSubject());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//    public void submitOrder(View view) {
//        displayMessage(createOrderSummary());
//    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}