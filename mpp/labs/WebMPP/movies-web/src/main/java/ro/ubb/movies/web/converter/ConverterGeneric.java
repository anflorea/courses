package ro.ubb.movies.web.converter;

/**
 * Created by andrapop on 2017-05-05.
 */
public interface ConverterGeneric<Model, Dto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
