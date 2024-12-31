package com.hypesofts.homember.infrastructure.framework.baseclass;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hypesofts.homember.infrastructure.framework.entityfetcher.EntityFetcherChain;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class FetchableDomainId<T extends DomainEntity> implements DomainId {
    @JsonUnwrapped
    private final UUID id;

    protected FetchableDomainId(UUID id) {
        this.id = id;
    }

    public T fetchEntity() {
        return (T) EntityFetcherChain.fetchEntity(this);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof FetchableDomainId domainId && this.id.equals(domainId.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}