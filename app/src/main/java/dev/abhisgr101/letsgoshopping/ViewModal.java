package dev.abhisgr101.letsgoshopping;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for item repository.
    private final ItemRepository repository;

    // below line is to create a variable for live
    // data where all the items are present.
    private final LiveData<List<ItemModal>> allItems;

    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
    }

    // below method is use to insert the data to our repository.
    public void insert(ItemModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(ItemModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(ItemModal model) {
        repository.delete(model);
    }

    // below method is to delete all the items in our list.
    public void deleteAllItems() {
        repository.deleteAllItems();
    }

    // below method is to get all the items in our list.
    public LiveData<List<ItemModal>> getAllItems() {
        return allItems;
    }
}
