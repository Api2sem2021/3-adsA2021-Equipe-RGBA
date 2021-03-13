package rgba.SkillShare.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="adm")
public class Adm extends Usuario{
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_adm")
    private Set<Contato> contatos = new HashSet<Contato>();

    public Adm(){}

    public Adm(String cpf,String nome) { //arrumar pra caso a pessoa so tenha o email
        this.setNome(nome);
        this.setCpf(cpf);
    }

    public Set<Contato> getContatos() {
        return this.contatos;
    }

    public void setContatos(Set<Contato> contatos) {
        this.contatos = contatos;
    }

    

}
