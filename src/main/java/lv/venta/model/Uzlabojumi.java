package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "UzlabojumiTabula")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Uzlabojumi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "IdU")
    private int idU;

    @ManyToMany(mappedBy = "uzlabojumi")
    @ToString.Exclude
    private Collection<Koks> koki = new ArrayList<Koks>();

    @OneToOne(mappedBy = "uzlabojumi")
    @ToString.Exclude
    private Pirkumi pirkumi;

    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "Nosaukums")
    private String nosaukums;

    @Min(0)
    @Max(1000000000)
    @Column(name = "Cena")
    private int cena;

    @NotNull
    @Column(name = "UzlabojumaTips")
    private UzlabojumaTips uzlabojumaTips;
}