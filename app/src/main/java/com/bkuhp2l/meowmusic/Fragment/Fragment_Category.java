package com.bkuhp2l.meowmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Adapter.CategoryAdapter;
import com.bkuhp2l.meowmusic.Adapter.TopicAdapter;
import com.bkuhp2l.meowmusic.Model.Category;
import com.bkuhp2l.meowmusic.Model.Topic;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.APIService;
import com.bkuhp2l.meowmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Category extends Fragment {
    View view;
    TextView txtViewmore;
    ArrayList<Category> arrayCategory;
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerViewTC;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        Mapping();
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Category>> callback = dataService.getDataCategory();
        callback.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                arrayCategory = (ArrayList<Category>) response.body();
                categoryAdapter = new CategoryAdapter(getActivity(), arrayCategory);
                recyclerViewTC.setAdapter(categoryAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewTC.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void Mapping() {
        txtViewmore = (TextView) view.findViewById(R.id.textViewViewmoreCategory);
        recyclerViewTC = (RecyclerView) view.findViewById(R.id.recyclerViewCategory);
    }
}
