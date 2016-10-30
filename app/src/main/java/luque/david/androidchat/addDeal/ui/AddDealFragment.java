package luque.david.androidchat.addDeal.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import luque.david.androidchat.R;
import luque.david.androidchat.addDeal.AddDealPresenter;
import luque.david.androidchat.addDeal.AddDealPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDealFragment extends DialogFragment implements AddDealView, DialogInterface.OnShowListener {


    @Bind(R.id.editTxtName)
    EditText editTxtName;

    @Bind(R.id.editTxtPrice)
    EditText editTxtPrice;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    AddDealPresenter presenter;

    public AddDealFragment() {
        // Required empty public constructor
        this.presenter = new AddDealPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.adddeal_message_title)
                .setPositiveButton(R.string.adddeal_message_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(R.string.adddeal_message_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_deal, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog)getDialog();

        if(dialog != null){
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    presenter.addDeal(editTxtName.getText().toString(), editTxtPrice.getText().toString());
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        presenter.onShow();
    }

    @Override
    public void showInput() {
        editTxtName.setVisibility(View.VISIBLE);
        editTxtPrice.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editTxtName.setVisibility(View.GONE);
        editTxtPrice.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void dealAdded() {
        dismiss();
    }

    @Override
    public void dealNotAdded() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
