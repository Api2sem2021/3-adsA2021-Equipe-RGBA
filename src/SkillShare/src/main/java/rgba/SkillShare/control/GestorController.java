package rgba.SkillShare.control;

import rgba.SkillShare.model.Gestor;

import rgba.SkillShare.repository.GestorRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *  Classe que define os endpoints para gestor
 *  @author Nicholas Roque
 */
@CrossOrigin
@RestController
@Api("API de gestor")
@RequestMapping("/gestor")
public class GestorController {

    @Autowired 
    GestorRepository gRepository;

    /** 
    *  Endpoint para cadastro de gestor.
    * @param gestor
    * @author Nicholas Roque
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um usuário do tipo gestor.")
    public Gestor createGestor(@RequestBody @ApiParam("Informações do gestor") Gestor gestor){
        return gRepository.save(gestor);
    }

    /** 
    *  Endpoint para listar todos os gestores.
    * @return Retorna uma lista do objeto Gestor com todos os gestores. 
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(code = 200,message = "Usuários retornados com sucesso.")
    @ApiOperation("Retorna uma lista com todos os usuários do tipo gestor")
    public List<Gestor> getAllGestores(){
        return gRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os detalhes de um gestor.
    * @return Retorna objeto do tipo Gestor com os dados do gestor.
    * @param cpf -> cpf do gestor
    * @return Gestor
    * @author Nicholas Roque
    */
    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Retorna os detalhes de um usuário do tipo gestor.")
    @ApiResponses({
        @ApiResponse(code = 200,message = "Usuário do tipo gestor encontrado com sucesso."),
        @ApiResponse(code = 404,message = "Usuário do tipo gestor não encontrado para o cpf informado.")
    })
    public Gestor getGestorByCpf(@PathVariable @ApiParam("Cpf do gestor") String cpf) {
        return gRepository
            .findById(cpf)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário do tipo gestor não encontrado.")
            );
    }
    
/* 	@PostMapping(value = "/update")
	public boolean updateGestor(@RequestBody String data) {
		JSONObject parsedData = new JSONObject(data);
		
		JSONObject admOldData = parsedData.getJSONObject("oldData");
		JSONObject admNewData = parsedData.getJSONObject("newData");
		
		Gestor oldUsuario = new Gestor(admOldData.getString("cpf"), admOldData.getString("nome"), admOldData.getString("email"), admOldData.getString("senha"));
		Gestor newUsuario = new Gestor(admNewData.getString("cpf"), admNewData.getString("nome"), admNewData.getString("email"), admNewData.getString("senha"));
		
		try{
			gRepository.delete(oldUsuario);
			gRepository.save(newUsuario);
			
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping(value = "/delete")
	public boolean deleteGestor(@RequestBody Gestor gestor) {
		try {
			gRepository.deleteById(gestor.getCpf());
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
 */
    /** 
    *  Endpoint para deletar um gestor especificado pelo cpf.
    * @param cpf-> cpf do gestor a ser deletado
    * @author Nicholas Roque
    */
    @DeleteMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta o gestor especificado pelo cpf.")
    @ApiResponses({
        @ApiResponse(code = 204,message = "Gestor deletado com sucesso."),
        @ApiResponse(code = 404,message = "Gestor não encontrado para o cpf informado.")
    })
    public void deleteGestorByCpf(@PathVariable @ApiParam("Cpf do gestor") String cpf) {
        gRepository
            .findById(cpf)
            .map(g->{
                gRepository.delete(g);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Gestor não encontrado.")         
            );
    }

    /** 
    *  Endpoint para atualizar um gestor especificado pelo id.
    * @param cpf-> cpf do gestor a ser atualizado
    * @param gestor-> objeto do gestor a ser atualizado
    * @author Nicholas Roque
    */
    @PutMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Atualiza o gestor especificado pelo cpf.")
    @ApiResponses({
        @ApiResponse(code = 204,message = "Gestor atualizado com sucesso."),
        @ApiResponse(code = 404,message = "Gestor não encontrado para o cpf informado.")
    })
    public void updateGestorByCpf(@PathVariable @ApiParam("Cpf do gestor") String cpf,@RequestBody @ApiParam("Gestor atualizado") Gestor gestor) {
        gRepository
            .findById(cpf)
            .map(g->{
                gestor.setSenha(g.getSenha());
                gRepository.save(gestor);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Gestor não encontrado.")         
            );
    }
	
}