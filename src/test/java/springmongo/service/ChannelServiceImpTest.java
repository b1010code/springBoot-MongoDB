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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ChannelServiceImpTest {
    private static final Integer INDEX   = 0;
    public static final String ID = "6518a3f02a93d936b260235e";
    public static final String TITLE = "Bikers";
    public static final String DESCRIPTION = "canal sobre bicicleta";
    public static final Integer NUMBERCLICKS = 17000;
    public static final String CHANNELADDRESS = "https://www.bikers.com";
    public static final String EMAIL = "bikers@hotmail.com";
    public static final LocalDate LOCALDATE = LocalDate.of(2018, 9, 19);
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
        assertEquals(1, response.size());
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
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(channel);

        Channel response = service.update(id, channel);

        assertNotNull(response);
        assertEquals(Channel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITLE, response.getTitle());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(NUMBERCLICKS, response.getNumberClicks());
        assertEquals(CHANNELADDRESS, response.getChannelAddress());
    }

    /*
    // Cria um mock do repositório
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        // Instancia o serviço
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        // Chama o método update
        Usuario usuario = usuarioService.update(new Usuario());

        // Valida o resultado
        assertThat(usuario.getId()).isNotNull();
     */

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