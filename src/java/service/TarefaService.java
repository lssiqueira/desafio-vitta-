/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import modelo.Tarefa;

/**
 *
 * @author Leandro
 */
public class TarefaService {
    File file;
    FileReader fReader;
    FileWriter fWriter;
    
    //Pega todas as tarefas no arquivo Json
    public List<Tarefa> GetTarefas()
    {
        BufferedReader bReader = null;
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        
        //caso o arquivo json não exista, um novo é criado
        try {
            file = new File("tarefas.json");
            fReader = new FileReader(file);
            bReader = new BufferedReader(fReader);
        } catch (FileNotFoundException ex) {
            try {
                file.createNewFile();
                return tarefas;
            } catch (IOException ex1) {
                Logger.getLogger(TarefaService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        try {
            if(bReader.ready())
            {
                Gson gson = new Gson();
                tarefas = gson.fromJson(bReader, new TypeToken<ArrayList<Tarefa>>(){}.getType());
            }
            else
                return tarefas;
        } catch (IOException ex) {
            Logger.getLogger(TarefaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tarefas;
    }
    
    //pega as tarefas filtrando pelas realizadas ou pelas não realizadas
    public List<Tarefa> GetTarefasRealizadas(boolean realizada)
    {
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        List<Tarefa> tarefasFiltradas = new ArrayList<Tarefa>();
        
        tarefas = GetTarefas();
        
        for(int i = 0; i < tarefas.size(); i++)
        {
            if(tarefas.get(i).isRealizada() == realizada)
            {
                tarefasFiltradas.add(tarefas.get(i));
            }
        }
        
        return tarefasFiltradas;
    }
     
    //pega as tarefas de uma data especifica
    public List<Tarefa> GetTarefasPorData(String data)
    {
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        List<Tarefa> tarefasFiltradas = new ArrayList<Tarefa>();
        
        tarefas = GetTarefas();
        
        for(int i = 0; i < tarefas.size(); i++)
        {
            if(tarefas.get(i).getDataEHoraCriacao().contains(data))
            {
                tarefasFiltradas.add(tarefas.get(i));
            }
        }
        
        return tarefasFiltradas;
    }
    
    //adiciona uma nova tarefas ao json
    public String AddTarefas(Tarefa tarefa)
    {
        List<Tarefa> tarefasSalvas = GetTarefas();
        
        tarefasSalvas.add(tarefa);
        
        return SaveTarefas(tarefasSalvas);
    }
    
    //salva as tarefas no json
    public String SaveTarefas(List<Tarefa> tarefas)
    {        
        BufferedWriter bWriter = null;
        BufferedReader bReader = null;
        Gson gson = new Gson();
        try {
            fWriter = new FileWriter(file);
            bReader = new BufferedReader(fReader);
            bWriter = new BufferedWriter(fWriter);
            String json = gson.toJson(tarefas);
            
            bWriter.write(json);
            
            bWriter.close();
            bReader.close();
            
            return json;
        } catch (IOException ex) {
            Logger.getLogger(TarefaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    //atualiza os dados de uma tarefa
    public String UpdateTarefa(Tarefa tarefa){
        List<Tarefa> tarefasSalvas = GetTarefas();
        
        for(int i = 0; i < tarefasSalvas.size(); i++){
            if(tarefasSalvas.get(i).getId() == tarefa.getId()){
                tarefasSalvas.get(i).setId(tarefa.getId());
                tarefasSalvas.get(i).setDescricao(tarefa.getDescricao());
                tarefasSalvas.get(i).setTempoDeDuracao(tarefa.getTempoDeDuracao());
                tarefasSalvas.get(i).setLembrete(tarefa.getLembrete());
                tarefasSalvas.get(i).setDataEHoraCriacao(tarefa.getDataEHoraCriacao());
                tarefasSalvas.get(i).setDataEHoraEdicao(tarefa.getDataEHoraEdicao());
                tarefasSalvas.get(i).setDataEHoraRemocao(tarefa.getDataEHoraRemocao());
                
                SaveTarefas(tarefasSalvas);
                Gson gson = new Gson();
                return gson.toJson(tarefasSalvas);
            }
        }
        return "";
    }
    
    //deleta uma tarefa do json
//    public String DeleteTarefa(int idTarefa){
//        List<Tarefa> tarefasSalvas = GetTarefas();
//        
//        for(int i = 0; i < tarefasSalvas.size(); i++){
//            if(tarefasSalvas.get(i).getId() == idTarefa){
//                tarefasSalvas.remove(i);
//                return SaveTarefas(tarefasSalvas);
//            }
//        }
//        
//        return "";
//    }
}
