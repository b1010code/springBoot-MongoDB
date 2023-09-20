package springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import springmongo.model.MeetingChannels;

public interface MeetChRepository extends MongoRepository<MeetingChannels,String> {
}
