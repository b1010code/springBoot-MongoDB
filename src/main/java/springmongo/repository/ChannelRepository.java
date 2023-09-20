package springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springmongo.model.Channel;

@Repository
public interface ChannelRepository extends MongoRepository<Channel,String> {
}
