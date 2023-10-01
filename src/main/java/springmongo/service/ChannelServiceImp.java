package springmongo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springmongo.DTO.ChannelResDto;
import springmongo.exception.DataIntegratyViolationException;
import springmongo.exception.ObjectNotFoundException;
import springmongo.model.Channel;
import springmongo.repository.ChannelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelServiceImp implements ChannelService {

    private final ChannelRepository channelRepository;
    private final ModelMapper mapper;

    @Override
    public Channel create(Channel channel) {
        findByEmail(channel);
        return this.channelRepository.save(channel);
    }

    private void findByEmail(Channel channel) {
        Optional<Channel> ch = this.channelRepository.findByEmail(channel.getEmail());

        if (ch.isPresent()) {
            throw new DataIntegratyViolationException("Email já cadastrado");
        }
    }

    @Override
    public List<Channel> findAll() {
        return this.channelRepository.findAll();
    }

    @Override
    public List<ChannelResDto> findAllSearch() {
        List<Channel> channels = this.channelRepository.findAll();
        List<ChannelResDto> channelResDtos = new ArrayList<>();

        for (Channel channel : channels) {
            ChannelResDto channelResDto = mapper.map(channel, ChannelResDto.class);
            channelResDtos.add(channelResDto);
        }

        return channelResDtos;
    }

    @Override
    public Channel findById(String id) {
        Optional<Channel> obj = this.channelRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public Channel update(String id, Channel channel) {

        Optional<Channel> channelExists = this.channelRepository.findById(id);

        if (channelExists != null) {
            this.channelRepository.save(channel);
        }

        return channel;
    }

    @Override
    public boolean delete(String id) {
        this.channelRepository.deleteById(id);
        return false;
    }

}
