package com.bluesoft.servermanager.service;

import com.bluesoft.servermanager.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(long id);
    Server update(Server server);
    boolean delete(long id);
}
