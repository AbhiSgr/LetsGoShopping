package dev.abhisgr101.letsgoshopping;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    // creating a new variable for item repository.
    private final ItemRepository repository;

    // below line is to create a variable for live
    // data where all the items are present.
    private final LiveData<List<ItemModel>> allItems;

    // constructor for our view model.
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
    }

    // below method is use to insert the data to our repository.
    public void insert(ItemModel model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(ItemModel model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(ItemModel model) {
        repository.delete(model);
    }

    // below method is to delete all the items in our list.
    public void deleteAllItems() {
        repository.deleteAllItems();
    }

    // below method is to get all the items in our list.
    public LiveData<List<ItemModel>> getAllItems() {
        return allItems;
    }
}
