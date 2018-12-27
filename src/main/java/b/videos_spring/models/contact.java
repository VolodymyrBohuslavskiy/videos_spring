package b.videos_spring.models;

import lombok.AccessLevel;
import lombok.Data;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
public class contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    String name, surname, tel, avatar;


    public contact(String name, String surname, String tel) {
        this.name = name;
        this.surname = surname;
        this.tel = tel;
    }

    public contact() {
    }
}
