package kz.kase.example.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "ind_sessions_end_time", columnList = "endTime")})
public class Sessions extends PanacheEntityBase {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(comment = "Первичный ключ")
   public Long id;
   @Column(nullable = false, comment = "Дата и время начала сессии")
   public LocalDateTime endTime;

   @Column(comment = "Количество добавленных сделок")
   public Integer addCnt;
   @Column(comment = "Количество обновленных сделок")
   public Integer updateCnt;
   @Column(comment = "Количество ошибочных сделок")
   public Integer errorCnt;

   @Column(nullable = false, comment = "Статус сессии")
   public SessionStatus status;
   @Column(length = 65000, comment = "Сообщение об ошибке")
   public String errorMessage;
}
