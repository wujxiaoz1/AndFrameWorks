package com.andframe.annotation.listener;

import android.support.annotation.IdRes;
import android.view.View;

import com.andframe.annotation.listener.internal.ListenerClass;
import com.andframe.annotation.listener.internal.ListenerMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static android.widget.AdapterView.OnItemClickListener;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Bind a method to an {@link OnItemClickListener OnItemClickListener} on the view for each ID
 * specified.
 * <pre><code>
 * {@literal @}BindOnItemClick(R.id.example_list) void onItemClick(int position) {
 *   Toast.makeText(this, "Clicked position " + position + "!", Toast.LENGTH_SHORT).show();
 * }
 * </code></pre>
 * Any number of parameters from {@link OnItemClickListener#onItemClick(android.widget.AdapterView,
 * View, int, long) onItemClick} may be used on the method.
 *
 * @see OnItemClickListener
 */
@Target(METHOD)
@Retention(CLASS)
@ListenerClass(
        targetType = "android.widget.AdapterView<?>",
        setter = "setOnItemClickListener",
        type = "android.widget.AdapterView.OnItemClickListener",
        method = @ListenerMethod(
                name = "onItemClick",
                parameters = {
                        "android.widget.AdapterView<?>",
                        "android.view.View",
                        "int",
                        "long"
                }
        )
)
public @interface BindOnItemClick {
    /**
     * View IDs to which the method will be bound.
     */
    @IdRes int[] value() default {View.NO_ID};
	String[] idname() default {""};
}