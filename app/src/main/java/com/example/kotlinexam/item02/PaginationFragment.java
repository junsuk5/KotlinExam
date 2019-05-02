package com.example.kotlinexam.item02;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kotlinexam.R;
import com.example.kotlinexam.databinding.ItemEmployeeBinding;
import com.example.kotlinexam.item02.models.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaginationFragment extends Fragment {


    public PaginationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PaginationViewModel viewModel = ViewModelProviders.of(this).get(PaginationViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        EmployeeAdapter adapter = new EmployeeAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getEmployees().observe(this, employees -> {
             adapter.setItems(employees);
        });

        viewModel.fetchEmployees(1);
    }

    private static class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
        interface OnClickListener {
            void onEmployeeClicked(Employee model);
        }

        private OnClickListener mListener;

        private List<Employee> mItems = new ArrayList<>();

        public EmployeeAdapter() {
        }

        public EmployeeAdapter(OnClickListener listener) {
            mListener = listener;
        }

        public void setItems(List<Employee> items) {
            this.mItems = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_employee, parent, false);
            final EmployeeViewHolder viewHolder = new EmployeeViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        final Employee item = mItems.get(viewHolder.getAdapterPosition());
                        mListener.onEmployeeClicked(item);
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
            Employee item = mItems.get(position);
            holder.binding.setEmployee(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
            ItemEmployeeBinding binding;

            public EmployeeViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = ItemEmployeeBinding.bind(itemView);
            }
        }
    }
}
