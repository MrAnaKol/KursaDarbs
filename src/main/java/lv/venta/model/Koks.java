package lv.venta.model;

import java.util.Set;

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
    private Dalibnieks dalibnieks;

    @NotNull
    @Column(name = "Augstums")
    private int augstums;

    @NotNull
    @OneToOne(mappedBy = "kokaLimenis")
    @JoinColumn(name = "KokaLimenis")
    private Sasniegumi kokaLimenis;

    @NotNull
    @OneToOne(mappedBy = "pupinuSkaitsParKlikski")
    @JoinColumn(name = "PupinuSkaitsParKlikski")
    private Pupinas pupinuSkaitsParKlikski;

    @ManyToMany(mappedBy = "koki")
    @JoinTable(
            name = "Koks_Uzlabojumi",
            joinColumns = @JoinColumn(name = "IdK"),
            inverseJoinColumns = @JoinColumn(name = "IdU")
    )
    private Set<Uzlabojumi> uzlabojumi;
}
