package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "PupinasTabula")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pupinas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "IdPu")
    private int idPu;

    @NotNull
    @OneToOne(mappedBy = "pupinas")
    @JoinColumn(name = "PupinuKopejaisSkaits")
    private Sasniegumi pupinuKopejaisSkaits;

    @NotNull
    @OneToOne(mappedBy = "pupinas")
    @JoinColumn(name = "PupinuSkaitsParKlikski")
    private Koks pupinuSkaitsParKlikski;

    @NotNull
    @Column(name = "AutonomasPupinas")
    private int autonomasPupinas;
}
