package com.bluesoft.servermanager.service.implementation;

import com.bluesoft.servermanager.enumerationv.Status;
import com.bluesoft.servermanager.model.Server;
import com.bluesoft.servermanager.repository.ServerRepository;
import com.bluesoft.servermanager.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Random;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
class JpaServerService implements ServerService {

    private final ServerRepository repository;

    @Override
    public Server create(final Server server) {
        log.info("Serving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return repository.save(server);
    }

    @Override
    public Server ping(final String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = repository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP: Status.SERVER_DOWN);
        repository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(final int limit) {
        log.info("Fetching all servers");
        return repository.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(final long id) {
        log.info("Fetching server by id {}",id);
        return repository.findById(id).get();
    }

    @Override
    public Server update(final Server server) {
        log.info("Updating server {}",server.getName());
        return repository.save(server);
    }

    @Override
    public boolean delete(final long id) {
        log.info("deleting server by ID {}",id);
        repository.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imageNames[new Random().nextInt(4)]).toUriString();
    }

    private boolean isReachable(String ipAddress, int port, int timeOut){
        try{
            try(Socket socket = new Socket()){
                socket.connect(new InetSocketAddress(ipAddress,port), timeOut);
            }
            return true;
        }catch (IOException exception){
            return false;
        }
    }


}
