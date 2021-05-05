package rgba.SkillShare.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 *  Classe que define o curso
 *  @author Nicholas Roque
 */
@Entity(name="curso")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    private List<Pilula> pilulas;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Questao> questoes;

    @ManyToOne
    @JoinColumn(name="id_gestor")
    @JsonIgnore //ignora o gestor no retorno do json
    private Gestor gestor;
    
    @ManyToOne
    @JoinColumn(name="id_tutor")
    @JsonIgnore //ignora o gestor no retorno do json
    private Gestor tutor;


    /** 
    *  Cria uma instância da classe Curso.
    * @param titulo -> Título do curso.
    * @param descricao -> Descrição do curso.
    * @author Nicholas Roque
    */

    public Curso(String titulo,String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Curso(Long id){
        this.id = id;
    }
}