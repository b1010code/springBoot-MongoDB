package springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springmongo.model.Channel;

import java.util.Optional;

@Repository
public interface ChannelRepository extends MongoRepository<Channel,String> {
    Optional<Channel> findByEmail(String email);
}
