package fun.android.federal_square.child_view;

import android.content.Context;
import android.view.View;

import fun.android.federal_square.R;

public class Article_View {
    private View view;
    public Article_View(Context context){
        view = View.inflate(context, R.layout.article_view, null);
        view.setTag("article_view");
    }

    public View getView(){
        return view;
    }

}
