package ru.halt.practice.util;

/**
 * Created by Petr Rudenko on 25.01.2016.
 */
public enum  RoleUser {
    ANONYMOUS, ADMIN;

    public RoleUser getUserRole(RoleUser roleUser){
        for(RoleUser existRole: RoleUser.values()){
            if(roleUser == existRole){
                return roleUser;
            }
        }
        return ANONYMOUS;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
