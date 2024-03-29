package com.hypesofts.homember.technical.entityfetcher;

import com.hypesofts.homember.technical.baseclass.DomainEntity;
import com.hypesofts.homember.technical.baseclass.DomainId;

public interface EntityFetcher<I extends DomainId, E extends DomainEntity> {

    E fetchEntity(I domainId);

    Class<? extends DomainId> getDomainIdConcreteClass();

    default <I> boolean canFetch(DomainId id) {
        return id.getClass().isAssignableFrom(getDomainIdConcreteClass());
    }
}
