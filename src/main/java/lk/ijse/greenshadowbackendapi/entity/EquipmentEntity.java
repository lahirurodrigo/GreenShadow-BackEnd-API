package lk.ijse.greenshadowbackendapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipments")
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
    @OneToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private FieldEntity fields;
}
