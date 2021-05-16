package dev.abhisgr101.letsgoshopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRVAdapter extends ListAdapter<ItemModal, ItemRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    ItemRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<ItemModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<ItemModal>() {
        @Override
        public boolean areItemsTheSame(ItemModal oldItem, ItemModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(ItemModal oldItem, ItemModal newItem) {
            // below line is to check the item name, description and item quantity.
            return oldItem.getItemName().equals(newItem.getItemName()) &&
                    oldItem.getItemDescription().equals(newItem.getItemDescription()) &&
                    oldItem.getItemQuantity().equals(newItem.getItemQuantity());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rview, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        ItemModal model = getItemAt(position);
        holder.itemNameTV.setText(model.getItemName());
        holder.itemDescTV.setText(model.getItemDescription());
        holder.itemQuantityTV.setText(model.getItemQuantity());
    }

    // creating a method to get item modal for a specific position.
    public ItemModal getItemAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView itemNameTV, itemDescTV, itemQuantityTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            itemNameTV = itemView.findViewById(R.id.idTVItemName);
            itemDescTV = itemView.findViewById(R.id.idTVItemDescription);
            itemQuantityTV = itemView.findViewById(R.id.idTVItemQuantity);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(v -> {
                // inside on click listener we are passing
                // position to our item of recycler view.
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ItemModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

