package springmongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigInteger;
import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Channel")
public class Channel {
    @Id
    private String id;

    @NotBlank(message = "Título obrigatório")
    private String title;

    @NotBlank(message = "Descrição obrigatória")
    @Field(targetType = FieldType.STRING)
    private String description;

    @NotBlank(message = "Clicks obrigatório")
    private BigInteger numberClicks;

    @NotBlank(message = "Endereço do canal obrigatório")
    private  String channelAddress;

    @NotBlank(message = "Email obrigatório")
    private String email;

    @JsonProperty("registrationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotBlank(message = "Data obrigatório")
    private LocalDate registrationDate;
}
