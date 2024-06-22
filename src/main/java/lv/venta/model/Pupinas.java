package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private int pupinuKopejaisSkaits = 0;

    @Min(1)
    @Max(100000)
    @Column(name = "PupinuSkaitsParKlikski")
    private int pupinuSkaitsParKlikski = 1;

    @Min(0)
    @Max(1000000)
    @Column(name = "AutonomasPupinas")
    private int autonomasPupinas = 0;
}
