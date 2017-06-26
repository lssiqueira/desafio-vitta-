/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import service.TarefaService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Tarefa;

/**
 * REST Web Service
 *
 * @author Leandro
 */
@Path("tarefa")
public class TarefaResource {
    private TarefaService ts;
                        
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TarefaResource
     */
    public TarefaResource() {
        ts = new TarefaService();
        
    }

    /**
     * GET metodo para pegar todas as tarefas
     * @return json com todas as tarefas
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String GetTarefa(){
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        tarefas = ts.GetTarefas();
//        tarefas.add(new Tarefa("tarefa 1", "Realizada ás: " + getData(),
//                (long) 0, (long) 0.0, "Criado ás: " + getData(), 
//                "Editado ás: ", "Removido ás: "));
        
        Gson gson = new Gson();
        String json = gson.toJson(tarefas);
        
        return json;
    }

    /**
     * GET metodo para pegar tarefas filtradas por realizadas ou não realizadas
     * @param realizada 
     * @return json com as tarefas filtradas
     */
    @Path("tarefaRealizada/{realizada}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String GetTarefaRealizadas(@PathParam("realizada") boolean r){
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        tarefas = ts.GetTarefasRealizadas(r);

        Gson gson = new Gson();
        String json = gson.toJson(tarefas);
        
        return json;
    }
    
    /**
     * GET metodo para pegar tarefas por uma data especifica
     * @param dia dia da tarefa
     * @param mes mês da tarefa
     * @param ano ano da tarefa
     * @return json com as tarefas daquele dia
     */
    @Path("tarefaPorData/{dia}/{mes}/{ano}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String GetTarefaPorData(@PathParam("dia") String dia, @PathParam("mes") String mes, @PathParam("ano") String ano){
        String data = dia+"/"+mes+"/"+ano;
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        tarefas = ts.GetTarefasPorData(data);
        
        Gson gson = new Gson();
        String json = gson.toJson(tarefas);
        
        return json;
    }
    
    /**
     * PUT metodo de atualização de tarefas
     * @param json json vindo do front-end que será atualizado
     * @return json com as informações atualizadas
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String UpdateTarefa(String json) {
        Gson gson = new Gson();
                
        List<Tarefa> tarefa = new ArrayList<Tarefa>();
        tarefa = gson.fromJson(json, new TypeToken<ArrayList<Tarefa>>(){}.getType());
        
        return ts.UpdateTarefa(tarefa.get(0));
    }
    
    /**
     * POST metodo de criação de tarefas
     * @param json json vindo do front-end que será inserido como nova tarefa no arquivo json
     * @return json resultante após a adição das novas informações
     * exemplo de json que o serviço está esperando:
     * [{"id":2,"descricao":"tarefa 3",
     * "dataEHoraRealizada":"Realizada ás: 22/06/2017 - 8:41 - AM","tempoDeDuracao":0,
     * "lembrete":0,"dataEHoraCriacao":"Criado ás: 22/06/2017 - 8:41 - AM",
     * "dataEHoraEdicao":"Editado ás: ","dataEHoraRemocao":"Removido ás: ","realizada":true}]
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String AddTarefa(String json) {
        Gson gson = new Gson();
                
        List<Tarefa> tarefa = new ArrayList<Tarefa>();
        tarefa = gson.fromJson(json, new TypeToken<ArrayList<Tarefa>>(){}.getType());
        
        return ts.AddTarefas(tarefa.get(0));
    }
    
    /**
     * DELETE metodo para deletar tarefas do arquivo json
     * @param id id da tarefa que será deletada
     * @return json com as tarefas restantes
     */
//    @DELETE
//    @Path("tarefaParaDeletar/{idTarefa}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String ExcluirTarefa(int id){
//        Gson gson = new Gson();
//                
//        List<Tarefa> tarefa = new ArrayList<Tarefa>();
//        tarefa = gson.fromJson(json, new TypeToken<ArrayList<Tarefa>>(){}.getType());
        
//        return ts.DeleteTarefa(id);
        
        //return "";
//    }
     
}
