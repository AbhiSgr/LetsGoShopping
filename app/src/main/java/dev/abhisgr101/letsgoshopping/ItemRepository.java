package dev.abhisgr101.letsgoshopping;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    // below line is the create a variable
    // for dao and list for all items.
    private final DAO dao;
    private final LiveData<List<ItemModel>> allItems;

    // creating a constructor for our variables
    // and passing the variables to it.
    public ItemRepository(Application application) {
        ItemDatabase database = ItemDatabase.getInstance(application);
        dao = database.Dao();
        allItems = dao.getAllItems();
    }

    // creating a method to insert the data to our database.
    public void insert(ItemModel model) {
        new InsertItemAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(ItemModel model) {
        new UpdateItemAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(ItemModel model) {
        new DeleteItemAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the items.
    public void deleteAllItems() {
        new DeleteAllItemsAsyncTask(dao).execute();
    }

    // below method is to read all the items.
    public LiveData<List<ItemModel>> getAllItems() {
        return allItems;
    }

    // we are creating a async task method to insert new item.
    private static class InsertItemAsyncTask extends AsyncTask<ItemModel, Void, Void> {
        private final DAO dao;

        private InsertItemAsyncTask(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemModel... model) {
            // below line is use to insert our model in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our item.
    private static class UpdateItemAsyncTask extends AsyncTask<ItemModel, Void, Void> {
        private final DAO dao;

        private UpdateItemAsyncTask(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemModel... models) {
            // below line is use to update
            // our model in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete item.
    private static class DeleteItemAsyncTask extends AsyncTask<ItemModel, Void, Void> {
        private final DAO dao;

        private DeleteItemAsyncTask(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemModel... models) {
            // below line is use to delete
            // our item model in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all items.
    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DAO dao;
        private DeleteAllItemsAsyncTask(DAO dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all items.
            dao.deleteAllItems();
            return null;
        }
    }
}
