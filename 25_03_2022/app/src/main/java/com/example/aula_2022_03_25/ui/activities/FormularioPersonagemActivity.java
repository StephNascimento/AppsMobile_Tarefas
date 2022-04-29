package com.example.aula_2022_03_25.ui.activities;

import static com.example.aula_2022_03_25.ui.activities.ConstatesActivities.CHAVE_PERSONAGEM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.aula_2022_03_25.R;
import com.example.aula_2022_03_25.dao.PersonagemDAO;
import com.example.aula_2022_03_25.model.Personagem;
/*import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;*/

public class FormularioPersonagemActivity extends Activity {

    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar o Personagem";
    private static final  String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    //Superclasse que referencia o menu de salvar o formulário
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Superclasse está salvando e finalizando o formulário
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int itemId  = item.getItemId();
        //Chamando o método para finalizar
        if(itemId == R.id.activity_formulario_personagem_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    //No que o usuário entra no formulário, esse método é buscado pelo app em seguida
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagens);
        inicializacaoCampos();
        carregaPersonagem();
        //checaPermissoes();
    }

    //Método para carregar o personagem criado ou novo
    private void carregaPersonagem(){
        Intent dados = getIntent();
        //Se já tiver dados sobre um personagem, abre para editar
        if(dados.hasExtra(CHAVE_PERSONAGEM)){
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        }
        //Se não irá normalmente ir criar o personagem
        else{
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    //Método onde o usuário irá preenhcer os dados do personagem no formulário
    private void preencherCampos(){
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    //AUsuário terminou de preencher o formulário e esse método finaliza ele
    private void finalizarFormulario(){
        preencherPersonagem();
        //Se os dados forem alterados irá mandar as modificaçõe ao código e finalizar
        if(personagem.IdValido()){
            dao.edita(personagem);
            finish();
        }
        //Salvará normalmente
        else{
            dao.salva(personagem);
        }
        finish();
    }

    //Pegando as informações que o usuário está entregando ao aplicativo
    private void inicializacaoCampos(){
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }

    //Colocando os dados dentro das variáveis
    private void preencherPersonagem(){
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}
