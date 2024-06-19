package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "PirkumiTabula")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pirkumi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "IdPi")
    private int idPi;

    @ManyToOne
    @JoinColumn(name = "IdD")
    private Dalibnieks dalibnieks;
    public Dalibnieks getIdD() {
    	return dalibnieks;
    }

    @Column(name = "DatumsUnLaiks")
    private LocalDateTime datumsUnLaiks = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "IdU")
    private Uzlabojumi uzlabojumi;
}