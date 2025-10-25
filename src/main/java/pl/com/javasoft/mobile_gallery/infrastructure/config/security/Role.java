package pl.com.javasoft.mobile_gallery.infrastructure.config.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    CLIENT(Const.CLIENT), PHOTOGRAPH(Const.PHOTOGRAPH),ADMIN(Const.ADMIN);

    private final String role;

    @Override
    public String toString() {
        return this.role;
    }

    public static class Const {
        public static final String CLIENT="CLIENT";
        public static final String PHOTOGRAPH="PHOTOGRAPH";
        public static final String ADMIN="ADMIN";
    }

    public static class Quoted {
        public static final String CLIENT = "'"+Const.CLIENT+"'";
        public static final String PHOTOGRAPH = "'"+Const.PHOTOGRAPH+"'";
        public static final String ADMIN = "'"+Const.ADMIN+"'";
    }

}
