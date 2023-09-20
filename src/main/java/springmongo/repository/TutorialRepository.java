package springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springmongo.model.Tutorial;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial,String> {
}
