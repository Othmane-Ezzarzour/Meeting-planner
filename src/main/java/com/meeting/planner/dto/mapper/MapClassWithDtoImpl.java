package com.meeting.planner.dto.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapClassWithDtoImpl<E, D> implements MapClassWithDto<E, D> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public D convertToDto(E entity, Class<D> dtoClass) {
        if(entity == null){
            return null;
        }
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(entity,dtoClass);
    }

    @Override
    public E convertToEntity(D dto, Class<E> entityClass) {
        if(dto == null)
            return null;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<D> convertListToListDto(Collection<E> entityList, Class<D> outCLass) {
        if(entityList == null)
            return Collections.emptyList();

        return entityList.stream().map(entity -> convertToDto(entity, outCLass)).collect(Collectors.toList());
    }

    @Override
    public List<E> convertListToListEntity(Collection<D> dtoList, Class<E> outCLass) {
        if(dtoList == null)
            return Collections.emptyList();

        return dtoList.stream().map(dto -> convertToEntity(dto, outCLass)).collect(Collectors.toList());
    }

}