package com.example.aula_2022_03_25.dao;

import com.example.aula_2022_03_25.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeIds = 1;

    //Salvando os dados do personagem e atualizando a id
    public void salva(Personagem personagemSalvo){
        personagemSalvo.setId(contadorDeIds);
        personagens.add(personagemSalvo);
        atualizaId();
    }

    //aumentando o valor do contador
    private void atualizaId(){ contadorDeIds++;}

    //Buscando o personagem para editar
    public void edita(Personagem personagem){
        Personagem personagemEncontrado = buscaPersonagemId(personagem);
        //Se não encontrar o personagem, vai setar a posição dele
        if(personagemEncontrado != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEncontrado);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }

    //Buscando o personagem pela Id
    private Personagem buscaPersonagemId(Personagem personagem){
        for(Personagem p:
                personagens){
            if(p.getId() == personagem.getId()){
                return p;
            }
        }
        return null;
    }

    //Uma lista para todos os personagens criados
    public List<Personagem> todos(){ return new ArrayList<>(personagens);}

    //Método para o usuário excluir personagens feitos
    public void remove(Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if(personagemDevolvido != null){
            personagens.remove(personagemDevolvido);
        }
    }
}
