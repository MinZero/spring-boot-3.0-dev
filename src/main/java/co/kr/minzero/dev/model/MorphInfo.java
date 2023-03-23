package co.kr.minzero.dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("morph_info")
public class MorphInfo {
    @Id
    @Column("morph_info_seq")
    private int morphInfoSeq;

    @Column("morph_name_kr")
    private String morphNameKr;

    @Column("morph_name_en")
    private String morphNameEn;

    @Column("morph_type_code_cd")
    private String morphTypeCodeCd;

    @Column("morph_complex")
    private String morphComplex;

    @Column("morph_issue")
    private String morphIssue;

    @Column("search_keyword")
    private String searchKeyword;

    @Column("link_url_kr")
    private String linkUrlKr;

    @Column("link_url_en")
    private String linkUrlEn;
}
