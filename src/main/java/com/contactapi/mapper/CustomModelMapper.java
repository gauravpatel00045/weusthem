package com.contactapi.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Refer to <a href=
 * "https://www.tabnine.com/code/java/classes/org.modelmapper.ModelMapper">ModelMapper</a>
 * 
 */
@Component
public class CustomModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * 
	 * Refer to <a href=
	 * "https://www.tabnine.com/code/java/classes/org.modelmapper.ModelMapper">ModelMapper</a>
	 * 
	 */
	public <D> D convertEntityToDto(Object source, Class<D> destination) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, destination);
	}

	/**
	 * 
	 * Refer to <a href=
	 * "https://www.tabnine.com/code/java/classes/org.modelmapper.ModelMapper">ModelMapper</a>
	 * 
	 */
	public <D> D convertDtoToEntity(Object source, Class<D> destination) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, destination);
	}

	/**
	 * <p>
	 * Note: outClass object must have default constructor with no arguments
	 * </p>
	 *
	 * @param entityList list of entities that needs to be mapped
	 * @param outCLass   class of result list element
	 * @param <D>        type of objects in result list
	 * @param <T>        type of entity in <code>entityList</code>
	 * @return list of mapped object with <code><D></code> type.
	 */
	public <D, T> List<D> mapAllToDtoList(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> modelMapper.map(entity, outCLass)).collect(Collectors.toList());
	}
	
}
