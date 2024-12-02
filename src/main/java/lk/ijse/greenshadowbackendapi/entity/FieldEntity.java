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
@Table(name = "fields")
public class FieldEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double fieldSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage01;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage02;
    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private CropEntity crop;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogEntity> logServices;
    @ManyToMany
    @JoinTable(
            name = "field_staff",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staffMembers;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EquipmentEntity> equipments;
}
