package ro.ubb.movies.web.converter;

import ro.ubb.movies.core.model.BaseEntity;
import ro.ubb.movies.web.dto.BaseDto;

/**
 * Created by andrapop on 2017-04-24.
 */
public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> extends ConverterGeneric<Model, Dto> {

}
