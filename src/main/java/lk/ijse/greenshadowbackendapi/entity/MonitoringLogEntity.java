package lk.ijse.greenshadowbackendapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "monitoring_log_service")
public class MonitoringLogEntity {
    @Id
    private String logCode;
    private Date logDate;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private FieldEntity fields;
    @ManyToOne
    @JoinColumn(name = "cropCode", nullable = false)
    private CropEntity crop;
}
