package com.example.myapplication.utils;

public class CategoriesNames {
    public static String[] categories = {"ספא", "שופינג", "הופעות", "אטרקציות", "מסעדות", "ספורט"};

    public static String[] getCategories() {
        return categories;
    }

    public static String getCategoryById(Long id) {
        return categories[id.intValue()];
    }

    public static Long getIdByCategory(String category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(category)) {
                return (long) i+1;
            }
        }
        return null;
    }
}
