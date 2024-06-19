package lv.venta.model;

import java.util.ArrayList;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @ToString.Exclude
    private Dalibnieks idD;

    @Min(0)
    @Max(1000000)
    @Column(name = "Augstums")
    private int augstums;

    @Min(0)
    @Max(4)
    @Column(name = "KokaLimenis")
    private int kokaLimenis;

    @ManyToMany
    @JoinTable(
            name = "KoksUzlabojumi",
            joinColumns = @JoinColumn(name = "IdK"),
            inverseJoinColumns = @JoinColumn(name = "IdU")
    )
    private Collection<Uzlabojumi> uzlabojumi = new ArrayList<>();
    
    @OneToOne(mappedBy = "koks")
    @ToString.Exclude
    private Sasniegumi sasniegumi;
}
