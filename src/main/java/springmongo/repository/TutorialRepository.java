package springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import springmongo.model.Tutorial;


public interface TutorialRepository extends MongoRepository<Tutorial,String> {
}
