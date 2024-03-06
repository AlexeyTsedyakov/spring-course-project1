package org.example.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;

    @NotEmpty(message = "Не заполнено ФИО!")
    @Length(min = 1, max = 100, message = "Длина имени должна быть между 1 и 100 символов!")
    @Pattern(regexp = "[A-Z_А-Я][a-z_а-я]+ [A-Z_А-Я][a-z_а-я]+ [A-Z_А-Я][a-z_а-я]+",
            message = "ФИО должно быть в формате: (Фамилия Имя Отчество)")
    private String name;

    @NotNull(message = "Не заполнен год рождения!")
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900!")
    private Integer yearOfBirth;
}
