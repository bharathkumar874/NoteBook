package come.one.notebook.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import come.one.notebook.R;

public class NewDraft extends Fragment {

    private NewDraftViewModel mViewModel;

    public static NewDraft newInstance() {
        return new NewDraft();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_draft_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewDraftViewModel.class);
        // TODO: Use the ViewModel

        //Go back on clicking the back button
        View.OnClickListener backButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        };

        View.OnClickListener saveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDraft();
                goBack();
            }
        };

        //Save and go back on
        getView().findViewById(R.id.back_btn).setOnClickListener(backButtonListener);
        getView().findViewById(R.id.save_btn).setOnClickListener(saveListener);
    }

    private void goBack() {
        getFragmentManager().popBackStack();
    }

    private void saveDraft() {
        //Save new draft to local DB
        Toast.makeText(getContext(), "Your draft is saved.", Toast.LENGTH_LONG).show();
    }

}
