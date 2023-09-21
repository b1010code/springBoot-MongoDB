package springmongo.service;

import springmongo.DTO.ChannelResDto;
import springmongo.model.Channel;

import java.util.List;

public interface ChannelService {
    public Channel create(Channel channel);
    public List<Channel> findAll();
    public ChannelResDto findRespAll(String id);
    public Channel findById(String id);
    public Channel update (String id, Channel channel);
    public boolean delete(String id);

}
