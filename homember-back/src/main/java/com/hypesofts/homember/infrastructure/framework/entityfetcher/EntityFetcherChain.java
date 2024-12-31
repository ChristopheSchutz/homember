package com.hypesofts.homember.infrastructure.framework.entityfetcher;

import com.hypesofts.homember.infrastructure.framework.baseclass.DomainEntity;
import com.hypesofts.homember.infrastructure.framework.baseclass.DomainId;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

@Configurable
public class EntityFetcherChain {

    private static EntityFetcherChain instance = new EntityFetcherChain();
    private final List<EntityFetcher> entityFetchers = SpringContext.getBeans(EntityFetcher.class);

    private static EntityFetcherChain getInstance() {
        if (instance == null) {
            instance = new EntityFetcherChain();
        }
        return instance;
    }

    public static DomainEntity fetchEntity(DomainId domainId) {
        for (EntityFetcher fetcher : getInstance().entityFetchers) {
            if (fetcher.canFetch(domainId)) {
                return fetcher.fetchEntity(domainId);
            }
        }
        throw new NoEntityFetcherFound(domainId);
    }
}
