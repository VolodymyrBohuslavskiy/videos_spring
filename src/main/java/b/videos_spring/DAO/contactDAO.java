package b.videos_spring.DAO;

import b.videos_spring.models.contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface contactDAO extends JpaRepository<contact, Integer> {

    @Query("select c from contact c where c.id=:id")//Запит до БД
    List<contact> findById(int id);

    List<contact> findAllByName(String name);// Метод за допомогою підказок спрінг бут
}
