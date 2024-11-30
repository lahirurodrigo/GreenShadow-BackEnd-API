package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.dto.StaffStatus;
import lk.ijse.greenshadowbackendapi.dto.SuperDTO;
import lk.ijse.greenshadowbackendapi.util.Gender;
import lk.ijse.greenshadowbackendapi.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffStatus {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private Date joinDate;
    private Date DOB;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String contactNo;
    private String email;
    private Role role;
}
