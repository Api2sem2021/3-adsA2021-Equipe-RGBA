package rgba.SkillShare.control;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rgba.SkillShare.model.Arquivo;
import rgba.SkillShare.model.Curso;
import rgba.SkillShare.model.Gestor;
import rgba.SkillShare.model.Thumb;
import rgba.SkillShare.model.Gestor;
import rgba.SkillShare.repository.CursoRepository;
import rgba.SkillShare.repository.GestorRepository;
import rgba.SkillShare.repository.TutorRepository;

/**
 *  Classe que define os endpoints para curso
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/cursos")
@Api("API de curso")
public class CursoController {

    @Autowired 
    CursoRepository cursoRepository;

    @Autowired 
    GestorRepository gestorRepository;

    @Autowired 
    TutorRepository tutorRepository;

    /** 
    *  Endpoint para cadastro de curso.
    * @param curso
    * @author Nicholas Roque
     * @throws IOException
    */
    @PostMapping("/cadastrar")
    @ApiOperation("Cria um curso.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(code = 201,message = "Curso criado com sucesso.")
    public Curso createCurso(@ApiParam("Informações do curso") @RequestParam MultipartFile th, Curso curso, @ApiParam("CPF do gestor") String cpf) throws IOException{
        Arquivo a = new Arquivo(th.getOriginalFilename(),th.getBytes(),th.getContentType());
        Thumb thumb = new Thumb();
        thumb.setArquivo(a);
        
        return cursoRepository.save(curso);
    }

    /** 
    *  Endpoint para listar todos os cursos.
    * @return Retorna uma lista do objeto Curso com todos os cursos. 
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Retorna uma lista com todos os cursos.")
    @ApiResponse(code = 200,message = "Cursos retornados com sucesso.")
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os detalhes de um curso.
    * @return Retorna objeto do tipo Curso com os dados do curso especificado.
    * @param id -> id do curso
    * @author Nicholas Roque
    */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Retorna os detalhes de um curso.")
    @ApiResponses({
        @ApiResponse(code = 200,message = "Curso encontrado com sucesso."),
        @ApiResponse(code = 404,message = "Curso não encontrado para o id informado.")
    })
    public Curso getCursoById(@PathVariable @ApiParam("Id do curso") Long id) {
        return cursoRepository
            .findById(id)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrado.")
            );
    }

    /** 
    *  Endpoint para retornar os cursos com um determinado gestor.
    * @return Retorna uma lista de objetos do tipo Curso.
    * @param cpf -> cpf do gestor
    * @author Nicholas Roque
    */
    @GetMapping("/gestor/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Retorna os cursos com um determinado gestor.")
    @ApiResponses({
        @ApiResponse(code = 200,message = "Cursos encontrados com sucesso para o cpf informado."),
        @ApiResponse(code = 404,message = "Cursos não encontrados para o cpf informado.")
    })
    public List<Curso> getCursosByGestor(@PathVariable @ApiParam("Cpf do gestor") String cpf) {
       return gestorRepository
           .findById(cpf).map(gestor->{
                return gestor.getCursos();
           })
           .orElseThrow(()->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso encontrado.")
           );
       
    }

     /** 
    *  Endpoint para retornar os detalhes de um curso.
    * @return Retorna objeto do tipo Curso com os dados do curso especificado.
    * @param id -> id do curso
    * @author Nicholas Roque
    */
   @GetMapping("/{id}/gestor")
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation("Retorna os detalhes de um gestor relacionado a um curso através do id do curso.")
   @ApiResponses({
       @ApiResponse(code = 200,message = "Gestor encontrado com sucesso."),
       @ApiResponse(code = 404,message = "Curso não encontrado para o id informado.")
   })
   public Gestor getGestorByCursoId(@PathVariable @ApiParam("Id do curso") Long id) {
       return cursoRepository
           .findById(id).map(curso->{
               return curso.getGestor();
           })
           .orElseThrow(()->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrado.")
           );
   }
    /** 
    *  Endpoint para retornar os cursos com um determinado tutor.
    * @return Retorna uma lista de objetos do tipo Curso.
    * @param cpf -> cpf do tutor
    * @author Barbara Port
    */
    /* @GetMapping("/tutor/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Retorna os cursos com um determinado tutor.")
    @ApiResponses({
        @ApiResponse(code = 200,message = "Cursos encontrados com sucesso para o cpf informado."),
        @ApiResponse(code = 404,message = "Cursos não encontrados para o cpf informado.")
    })
    public List<Curso> getCursosByTutor(@PathVariable @ApiParam("Cpf do tutor") String cpf) {
       return tutorRepository
           .findById(cpf).map(tutor->{
                return tutor.getCursos();
           })
           .orElseThrow(()->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso encontrado.")
           );
       
    } */
    
}