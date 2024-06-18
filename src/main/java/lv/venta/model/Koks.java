package lv.venta.model;

import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "KoksTabula")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Koks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "IdK")
    private int idK;

    @OneToOne(mappedBy = "koks")
    @JoinColumn(name = "IdD")
    private Dalibnieks idD;

    @NotNull
    @Column(name = "Augstums")
    private int augstums;

    @NotNull
    @OneToOne(mappedBy = "koks")
    @JoinColumn(name = "KokaLimenis")
    private Sasniegumi kokaLimenis;

    @NotNull
    @OneToOne(mappedBy = "koks")
    @JoinColumn(name = "PupinuSkaitsParKlikski")
    private Pupinas pupinuSkaitsParKlikski;

    @ManyToMany(mappedBy = "koks")
    @JoinTable(
            name = "KoksUzlabojumi",
            joinColumns = @JoinColumn(name = "IdK"),
            inverseJoinColumns = @JoinColumn(name = "IdU")
    )
    private Collection<Uzlabojumi> idU;
}
