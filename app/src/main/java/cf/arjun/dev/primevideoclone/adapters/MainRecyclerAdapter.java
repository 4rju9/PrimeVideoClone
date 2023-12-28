package cf.arjun.dev.primevideoclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cf.arjun.dev.primevideoclone.R;
import cf.arjun.dev.primevideoclone.models.AllCategory;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    Context context;
    List<AllCategory> allCategoryList;

    public MainRecyclerAdapter(Context context, List<AllCategory> allCategoryList) {
        this.context = context;
        this.allCategoryList = allCategoryList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_reycler_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.categoryName.setText(allCategoryList.get(position).getCategoryTitle());

    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.item_category);
        }
    }

}