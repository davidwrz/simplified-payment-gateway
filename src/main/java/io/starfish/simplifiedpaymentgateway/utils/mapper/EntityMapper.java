package io.starfish.simplifiedpaymentgateway.utils.mapper;

public interface EntityMapper<E, D> {
    E toEntity(D d);
}
