package com.meeting.planner.dto.mapper;

import java.util.Collection;
import java.util.List;

public interface MapClassWithDto<E, D> {
	
    public D convertToDto(E entity, Class<D> dtoClass);

    public E convertToEntity(D dto, Class<E> entityClass);

    public List<D> convertListToListDto(Collection<E> entityList, Class<D> outCLass);

    public List<E> convertListToListEntity(Collection<D> dtoList, Class<E> outCLass);
    
}