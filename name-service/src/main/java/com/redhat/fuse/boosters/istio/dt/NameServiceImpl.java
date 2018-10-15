package com.redhat.fuse.boosters.istio.dt;

import org.springframework.stereotype.Service;

@Service("nameService")
public class NameServiceImpl implements NameService {

    private static final Name THE_NAME = new Name("Jacopo");

    @Override
    public Name getName() {
        return THE_NAME;
    }

}