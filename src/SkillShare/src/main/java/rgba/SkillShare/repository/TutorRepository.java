package rgba.SkillShare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rgba.SkillShare.model.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, String>{
    
}