package com.bluesoft.servermanager.service.implementation;

import com.bluesoft.servermanager.model.Server;
import com.bluesoft.servermanager.service.ServerService;

import java.io.IOException;
import java.util.Collection;

class JpaServerService implements ServerService {
    @Override
    public Server create(final Server server) {
        return null;
    }

    @Override
    public Server ping(final String ipAddress) throws IOException {
        return null;
    }

    @Override
    public Collection<Server> list(final int limit) {
        return null;
    }

    @Override
    public Server get(final long id) {
        return null;
    }

    @Override
    public Server update(final Server server) {
        return null;
    }

    @Override
    public boolean delete(final long id) {
        return false;
    }
}
