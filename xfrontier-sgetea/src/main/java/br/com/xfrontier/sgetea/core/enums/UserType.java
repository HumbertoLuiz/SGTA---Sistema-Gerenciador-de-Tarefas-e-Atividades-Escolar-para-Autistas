package br.com.xfrontier.sgetea.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
	
    ADMIN(1),
    USER(2),
    REGISTER(3);

    private final Integer id;

    public static UserType fromId(Integer id) {
        for (UserType userType : values()) {
            if (userType.getId().equals(id)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Invalid UserType id: " + id);
    }

}
