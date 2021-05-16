package dev.abhisgr101.letsgoshopping;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface DAO {

    // below method is use to
    // add data to database.
    @Insert
    void insert(ItemModal model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(ItemModal model);

    // below line is use to delete a
    // specific item in our database.
    @Delete
    void delete(ItemModal model);

    // on below line we are making query to
    // delete all items from our database.
    @Query("DELETE FROM item_table")
    void deleteAllItems();

    // below line is to read all the items from our database.
    // in this we are ordering our items in ascending order
    // with our item name.
    @Query("SELECT * FROM item_table ORDER BY itemName ASC")
    LiveData<List<ItemModal>> getAllItems();
}
