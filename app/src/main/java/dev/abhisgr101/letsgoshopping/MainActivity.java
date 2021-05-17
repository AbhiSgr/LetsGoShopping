package dev.abhisgr101.letsgoshopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ITEM_REQUEST = 1;
    private static final int EDIT_ITEM_REQUEST = 2;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variable for our recycler view and fab.
        // creating a variables for our recycler view.
        RecyclerView itemsRV = findViewById(R.id.idRVItems);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(v -> {
            // starting a new activity for adding a new item
            // and passing a constant value in it.
            Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
            startActivityForResult(intent, ADD_ITEM_REQUEST);
        });

        // setting layout manager to our adapter class.
        itemsRV.setLayoutManager(new LinearLayoutManager(this));
        itemsRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final ItemRVAdapter adapter = new ItemRVAdapter();

        // setting adapter class for recycler view.
        itemsRV.setAdapter(adapter);

        // passing a data from view model.
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        // below line is use to get all the items from view model.
        // when the data is changed in our models we are
        // adding that list to our adapter class.
        viewModel.getAllItems().observe(this, adapter::submitList);
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(itemsRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(model -> {
            // after clicking on item of recycler view
            // we are opening a new activity and passing
            // a data to our activity.
            Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
            intent.putExtra(NewItemActivity.EXTRA_ID, model.getId());
            intent.putExtra(NewItemActivity.EXTRA_ITEM_NAME, model.getItemName());
            intent.putExtra(NewItemActivity.EXTRA_DESCRIPTION, model.getItemDescription());
            intent.putExtra(NewItemActivity.EXTRA_QUANTITY, model.getItemQuantity());

            // below line is to start a new activity and
            // adding a edit item constant.
            startActivityForResult(intent, EDIT_ITEM_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String itemName = data.getStringExtra(NewItemActivity.EXTRA_ITEM_NAME);
            String itemDescription = data.getStringExtra(NewItemActivity.EXTRA_DESCRIPTION);
            String itemQuantity = data.getStringExtra(NewItemActivity.EXTRA_QUANTITY);
            ItemModel model = new ItemModel(itemName, itemDescription, itemQuantity);
            viewModel.insert(model);
            Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_ITEM_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            int id = data.getIntExtra(NewItemActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Item can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String itemName = data.getStringExtra(NewItemActivity.EXTRA_ITEM_NAME);
            String itemDesc = data.getStringExtra(NewItemActivity.EXTRA_DESCRIPTION);
            String itemQuantity = data.getStringExtra(NewItemActivity.EXTRA_QUANTITY);
            ItemModel model = new ItemModel(itemName, itemDesc, itemQuantity);
            model.setId(id);
            viewModel.update(model);
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Item not saved", Toast.LENGTH_SHORT).show();
        }
    }
}

