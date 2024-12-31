package com.hypesofts.homember.infrastructure.framework.entityfetcher;

import com.hypesofts.homember.infrastructure.framework.baseclass.DomainEntity;
import com.hypesofts.homember.infrastructure.framework.baseclass.DomainId;

public interface EntityFetcher<I extends DomainId, E extends DomainEntity> {

    E fetchEntity(I domainId);

    Class<? extends DomainId> getDomainIdConcreteClass();

    default boolean canFetch(DomainId id) {
        return id.getClass().isAssignableFrom(getDomainIdConcreteClass());
    }
}
