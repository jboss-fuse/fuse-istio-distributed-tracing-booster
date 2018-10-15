package com.redhat.fuse.boosters.istio.dt;

/**
 * Service interface for name service.
 * 
 */
public interface NameService {

    /**
     * Generate Name
     *
     * @return a string name
     */
    Name getName();

}