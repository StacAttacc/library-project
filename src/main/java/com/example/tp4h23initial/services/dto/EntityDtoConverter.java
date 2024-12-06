package com.example.tp4h23initial.services.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityDtoConverter {
    public static <E,D> List<D> convertList(List<E> entities, Function<E,D> mapper){
        return entities.stream().map(mapper).collect(Collectors.toList());
    }
}
