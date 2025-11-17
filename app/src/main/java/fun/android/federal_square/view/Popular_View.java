package fun.android.federal_square.view;

import android.content.Context;
import android.view.View;
import fun.android.federal_square.R;

public class Popular_View extends View_Main{
    public Popular_View(Context context) {
        super(context);
        view = View.inflate(context, R.layout.popular_view, null);
        linear = view.findViewById(R.id.linear);
    }
}
