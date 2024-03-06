package org.example.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;

    private Long personId;

    @NotEmpty(message = "Не заполнено имя книги!")
    @Length(min = 1, max = 100, message = "Длина имени должна быть между 1 и 100 символов!")
    private String name;

    @NotEmpty(message = "Не заполнен автор!")
    @Length(min = 2, max = 50, message = "Длина имени автора должна быть между 2 и 50 символов!")
    private String author;

    @NotNull(message = "Не заполнен год!")
    @Min(value = 600, message = "Год должен быть больше, чем 600!")
    private Integer year;
}
