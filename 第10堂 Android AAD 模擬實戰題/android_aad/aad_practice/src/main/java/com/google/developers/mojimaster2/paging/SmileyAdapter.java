package com.google.developers.mojimaster2.paging;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.text.emoji.widget.EmojiAppCompatTextView;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.developers.mojimaster2.R;
import com.google.developers.mojimaster2.data.Smiley;

public class SmileyAdapter extends PagedListAdapter<Smiley, SmileyAdapter.SmileyViewHolder> {

    public SmileyAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SmileyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 設定 R.layout.list_item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new SmileyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SmileyViewHolder holder, int position) {
        Smiley item = getItem(position);
        holder.bindTo(item);
    }

    public class SmileyViewHolder extends RecyclerView.ViewHolder {

        private EmojiAppCompatTextView mEmoji;
        private TextView mName;
        private TextView mUnicode;
        private Smiley mSmiley;

        SmileyViewHolder(View itemView) {
            super(itemView);
            // 建立 View 實體
            mEmoji = itemView.findViewById(R.id.item_char);
            mName = itemView.findViewById(R.id.item_name);
            mUnicode = itemView.findViewById(R.id.item_code);
        }

        public Smiley getSmiley() {
            return mSmiley;
        }

        void bindTo(Smiley smiley) {
            mSmiley = smiley;
            mEmoji.setText(smiley.getEmoji());
            mName.setText(smiley.getName());
            mUnicode.setText(smiley.getCode());
        }
    }

    private static final DiffUtil.ItemCallback<Smiley> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Smiley>() {
                @Override
                public boolean areItemsTheSame(@NonNull Smiley oldItem, @NonNull Smiley newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Smiley oldItem,
                                                  @NonNull Smiley newItem) {
                    return oldItem == newItem;
                }
            };
}
