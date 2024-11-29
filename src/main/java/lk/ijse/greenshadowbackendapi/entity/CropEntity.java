package lk.ijse.greenshadowbackendapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String cropCategory;
    private String cropSeason;
    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<FieldEntity> fieldEntityList;
    @OneToMany(mappedBy = "crop" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogServiceEntity> logServices;

}
