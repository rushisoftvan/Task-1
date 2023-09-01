package com.softvan.softrepoexception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String msg){
        super(msg);
    }
}
