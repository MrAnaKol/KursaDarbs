package lv.venta.model;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "DalibnieksTabula")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dalibnieks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "IdD")
    private int idD;

    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "Loma")
    private String loma = "Spēlētājs";

    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp = "[a-zāēūīļķģšžčņA-ZĀĒŪĪĻĶĢŠŽČŅ0-9]+")
    @Column(name = "Lietotajvards")
    private String lietotajvards;

    @NotNull
    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$") //Satur vismaz vienu burtu, vienu ciparu un vienu speciālo rakstzīmi.
    @Column(name = "Parole")
    private String parole;

    @Column(name = "IzveidesDatums")
    private LocalDateTime izveidesDatums = LocalDateTime.now();
    
    @Column(name = "PedejoReiziIegajis")
    private LocalDateTime pedejoReiziIegajis = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdS")
    private Sasniegumi sasniegumi;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdK")
    private Koks koks;

    @OneToMany(mappedBy = "dalibnieks")
    @ToString.Exclude
    private Collection<Pirkumi> pirkumi;


    public Dalibnieks(String loma, String lietotajvards, String parole) {
        setLoma(loma);
        setLietotajvards(lietotajvards);
        setParole(parole);
        setKoks(new Koks());
        setSasniegumi(new Sasniegumi(koks));
    }
}
