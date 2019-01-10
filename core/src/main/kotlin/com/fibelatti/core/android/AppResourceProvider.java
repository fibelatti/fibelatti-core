package com.fibelatti.core.android;

import android.content.Context;
import android.util.Log;
import com.fibelatti.core.provider.ResourceProvider;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * This class is implemented in Java because Kotlin is not properly translating
 * `varargs formatArgs: Any` when invoking `getString(resId, formatArgs)`
 */
public class AppResourceProvider implements ResourceProvider {
    private static final String TAG = AppResourceProvider.class.getSimpleName();

    @NotNull
    private Context context;
    @NotNull
    private Moshi moshi;

    @Inject
    public AppResourceProvider(@NotNull Context context, @NotNull Moshi moshi) {
        this.context = context;
        this.moshi = moshi;
    }

    @NotNull
    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }

    @NotNull
    @Override
    public String getString(int resId, @NotNull Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }

    @Nullable
    @Override
    public <T> T getJsonFromAssets(@NotNull String fileName, @NotNull Class<T> clazz) {
        try (InputStreamReader reader = new InputStreamReader(context.getAssets().open(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(reader);
            String read = bufferedReader.readLine();

            while (read != null) {
                stringBuilder.append(read);
                read = bufferedReader.readLine();
            }

            return moshi.adapter(clazz).fromJson(stringBuilder.toString());
        } catch (Exception exception) {
            Log.d(TAG, TAG + ".getJsonFromAssets", exception);
        }

        return null;
    }
}
