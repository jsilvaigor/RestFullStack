package us.antenado.restnews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import us.antenado.restnews.rest.dto.SimpleNewsDTO;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private final List<SimpleNewsDTO> mValues;
    private final OnListItemInteractor mListener;

    public NewsRecyclerViewAdapter(List<SimpleNewsDTO> items, OnListItemInteractor listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem, InteractionType.VIEW);
                }
            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem, InteractionType.DELETE);
                }
            }
        });
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem, InteractionType.EDIT);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTitleView;
        public SimpleNewsDTO mItem;
        public ImageButton mEdit;
        public ImageButton mDelete;
        public ImageButton mView;

        public ViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.title);
            mDelete = view.findViewById(R.id.delete_news);
            mView = view.findViewById(R.id.view_news);
            mEdit = view.findViewById(R.id.edit_news);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
