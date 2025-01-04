package br.com.fmatheus.app.controller.converter;

/**
 * @param <T> Entidade
 * @param <S> DTO de requisicao
 * @param <U> DTO de resposta
 */
public interface MapperConverter<T, S, U> {

    T converterToEntity(S s);

    U converterToResponse(T t);
}
