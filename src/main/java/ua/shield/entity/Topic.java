package ua.shield.entity;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by sa on 14.07.17.
 * Сущность описывающая тему для создания подписей
 */
public class Topic {
    private Integer id;
    private String describe; //описание
    private LocalDateTime messageDateTime; //время создания
    private User owner; // автор
    private Set<Order> documentSet; //набор документов для подписи
    private Set<Sign> signSet; //список подписей
    private boolean isEditable; //разрешено ли редактировать
}
