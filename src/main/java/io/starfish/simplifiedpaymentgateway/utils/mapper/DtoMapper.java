package io.starfish.simplifiedpaymentgateway.utils.mapper;

public interface DtoMapper<E, D> {
    D toDto(E e);
}
