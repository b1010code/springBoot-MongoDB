package springmongo.service;

import springmongo.DTOs.TutorialResDto;
import springmongo.model.Channel;

import java.util.List;

public interface ChannelService {
    public Channel create(Channel tutorial);
    public List<Channel> findAll();
    public TutorialResDto findRespAll(String id);
    public Channel findById(String id);
    public Channel update (String id, Channel tutorial);
    public boolean delete(String id);

}
