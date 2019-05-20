package com.fibelatti.core.android;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fibelatti.core.provider.ResourceProvider;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * This class is implemented in Java because Kotlin is not properly translating
 * `varargs formatArgs: Any` when invoking `getString(resId, formatArgs)`
 */
public class AppResourceProvider implements ResourceProvider {

    private static final String TAG = AppResourceProvider.class.getSimpleName();

    @NonNull
    private Context context;

    @Inject
    public AppResourceProvider(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }

    @NonNull
    @Override
    public String getString(int resId, @NonNull Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }

    @Nullable
    @Override
    public String getJsonFromAssets(@NonNull String fileName) {
        try (InputStreamReader reader = new InputStreamReader(context.getAssets().open(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(reader);
            String read = bufferedReader.readLine();

            while (read != null) {
                stringBuilder.append(read);
                read = bufferedReader.readLine();
            }

            return stringBuilder.toString();
        } catch (Exception exception) {
            Log.d(TAG, TAG + ".getJsonFromAssets", exception);
        }

        return null;
    }
}
