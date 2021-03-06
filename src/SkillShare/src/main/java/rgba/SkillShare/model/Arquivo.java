package rgba.SkillShare.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 *  Classe que define o arquivo
 *  @author Nicholas Roque
 */
@Entity(name="arquivo")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Lob
    @Column(nullable = false)
    private byte[] conteudo;

    @Column(nullable = false)
    private String tipoArquivo;

    /** 
    *  Cria uma instância da classe Arquivo.
    * @param nomeArquivo -> nome do arquivo
    * @param conteudo -> arquivo
    * @param tipoArquivo -> tipoArquivo
    * @author Nicholas Roque
    */
    public Arquivo(String nomeArquivo,byte[] conteudo,String tipoArquivo){
        this.nomeArquivo = nomeArquivo;
        this.conteudo = conteudo;
        this.tipoArquivo = tipoArquivo;
    }

}
