package me.vickychijwani.spectre.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import me.vickychijwani.spectre.model.Post;

public class AppUtils {

    public static String pathJoin(String basePath, String relativePath) {
        return Uri.withAppendedPath(Uri.parse(basePath), relativePath).toString();
    }

    public static void hideKeyboard(@NonNull Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean isDraft(@NonNull Post post) {
        return ! DateTimeUtils.isValidDate(post.getPublishedAt());
    }

}
