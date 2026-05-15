package mvc.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder

public record APIResponseTamplete<T>(
        int status,
        String message,
        LocalDate timeStamp,
        T data
) {


}
