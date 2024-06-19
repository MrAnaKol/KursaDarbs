package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Getter
@Setter
@NoArgsConstructor
@ToString
@Embeddable
public class Pupinas {

    @Min(0)
    @Max(1000000000)
    @Column(name = "PupinuKopejaisSkaits")
    private int pupinuKopejaisSkaits;

    @Min(1)
    @Max(100000)
    @Column(name = "PupinuSkaitsParKlikski")
    private int pupinuSkaitsParKlikski;

    @Min(0)
    @Max(1000000)
    @Column(name = "AutonomasPupinas")
    private int autonomasPupinas;
}
