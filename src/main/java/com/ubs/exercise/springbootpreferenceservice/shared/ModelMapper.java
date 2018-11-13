package com.ubs.exercise.springbootpreferenceservice.shared;

/**
 * A generic model mapper interface.
 *
 * @param <D> Dto object
 * @param <E> Entity object
 * @author Efthymios Doukas
 */
public interface ModelMapper<D, E> {

    E mapDtoToEntity(D d);

}
