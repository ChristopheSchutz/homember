package com.hypesofts.homember.application.technical.entityfetcher;

import com.hypesofts.homember.application.technical.baseclass.DomainEntity;
import com.hypesofts.homember.application.technical.baseclass.DomainId;

public interface EntityFetcher<I extends DomainId, E extends DomainEntity> {

    E fetchEntity(I domainId);

    Class<? extends DomainId> getDomainIdConcreteClass();

    default <I> boolean canFetch(DomainId id) {
        return id.getClass().isAssignableFrom(getDomainIdConcreteClass());
    }
}
