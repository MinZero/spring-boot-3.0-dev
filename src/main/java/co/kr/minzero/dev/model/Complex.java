package co.kr.minzero.dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("complex")
public class Complex {
    @Id
    @Column("complex_id")
    private Integer complexId;

    @Column("complex_name_kr")
    private String complexNameKr;

    @Column("complex_name_en")
    private String complexNameEn;
}
