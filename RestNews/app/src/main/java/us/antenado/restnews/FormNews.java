package us.antenado.restnews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.antenado.restnews.rest.Rest;
import us.antenado.restnews.rest.dto.FullNewsDTO;

public class FormNews extends AppCompatActivity implements Callback<Void> {

    public static final String ID_KEY = "news_id";
    public static final String EDIT = "edit";
    private String newsId;
    private boolean edit;
    Button saveButton, cancelButton;
    EditText mTitle, mContent;
    AppCompatActivity ctx;
    FullNewsDTO fullNewsDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;

        getExtras();

        saveButton = (Button) findViewById(R.id.bt_save);
        cancelButton = (Button) findViewById(R.id.bt_cancel);
        mTitle = (EditText) findViewById(R.id.et_title);
        mContent = (EditText) findViewById(R.id.et_content);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit) {
                    saveEdit();
                } else {
                    save();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctx.finish();
            }
        });

    }

    private boolean validateSave() {

        boolean valid = true;
        View focusView = null;

        mTitle.setError(null);
        mContent.setError(null);

        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();

        if (TextUtils.isEmpty(title)) {
            valid = false;
            focusView = mTitle;
            mTitle.setError("Requerido");
        }
        if (TextUtils.isEmpty(content)) {
            valid = false;
            focusView = mContent;
            mContent.setError("Requerido");
        }

        if (!valid) {
            focusView.requestFocus();
        }

        return valid;
    }

    private void saveEdit() {
        if (validateSave()) {
            String title = mTitle.getText().toString();
            String content = mContent.getText().toString();

            fullNewsDTO.setContent(content);
            fullNewsDTO.setTitle(title);

            Call<Void> editCall = Rest.newsService().updateNews(fullNewsDTO.getId(), fullNewsDTO);

            editCall.enqueue(this);

        }
    }

    private void save() {
        if (validateSave()) {
            String title = mTitle.getText().toString();
            String content = mContent.getText().toString();

            fullNewsDTO.setContent(content);
            fullNewsDTO.setTitle(title);

            Call<Void> editCall = Rest.newsService().createNews(fullNewsDTO);

            editCall.enqueue(this);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (edit) {
            Call<FullNewsDTO> newsCall = Rest.newsService().getSpecific(newsId);

            newsCall.enqueue(new Callback<FullNewsDTO>() {
                @Override
                public void onResponse(Call<FullNewsDTO> call, Response<FullNewsDTO> response) {
                    if (response.isSuccessful()) {
                        fullNewsDTO = response.body();
                        mTitle.setText(fullNewsDTO.getTitle());
                        mContent.setText(fullNewsDTO.getContent());
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
        } else {
            fullNewsDTO = new FullNewsDTO();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getExtras() {
        Bundle b = getIntent().getExtras();
        edit = b.getBoolean(EDIT);
        if (edit) {
            newsId = b.getString(ID_KEY);
        }

    }

    public static Intent getEditIntent(Context ctx, String id) {
        Intent it = new Intent(ctx, FormNews.class);
        it.putExtra(ID_KEY, id);
        it.putExtra(EDIT, true);
        return it;
    }

    public static Intent getCreateIntent(Context ctx) {
        Intent it = new Intent(ctx, FormNews.class);
        it.putExtra(EDIT, false);
        return it;
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
        finish();
    }
}
