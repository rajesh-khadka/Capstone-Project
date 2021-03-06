package com.example.rajesh.expensetracker.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rajesh.expensetracker.ExpenseTrackerApplication;
import com.example.rajesh.expensetracker.R;
import com.example.rajesh.expensetracker.category.ExpenseCategory;
import com.example.rajesh.expensetracker.data.ExpenseTrackerContract;
import com.example.rajesh.expensetracker.expense.ExpenseEditActivity;
import com.example.rajesh.expensetracker.widget.CircularView;

import java.util.ArrayList;

import timber.log.Timber;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    ArrayList<Expense> expense = new ArrayList<>();
    ArrayList<ExpenseCategory> categories = new ArrayList<>();
    Context context;


    public ExpenseAdapter(Context context, ArrayList<Expense> expenses, ArrayList<ExpenseCategory> expenseCategories) {
        this.context = context;
        this.expense = expenses;
        this.categories = expenseCategories;
    }

    @Override
    public ExpenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_expense_item_layout, parent, false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseHolder holder, final int position) {
        holder.expenseTitle.setText(expense.get(position).expenseTitle);
        holder.tvPrice.setText("" + expense.get(position).expenseAmount);
        holder.ivCategoriesIndicator.setFillColor(categories.get(position).categoryColor);

        holder.llDashboardContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                context.startActivity(ExpenseEditActivity.getLaunchIntent(context, expense.get(position), categories.get(position)));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return expense.size();
    }

    public void addExpenses(ArrayList<Expense> expenses, ArrayList<ExpenseCategory> expenseCategories) {
        expense.addAll(expenses);
        categories.addAll(expenseCategories);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        Timber.d("expense id  %d", expense.get(position).expenseId);
        ExpenseTrackerApplication.getExpenseTrackerApplication().getContentResolver().delete(ExpenseTrackerContract.ExpenseEntry.buildExpenseUri(expense.get(position).expenseId), null, null);
        expense.remove(position);
        notifyItemRangeRemoved(position, expense.size());
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public static class ExpenseHolder extends RecyclerView.ViewHolder {
        TextView expenseTitle, tvPrice;
        CircularView ivCategoriesIndicator;
        LinearLayout llDashboardContainer;

        public ExpenseHolder(View itemView) {
            super(itemView);
            expenseTitle = (TextView) itemView.findViewById(R.id.tv_expense_title);
            ivCategoriesIndicator = (CircularView) itemView.findViewById(R.id.iv_categories_indicator);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            llDashboardContainer = (LinearLayout) itemView.findViewById(R.id.ll_dashboard_container);
        }
    }
}
