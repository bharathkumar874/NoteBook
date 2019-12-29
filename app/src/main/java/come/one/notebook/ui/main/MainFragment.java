package come.one.notebook.ui.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import come.one.notebook.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        RelativeLayout book1Layout = getView().findViewById(R.id.book2LayoutImg);
        Drawable background = book1Layout.getBackground();
        setNoteBookStyle(book1Layout, "#441254", "#711855");

        RelativeLayout book2Layout = getView().findViewById(R.id.book3LayoutImg);
        setNoteBookStyle(book2Layout, "#fad35e", "#f76d1b");

        View.OnClickListener onAddNewClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUI();
            }
        };
        getView().findViewById(R.id.addNew).setOnClickListener(onAddNewClick);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setNoteBookStyle(RelativeLayout bookLayout, String startColor, String endColor) {
        Drawable background = bookLayout.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(this.getActivity(), R.color.majorColor));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientBg = setNoteBookColor(startColor, endColor);
            //apply the button background to newly created drawable gradient
            bookLayout.setBackground(gradientBg);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(ContextCompat.getColor(this.getActivity(), R.color.testColor2));

        }
    }

    private GradientDrawable setNoteBookColor(String startColor, String endColor) {
        //GradientDrawable gradientDrawable = (GradientDrawable) background;
        int[] colors = {Color.parseColor(startColor), Color.parseColor(endColor)};

        //create a new gradient color
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);

        gd.setCornerRadii(new float[]{
                3f, 3f, 10f, 10f, 10f, 10f, 3f, 3f
        });
        return gd;
    }

    private void setUI() {
        NewDraft newDraft = NewDraft.newInstance();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, newDraft, "newDraftFragment")
                .addToBackStack(null)
                .commit();
    }

}
