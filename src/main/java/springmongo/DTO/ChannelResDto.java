package springmongo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelResDto {
    private String title;
    private Integer numberClicks;
    private LocalDate registrationDate;
}
