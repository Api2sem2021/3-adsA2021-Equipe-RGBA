package rgba.SkillShare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rgba.SkillShare.model.Adm;

public interface AdmRepository extends JpaRepository<Adm, String>{
    
}
