package us.antenado.restnews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.antenado.restnews.rest.Rest;
import us.antenado.restnews.rest.dto.SimpleNewsDTO;

public class MainActivity extends AppCompatActivity implements OnListItemInteractor {

    RecyclerView recyclerView;
    List<SimpleNewsDTO> news = new ArrayList<>();
    public static final int NEWS_REQUEST = 101;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;

        recyclerView = (RecyclerView) findViewById(R.id.news_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new NewsRecyclerViewAdapter(news, this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(FormNews.getCreateIntent(ctx), NEWS_REQUEST);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshNews();
    }

    private void refreshNews() {
        Call<List<SimpleNewsDTO>> newsCall = Rest.newsService().getAll();

        newsCall.enqueue(new Callback<List<SimpleNewsDTO>>() {
            @Override
            public void onResponse(Call<List<SimpleNewsDTO>> call, Response<List<SimpleNewsDTO>> response) {
                if (response.isSuccessful()) {
                    news.clear();
                    news.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<SimpleNewsDTO>> call, Throwable t) {
                Log.e(getClass().getCanonicalName(), t.getMessage());
            }
        });
    }


    private void deleteNews(String id) {
        Call<Void> deleteCall = Rest.newsService().deleteNews(id);

        deleteCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ctx, "Noticia apagada", Toast.LENGTH_SHORT).show();
                    refreshNews();
                } else {
                    Toast.makeText(ctx, "Erro ao apagar noticia", Toast.LENGTH_SHORT).show();
                    refreshNews();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ctx, "Erro ao apagar noticia", Toast.LENGTH_SHORT).show();
                refreshNews();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(SimpleNewsDTO item, InteractionType interactionType) {

        switch (interactionType) {
            case VIEW: {
                startActivity(NewsViewActivity.getIntent(this, item.getId()));
                break;
            }
            case DELETE: {
                deleteNews(item.getId());
                break;
            }
            case EDIT: {
                startActivityForResult(FormNews.getEditIntent(this, item.getId()), NEWS_REQUEST);
                break;
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEWS_REQUEST) {
            refreshNews();
        }
    }
}
