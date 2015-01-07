package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vincetulit on 1/6/15.
 */
public class EditItemDialog extends DialogFragment{

    private EditText etEditName;
    private EditText etEditDueDate;
    private int pos;

    public EditItemDialog() {
        // Empty constructor required for DialogFragment
    }

    public static EditItemDialog newInstance(String title, String itemName,
                                             String itemDueDate, int pos) {
        EditItemDialog frag = new EditItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("itemName", itemName);
        args.putString("itemDueDate", itemDueDate);
        args.putInt("pos",pos);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_edit_item, container);

        etEditName = (EditText) view.findViewById(R.id.etEditItem);
        etEditDueDate = (EditText) view.findViewById(R.id.etEditDueDate);

        String title = getArguments().getString("title", "Enter Name");
        String itemName = getArguments().getString("itemName");
        String itemDueDate = getArguments().getString("itemDueDate");
        pos = getArguments().getInt("pos");

        getDialog().setTitle(title);
        etEditName.setText(itemName);
        etEditDueDate.setText(itemDueDate);

        etEditName.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        setupSaveButtonListener(view);
        return view;
    }

    public void setupSaveButtonListener(View view) {
        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditItemDialogListener listener = (EditItemDialogListener) getActivity();
                        listener.onFinishEditItemDialog(
                                etEditName.getText().toString(),
                                etEditDueDate.getText().toString(),
                                pos);
                        dismiss();
                    }
                }
        );
    }

    public interface EditItemDialogListener {
        public void onFinishEditItemDialog(String itemName, String itemDueDate, int pos);
    }

}
