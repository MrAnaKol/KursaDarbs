package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private Dalibnieks idD;

    @Embedded
    private Pupinas pupinas;

    @OneToOne
    @JoinColumn(name = "Koks")
    private Koks koks;
    
    @Column(name = "KokaLimenis")
    private int kokaLimenis;
    public void setKokaLimenis() {
    	this.kokaLimenis = koks.getKokaLimenis();
    }

}
