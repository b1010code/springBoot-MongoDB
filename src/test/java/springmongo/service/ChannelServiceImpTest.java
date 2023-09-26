package springmongo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import springmongo.DTO.ChannelResDto;
import springmongo.exception.DataIntegratyViolationException;
import springmongo.exception.ObjectNotFoundException;
import springmongo.model.Channel;
import springmongo.repository.ChannelRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//java.util.regex
import static jdk.nashorn.internal.runtime.regexp.joni.constants.OPSize.INDEX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ChannelServiceImpTest {
    public static final String ID = "6511733ede463d51907d66fa";
    public static final String TITLE = "snakers";
    public static final String DESCRIPTION = "canal de interessado em cobra";
    public static final Integer NUMBERCLICKS = 49000;
    public static final String CHANNELADDRESS = "www.youtube.com/watch?v=ArM7f-MLfw8&pp=ygUFY29icmE%3D";
    public static final String EMAIL = "cobra@hotmail.com";
    public static final LocalDate LOCALDATE = LocalDate.of(2021, 9, 25);
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";


    @InjectMocks
    private ChannelServiceImp service;

    @Mock
    private ChannelRepository repository;

    @Mock
    private ModelMapper mapper;

    private Channel channel;
    private ChannelResDto dto;
    private Optional<Channel> optionalChannel;
    private String id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startChannel();
    }

    @Test
    void WhenfindByIdThenReturnAnChannelInstance() {
        when(repository.findById(Mockito.anyString())).thenReturn(optionalChannel);

        Channel response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Channel.class, response.getClass());
        assertEquals(ID, response.getId());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {

        when(repository.findById(anyString()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfChannels() {
        when(repository.findAll()).thenReturn(List.of(channel));

        List<Channel> response = service.findAll();

        assertNotNull(response);
        assertEquals("6511733ede463d51907d66fa", response.size());
        assertEquals(Channel.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(TITLE, response.get(INDEX).getTitle());
        assertEquals(DESCRIPTION, response.get(INDEX).getDescription());
        assertEquals(NUMBERCLICKS, response.get(INDEX).getNumberClicks());
        assertEquals(CHANNELADDRESS, response.get(INDEX).getChannelAddress());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(eq(channel))).thenReturn(channel);

        Channel response = service.create(channel);

        assertNotNull(response);
        assertEquals(Channel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITLE, response.getTitle());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(NUMBERCLICKS, response.getNumberClicks());
        assertEquals(CHANNELADDRESS, response.getChannelAddress());
    }


    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalChannel);

        try {
            optionalChannel.get().setId("6511733ede463d51907d66fa");
            service.create(channel);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyString())).thenReturn(optionalChannel);
        doNothing().when(repository).deleteById(anyString());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyString());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        when(repository.findById(anyString()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startChannel() {
        channel = new Channel(ID, TITLE, DESCRIPTION, NUMBERCLICKS, CHANNELADDRESS, EMAIL, LOCALDATE);
        dto = new ChannelResDto(TITLE, NUMBERCLICKS, LOCALDATE);
        optionalChannel = Optional.of(new Channel(ID, TITLE, DESCRIPTION, NUMBERCLICKS, CHANNELADDRESS, EMAIL, LOCALDATE));
    }

}