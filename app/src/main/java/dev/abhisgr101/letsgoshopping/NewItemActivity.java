package dev.abhisgr101.letsgoshopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewItemActivity extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText itemNameEdt, itemDescEdt, itemQuantityEdt;

    // creating a constant string variable for our
    // item name, description and quantity.
    public static final String EXTRA_ID = "dev.abhisgr101.letsgoshoppingDB.EXTRA_ID";
    public static final String EXTRA_ITEM_NAME = "dev.abhisgr101.letsgoshoppingDB.EXTRA_ITEM_NAME";
    public static final String EXTRA_DESCRIPTION = "dev.abhisgr101.letsgoshoppingDB.EXTRA_ITEM_DESCRIPTION";
    public static final String EXTRA_QUANTITY = "dev.abhisgr101.letsgoshoppingDB.EXTRA_ITEM_QUANTITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // initializing our variables for each view.
        itemNameEdt = findViewById(R.id.idEdtItemName);
        itemDescEdt = findViewById(R.id.idEdtItemDescription);
        itemQuantityEdt = findViewById(R.id.idEdtItemQuantity);
        Button itemBtn = findViewById(R.id.idBtnSaveItem);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            itemNameEdt.setText(intent.getStringExtra(EXTRA_ITEM_NAME));
            itemDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            itemQuantityEdt.setText(intent.getStringExtra(EXTRA_QUANTITY));
        }
        // adding on click listener for our save button.
        itemBtn.setOnClickListener(v -> {
            // getting text value from edittext and validating if
            // the text fields are empty or not.
            String itemName = itemNameEdt.getText().toString();
            String itemDesc = itemDescEdt.getText().toString();
            String itemQuantity = itemQuantityEdt.getText().toString();
            if (itemName.isEmpty() || itemDesc.isEmpty() || itemQuantity.isEmpty()) {
                Toast.makeText(NewItemActivity.this, "Please enter the valid item details.", Toast.LENGTH_SHORT).show();
                return;
            }
            // calling a method to save our item.
            saveItem(itemName, itemDesc, itemQuantity);
            // dirty hack for clearing fields
            itemNameEdt.setText("");
            itemDescEdt.setText("");
            itemQuantityEdt.setText("");
        });
    }

    private void saveItem(String itemName, String itemDescription, String itemQuantity) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our item detail.
        data.putExtra(EXTRA_ITEM_NAME, itemName);
        data.putExtra(EXTRA_DESCRIPTION, itemDescription);
        data.putExtra(EXTRA_QUANTITY, itemQuantity);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Item has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}
