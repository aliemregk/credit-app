package com.credit.app.core.utilities.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public final class MapperUtil {

    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    private MapperUtil() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return mapper.map(entity, outClass);
    }

    public static <D, T> Collection<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
}
