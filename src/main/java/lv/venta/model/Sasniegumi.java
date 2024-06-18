package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "SasniegumiTabula")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Sasniegumi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "IdS")
    private int idS;

    @OneToOne(mappedBy = "sasniegumi")
    private Dalibnieks dalibnieks;

    @NotNull
    @Column(name = "PupinuKopejaisSkaits")
    private int pupinuKopejaisSkaits;

    @NotNull
    @Column(name = "KokaLimenis")
    private int kokaLimenis;

    @NotNull
    @Column(name = "UzspiestasReizes")
    private int uzspiestasReizes;
}
