package springmongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigInteger;
import java.time.LocalDate;



@Getter
@Setter
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

    @JsonProperty("checkIn")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkIn;

    @JsonProperty("checkOut")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkOut;

    @NotBlank(message = "Número de clicks obrigatório")
    private BigInteger numberClicks;

}
