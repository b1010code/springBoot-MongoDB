package springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springmongo.model.MeetingChannels;

@Repository
public interface MeetChRepository extends MongoRepository<MeetingChannels,String> {
}
