package com.edu.vincenttuan.app_paging_viewmodel;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.vincenttuan.app_paging_viewmodel.model.Lotto;

import java.util.Objects;

public class LottoAdapter extends PagedListAdapter<Lotto, LottoAdapter.LottoViewHolder>
                          implements View.OnClickListener, View.OnLongClickListener {
    private static final DiffUtil.ItemCallback<Lotto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Lotto>() {
                @Override
                public boolean areItemsTheSame(@NonNull Lotto oldItem, @NonNull Lotto newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Lotto oldItem, @NonNull Lotto newItem) {
                    return Objects.equals(oldItem.getNums(), newItem.getNums());
                }
            };

    public LottoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public LottoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        // 註冊事件
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new LottoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LottoViewHolder lottoViewHolder, int position) {
        Lotto lotto = getItem(position);
        lottoViewHolder.bindTo(lotto);
        lottoViewHolder.itemView.setTag(lotto);
    }

    public static class LottoViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textView2;
        public LottoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 =itemView.findViewById(R.id.textView2);
        }
        public void bindTo(Lotto lotto) {
            textView.setText(lotto.getId() + "");
            textView2.setText(lotto.getNums());
        }
    }

    //---------------------------------------------------------------------
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public static interface OnItemClickListener {
        void onItemClick(View view, Lotto lotto);
    }

    public static interface OnItemLongClickListener {
        void onItemLongClick(View view, Lotto lotto);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (Lotto)view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(view, (Lotto)view.getTag());
            return true;
        }
        return false;
    }
}
