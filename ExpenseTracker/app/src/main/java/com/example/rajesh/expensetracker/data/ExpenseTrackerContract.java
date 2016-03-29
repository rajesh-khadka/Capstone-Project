package com.example.rajesh.expensetracker.data;


import android.net.Uri;
import android.provider.BaseColumns;

public class ExpenseTrackerContract {
    public static final String CONTENT_AUTHORITY = "com.example.rajesh.expensetracker";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String ACCOUNT_PATH = "account";
    public static final String EXPENSE_PATH = "expense";
    public static final String EXPENSE_CATEGORIES_PATH = "categories";

    public static class AccountEntry implements BaseColumns {

        public static final String TABLE_NAME = "account";

        public static final String COLUMNS_ACCOUNT_TITLE = "account_title";
        public static final String COLUMNS_ACCOUNT_CREATED_DATE = "created_date";
        public static final String COLUMNS_ACCOUNT_AMOUNT = "account_amount";
        public static final String COLUMNS_ACCOUNT_TYPE = "account_type";
    }

    public static class ExpenseEntry implements BaseColumns {
        public static final String TABLE_NAME = "expense";

        public static final String COLUMNS_EXPENSE_DATE = "expense_date";
        public static final String COLUMNS_EXPENSE_TITLE = "expense_title";
        public static final String COLUMNS_EXPENSE_DESCRIPTION = "expense_description";
        public static final String COLUMNS_EXPENSE_RECURRING_TYPE = "expense_recurring_type";
        public static final String COLUMNS_EXPENSE_CATEGORIES_ID = "expense_categories_id";

    }

    public static class ExpenseCategoriesEntry implements BaseColumns {
        public static final String TABLE_NAME = "categories";

        public static final String COLUMNS_CATEGORIES_NAME = "categories_name";
        public static final String COLUMNS_CATEGORIES_COLOR = "categories_color";
    }
}
