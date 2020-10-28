package com.appr.digibiz.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.appr.digibiz.R;
import com.appr.digibiz.models.InventoryModel;

import org.parceler.Parcels;

public class DeleteInvetoryDialogFragment extends DialogFragment {
    private InventoryModel resultInventory;

    public DeleteInvetoryDialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DeleteInvetoryDialogFragment newInstance(InventoryModel inventoryItem) {
        DeleteInvetoryDialogFragment fragment = new DeleteInvetoryDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("toDeleteDialog", Parcels.wrap(inventoryItem));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
//                resultInventory = Parcels.unwrap(bundle.getParcelable("toDeleteDialog"));
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_invetory_dialog, container, false);
    }
}