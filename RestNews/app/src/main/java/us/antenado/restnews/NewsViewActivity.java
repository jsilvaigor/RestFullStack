package us.antenado.restnews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.antenado.restnews.rest.Rest;
import us.antenado.restnews.rest.dto.FullNewsDTO;

public class NewsViewActivity extends AppCompatActivity {

    public static final String ID_KEY = "news_id";
    private String newsId;
    private TextView title, created, updated, content;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detalhes da noticia");
        setSupportActionBar(toolbar);
        getExtras();
        ctx = this;

        title = (TextView) findViewById(R.id.tv_title);
        created = (TextView) findViewById(R.id.tv_created);
        updated = (TextView) findViewById(R.id.tv_updated);
        content = (TextView) findViewById(R.id.tv_content);

        getNews();
    }

    private void getExtras() {
        Bundle b = getIntent().getExtras();
        newsId = b.getString(ID_KEY);
    }

    public static Intent getIntent(Context ctx, String id) {
        Intent it = new Intent(ctx, NewsViewActivity.class);
        it.putExtra(ID_KEY, id);
        return it;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void getNews() {
        Call<FullNewsDTO> newsCall = Rest.newsService().getSpecific(newsId);

        newsCall.enqueue(new Callback<FullNewsDTO>() {
            @Override
            public void onResponse(Call<FullNewsDTO> call, Response<FullNewsDTO> response) {
                if (response.isSuccessful()) {
                    FullNewsDTO n = response.body();
                    title.setText(n.getTitle());
                    created.setText("Criado em: " + n.getCreatedAt().toGMTString());
                    updated.setText("Atualizado em: " + n.getUpdatedAt().toGMTString());
                    content.setText(n.getContent());
                } else {
                    Toast.makeText(ctx, "Erro ao buscar noticia", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<FullNewsDTO> call, Throwable t) {
                Log.e(getClass().getCanonicalName(), t.getMessage());
            }
        });
    }
}
