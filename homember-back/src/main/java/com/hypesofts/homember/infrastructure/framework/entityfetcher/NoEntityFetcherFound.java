package com.hypesofts.homember.infrastructure.framework.entityfetcher;

import com.hypesofts.homember.infrastructure.framework.baseclass.DomainId;

public class NoEntityFetcherFound extends RuntimeException {

    public NoEntityFetcherFound(DomainId domainId) {
        super("No fetcher found for " + domainId.getClass().getName());
    }
}
